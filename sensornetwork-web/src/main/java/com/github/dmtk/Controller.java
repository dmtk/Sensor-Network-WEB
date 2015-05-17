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

@WebServlet(name = "Controller", urlPatterns = {"/controller", "/index.html", "/jsp", "/overview"})

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

            request.getAttribute("page");
            session.setAttribute("events", getSubList(networkEventFacade.findAll(),1,20));
            session.setAttribute("nodes", sensorNodeFacade.findAll());
            request.getRequestDispatcher("jsp/overview.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
        }

    }

    private List<Object> getSubList(List inputList, int page, int itemsPerPage) {

        page--;//list index starts from 0
        int start = page * itemsPerPage;
        int end = (page + 1) * itemsPerPage;
        if (end >= inputList.size()) {
            end = inputList.size() - 1;
        }
        List<Object> result = inputList.subList(start, end);
        return result;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
