package com.github.dmtk;

import com.github.dmtk.entity.NetworkEventFacadeLocal;
import com.github.dmtk.entity.SensorNodeFacadeLocal;
import com.github.dmtk.logic.NetworkController;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*@WebServlet(name = "Controller", urlPatterns = {
    "/analitics",
    "/controller",
    "/export",
    "/jsp",
    "/overview",
    "/about",
    "/logout",
    "/map",
    "/settings",
    "/rawdata",
    "/reports",
    "/graphics",
    "/authenticate",
    "/reconnect",
    "/guest"})*/
public class Controller extends HttpServlet {

    /*@EJB
    private NetworkEventFacadeLocal networkEventFacade;
    @EJB
    private SensorNodeFacadeLocal sensorNodeFacade;
    @EJB
    private NetworkController controller;

    @Override

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        
        if ("/guest".equals(request.getServletPath())) {
            

        }
        if (session.getAttribute("authenticated") != null && session.getAttribute("authenticated").equals(true)) {
            String path = request.getServletPath();

            switch (path) {

                case "/about":
                    request.getRequestDispatcher("jsp/about.jsp").forward(request, response);
                    break;
                case "/analitics":

                    request.setAttribute("avg", networkEventFacade.getAvg(1));
                    request.getRequestDispatcher("jsp/analitics.jsp").forward(request, response);

                    break;
                case "/export":
                    request.getRequestDispatcher("jsp/export.jsp").forward(request, response);
                    break;
                case "/graphics":
                    request.setAttribute("nodes", sensorNodeFacade.findAll());
                    request.getRequestDispatcher("jsp/graph.jsp").forward(request, response);
                    break;
                case "/logout":
                    session.invalidate();
                    request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
                    break;
                case "/map":
                    request.setAttribute("nodes", sensorNodeFacade.findAll());
                    request.getRequestDispatcher("jsp/map.jsp").forward(request, response);
                    break;
                case "/rawdata":
                    request.getRequestDispatcher("jsp/rawdata.jsp").forward(request, response);
                    break;
                case "/reports":
                    int page = 1;
                    if (request.getParameter("page") != null) {
                        page = Integer.parseInt(request.getParameter("page"));
                    }
                    int itemsPerPage = 20;
                    if (request.getParameter("items") != null) {
                        itemsPerPage = Integer.parseInt(request.getParameter("items"));
                    }
                    request.setAttribute("events", networkEventFacade.findByDate(itemsPerPage));
                    request.getRequestDispatcher("jsp/reports.jsp").forward(request, response);
                    break;
                case "/settings":
                    request.getRequestDispatcher("jsp/settings.jsp").forward(request, response);
                    break;
                case "/reconnect":
                    controller.reconnect();
                    request.getRequestDispatcher("jsp/rawdata.jsp").forward(request, response);
                    break;
                default:

                    int itemsPerWebPage = 20;
                    request.setAttribute("events", networkEventFacade.findByDate(itemsPerWebPage));
                    request.setAttribute("nodes", sensorNodeFacade.findAll());
                    request.getRequestDispatcher("jsp/overview.jsp").forward(request, response);
                    break;
            }
        } else {

            String initialPage = request.getServletPath();
            session.setAttribute("initialPage", initialPage);
            request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
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

    }*/
}
