package com.github.dmtk;

import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Controller", urlPatterns = {
    "/analitics",
    "/controller",
    "/export",
    "/index.html",
    "/jsp",
    "/overview",
    "/about",
    "/logout",
    "/map",
    "/settings",
    "/rawdata",
    "/reports",
    "/graphics",
    "/authenticate"})

public class Controller extends HttpServlet {

    @EJB
    private NetworkEventFacadeLocal networkEventFacade;
    @EJB
    private SensorNodeFacadeLocal sensorNodeFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        if (session.getAttribute("authenticated") != null && session.getAttribute("authenticated").equals(true)) {
            if ("/about".equals(request.getServletPath())) {
                request.getRequestDispatcher("jsp/about.jsp").forward(request, response);
            } else if ("/analitics".equals(request.getServletPath())) {
                request.getRequestDispatcher("jsp/analitics.jsp").forward(request, response);
            } else if ("/export".equals(request.getServletPath())) {
                request.getRequestDispatcher("jsp/export.jsp").forward(request, response);
            } else if ("/graphics".equals(request.getServletPath())) {
                request.getRequestDispatcher("jsp/graph.jsp").forward(request, response);
            } else if ("/logout".equals(request.getServletPath())) {
                session.invalidate();
                request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
            } else if ("/map".equals(request.getServletPath())) {
                request.getRequestDispatcher("jsp/map.jsp").forward(request, response);
            } else if ("/rawdata".equals(request.getServletPath())) {
                request.getRequestDispatcher("jsp/rawdata.jsp").forward(request, response);
            } else if ("/reports".equals(request.getServletPath())) {
                request.getRequestDispatcher("jsp/reports.jsp").forward(request, response);
            } else if ("/settings".equals(request.getServletPath())) {
                request.getRequestDispatcher("jsp/settings.jsp").forward(request, response);
            } else {
                int page = 1;
                if(request.getAttribute("page")!=null){
                   page = parseIntegerValue(request.getAttribute("page"));
                }
                session.setAttribute("events", getSubList(networkEventFacade.findAll(), page, 20));
                session.setAttribute("nodes", sensorNodeFacade.findAll());
                request.getRequestDispatcher("jsp/overview.jsp").forward(request, response);
            }
        } else {
            request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
        }

    }

    private Integer parseIntegerValue(Object obj) {
        
        Integer result = 0;
        try {
            result = Integer.parseInt((String) obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private List<Object> getSubList(List inputList, int page, int itemsPerPage) {

        if (0 != inputList.size()) {
            int start = page * itemsPerPage;
            int end = (page + 1) * itemsPerPage;
            if (end >= inputList.size()) {
                end = inputList.size() - 1;
            }
            List<Object> result = inputList.subList(start, end);
            return result;
        } else {
            return inputList;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if ("/authenticate".equals(request.getServletPath())) {
            String login = (String) request.getParameter("login");
            String password = (String) request.getParameter("password");

            if ("admin".equals(login)) {

                boolean authenticated = true;
                HttpSession session = request.getSession();
                session.setAttribute("user", login);
                session.setAttribute("password", password);
                session.setAttribute("authenticated", authenticated);
                doGet(request, response);

            } else {

                request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
            }
        }

    }

}
