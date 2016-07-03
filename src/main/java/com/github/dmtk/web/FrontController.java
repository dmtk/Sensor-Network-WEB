package com.github.dmtk.web;

import com.github.dmtk.entity.Measurement;
import com.github.dmtk.entity.Sensor;
import com.github.dmtk.jms.server.JmsMessageListener;
import com.github.dmtk.jms.client.JmsMessageProducer;
import com.github.dmtk.logic.CoapEngine;
import com.github.dmtk.logic.MeasurementService;
import com.github.dmtk.logic.SensorNodeService;
import com.github.dmtk.logic.SensorService;
import com.github.dmtk.utils.ExcelExport;
import com.google.gson.Gson;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import javax.jms.JMSException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FrontController {

    @Autowired
    private CoapEngine controller;
    @Autowired
    private MeasurementService measurementService;
    @Autowired
    private SensorService sensorService;
    @Autowired
    private SensorNodeService sensorNodeService;
   
    

    private final static Logger log = LogManager.getLogger(FrontController.class);
    private final Properties menu = new Properties();

    FrontController() {
        menu.setProperty("options", "Options");
        menu.setProperty("charts", "Charts");
        menu.setProperty("datalog", "DataLog");
        menu.setProperty("export", "Export");
        menu.setProperty("overview", "Overview");
        menu.setProperty("sensors", "Sensors");
        menu.setProperty("sensornodes", "Sensor nodes");
        menu.setProperty("about", "About");

    }

    private void handleRequest(HttpServletRequest request) {
        request.setAttribute("menu", menu);
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(HttpServletRequest request, HttpServletResponse response) {

        request.setAttribute("activePage", "login");
        request.setAttribute("error", request.getParameter("error"));
        if (request.getParameter("logout") != null) {
            request.setAttribute("logout", "You have been logged out successfully");
        }
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

    @RequestMapping(value = "/overview", method = RequestMethod.GET)
    public String overview(HttpServletRequest request, HttpServletResponse response) {
        
        handleRequest(request);
        request.setAttribute("activePage", "overview");
        request.setAttribute("sensors", sensorService.getList());
        request.setAttribute("measurements", measurementService.getListOrderByDate(1000));
        return "main";
    }

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String about(HttpServletRequest request, HttpServletResponse response) {

        handleRequest(request);
        request.setAttribute("activePage", "about");
        return "main";
    }

    @RequestMapping(value = "/datalog", method = RequestMethod.GET)
    public String datalog(HttpServletRequest request, HttpServletResponse response) {

        handleRequest(request);
        request.setAttribute("activePage", "datalog");
        String page = request.getParameter("pageNumber");
        String pageSize = request.getParameter("pageSize");
        int pageNumber = 1;
        int pageSizeNumber = 10;

        if (page != null && pageSize != null) {
            try {
                pageNumber = Integer.parseInt(page);
                pageSizeNumber = Integer.parseInt(pageSize);
            } catch (NumberFormatException ex) {
                log.error(ex);
            }
        }
        request.setAttribute("activePage", "datalog");

        List<Integer> pages = new LinkedList();
        int lastPageNumber = (int) (measurementService.getCount() / pageSizeNumber);

        int step = 4;

        int previous = pageNumber - step < 1 ? 1 : pageNumber - step;
        int next = pageNumber + step > lastPageNumber ? lastPageNumber : pageNumber + step;

        if (pageNumber == 1) {
            pages.add(1);
        }

        for (int i = previous + 1; i < next; i++) {
            pages.add(i);
        }
        
        request.setAttribute("previous", previous);
        request.setAttribute("next", next);
        request.setAttribute("pages", pages);
        request.setAttribute("pageNumber", pageNumber);
        request.setAttribute("pageSize", pageSizeNumber);
        request.setAttribute("measurements", measurementService.getPage(pageNumber, pageSizeNumber, "date"));//sort by date
        return "main";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(HttpServletRequest request, HttpServletResponse response) {

        return "redirect:overview";
    }

    @RequestMapping(value = "/charts", method = RequestMethod.GET)
    public String charts(HttpServletRequest request, HttpServletResponse response) {

        request.setAttribute("activePage", "charts");
        handleRequest(request);
        request.setAttribute("sensors", sensorService.getList());
        return "main";
    }

    @RequestMapping(value = "/sensors", method = RequestMethod.GET)
    public String sensors(HttpServletRequest request, HttpServletResponse response) {

        request.setAttribute("activePage", "sensors");
        handleRequest(request);
        request.setAttribute("sensors", sensorService.getList());
        return "main";
    }

    @RequestMapping(value = "/sensornodes", method = RequestMethod.GET)
    public String sensorNodes(HttpServletRequest request, HttpServletResponse response) {

        request.setAttribute("activePage", "sensornodes");
        handleRequest(request);
        request.setAttribute("sensorNodes", sensorNodeService.getList());
        return "main";
    }
    
    @RequestMapping(value = "/start", method = RequestMethod.GET)
    public String admin(HttpServletRequest request, HttpServletResponse response) {

        controller.startUp();
        return "redirect:datalog";
    }

    @RequestMapping(value = "/options", method = RequestMethod.GET)
    public String options(HttpServletRequest request, HttpServletResponse response) {

        request.setAttribute("activePage", "options");
        handleRequest(request);
        String action = request.getParameter("action");
        if ("edit".equals(action)) {
            String idStr = request.getParameter("sensorId");
            if (idStr != null) {
                Long sensorId = Long.parseLong(idStr);
                request.setAttribute("sensor", sensorService.retrive(sensorId));
            }
        }

        return "main";
    }

    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public String save(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Sensor sensor = new Sensor();
        String idStr = request.getParameter("sensor_id");
        Integer sensorId = Integer.parseInt(idStr);
        sensor.setId(sensorId);
        sensor.setMeasuredQuantity(request.getParameter("sensor_measuredQuantity"));
        sensor.setName(request.getParameter("sensor_name"));
        sensor.setCoapURI(request.getParameter("sensor_coapURI"));
        controller.addCoAPConnection(sensor);
        sensorService.save(sensor);
        return "redirect:sensors";
    }

    @RequestMapping(value = "/plot")
    public void plot(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String sensorName = "";//by default
        int maxPoints = 1000;

        try {
            sensorName = request.getParameter("sensorName");
        } catch (NumberFormatException ex) {
            log.error(ex);
        }

        List<Measurement> listEvents = measurementService.getListBySensorNameOrderByDate(sensorName, maxPoints);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        double[][] arr = new double[listEvents.size()][2];
        int i = 0;
        for (Measurement m : listEvents) {
            arr[i][0] = m.getDate().getTime();
            arr[i][1] = m.getValue();
            i++;
        }
        response.getWriter().write(new Gson().toJson(arr));

    }

    @RequestMapping(value = "/livedata")
    public void livedata(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String sensorName = "";
        sensorName = request.getParameter("sensorName");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        double[] arr = new double[2];
        Measurement m = measurementService.getLastBySensorName(sensorName);
        arr[0] = m.getDate().getTime();
        arr[1] = m.getValue();
        response.getWriter().write(new Gson().toJson(arr));

    }

    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public String export(HttpServletRequest request, HttpServletResponse response) {

        request.setAttribute("activePage", "export");
        handleRequest(request);
        String action = request.getParameter("action");
        if ("results-to-excel".equals(action)) {
            try {
                Integer resultsCount = 1000;
                File exelFile = new ExcelExport().exportExperiments(measurementService.getListOrderByDate(resultsCount));
                sendFile("experiments.xls", exelFile, response);
            } catch (IOException ex) {
                log.error(ex);
            } catch (ServletException ex) {
                log.error(ex);
            }
        }
        return "main";

    }

    private void sendFile(String fileName, File excelFile, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment; filename=" + fileName);
        OutputStream out = response.getOutputStream();
        FileInputStream in = new FileInputStream(excelFile);
        byte[] buffer = new byte[4096];
        int length;
        while ((length = in.read(buffer)) > 0) {
            out.write(buffer, 0, length);
        }
        in.close();
        out.flush();
        excelFile.delete();
    }
}
