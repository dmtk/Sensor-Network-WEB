package com.github.dmtk.web;

import com.github.dmtk.entity.Measurement;
import com.github.dmtk.logic.NetworkController;
import com.github.dmtk.logic.MeasurementService;
import com.github.dmtk.logic.SensorService;
import com.github.dmtk.utils.ExcelExport;
import com.google.gson.Gson;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FrontController {

    @Autowired
    private NetworkController controller;
    @Autowired
    private MeasurementService measurementService;
    @Autowired
    private SensorService sensorService;

    private final Properties menu = new Properties();

    FrontController() {
        menu.setProperty("options", "Options");
        menu.setProperty("charts", "Charts");
        menu.setProperty("datalog", "DataLog");
        menu.setProperty("export", "Export");
        menu.setProperty("overview", "Overview");

    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(HttpServletRequest request, HttpServletResponse response) {

        request.setAttribute("activePage", "login");
        request.setAttribute("error", request.getParameter("error"));
        return "login";
    }

    @RequestMapping(value = "/overview", method = RequestMethod.GET)
    public String perform(HttpServletRequest request, HttpServletResponse response) {

        request.setAttribute("activePage", "overview");
        request.setAttribute("menu", menu);
        request.setAttribute("measurements", measurementService.getList());
        return "main";
    }

    @RequestMapping(value = "/options", method = RequestMethod.GET)
    public String perform2(HttpServletRequest request, HttpServletResponse response) {

        request.setAttribute("activePage", "options");
        request.setAttribute("measurements", measurementService.getList());
        request.setAttribute("menu", menu);
        return "main";
    }

    @RequestMapping(value = "/datalog", method = RequestMethod.GET)
    public String perform4(HttpServletRequest request, HttpServletResponse response) {

        int sensorId=1;
        request.setAttribute("activePage", "datalog");
        request.setAttribute("measurements", measurementService.findBySensorId(sensorId));
        request.setAttribute("menu", menu);
        return "main";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String perform1(HttpServletRequest request, HttpServletResponse response) {

        return "redirect:overview";
    }

    @RequestMapping(value = "/charts", method = RequestMethod.GET)
    public String perform3(HttpServletRequest request, HttpServletResponse response) {

        request.setAttribute("activePage", "charts");
        request.setAttribute("menu", menu);
        request.setAttribute("sensors", sensorService.getList());
        return "main";
    }

    @RequestMapping(value = "/plot", method = RequestMethod.POST)
    public void perform5(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Integer sensorId = 1;//by default
        try {
            sensorId = Integer.parseInt(request.getParameter("nodeId"));
        } catch (NumberFormatException e) {
            
        }

        List listEvents = measurementService.findBySensorId(sensorId);

        double[] data = new double[listEvents.size()];
        Iterator it = listEvents.iterator();
        int j = 0;
        while (it.hasNext()) {

            data[j] = ((Measurement) it.next()).getValue();
            j++;
        }

        Map< String, Object> map = new HashMap< String, Object>();
        boolean isValid = false;
        String[] temperature = new String[data.length];
        for (int i = 0; i < data.length; i++) {
            temperature[i] = String.valueOf(data[i]);
        }
        isValid = true;
        map.put("temperature", data);
        map.put("isValid", isValid);

        write(response, map);

    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void update(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Map<String, Object> map = new HashMap<String, Object>();
        boolean isValid = false;
        String temperature;
        isValid = true;
        temperature = controller.getData();
        map.put("temperature", temperature);
        map.put("isValid", isValid);
        write(response, map);

    }
    
    @RequestMapping(value = "/livedata")
    public void livedata(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        double[] arr = new double[2];
        Measurement m= measurementService.findBySensorIdOrderByDate(1);
        arr[0]=m.getDate().getTime();
        arr[1]=m.getValue();
        response.getWriter().write(new Gson().toJson(arr));

    }

    private void write(HttpServletResponse response, Map<String, Object> map) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new Gson().toJson(map));
    }
    
    

    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public String export(HttpServletRequest request, HttpServletResponse response) {

        request.setAttribute("activePage", "export");
        request.setAttribute("menu", menu);
        String action = request.getParameter("action");
        if ("results-to-excel".equals(action)) {
            try {
                File exelFile = new ExcelExport().exportExperiments(measurementService.getList());
                sendFile("experiments.xls", exelFile, response);
            } catch (IOException ex) {

            } catch (ServletException ex) {

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
