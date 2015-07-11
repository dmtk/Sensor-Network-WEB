package com.github.dmtk;

import com.github.dmtk.entity.NetworkEventFacadeLocal;
import com.github.dmtk.entity.SensorNodeFacadeLocal;
import com.github.dmtk.logic.NetworkController;
import java.io.IOException;
import java.util.Arrays;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "FrontController", urlPatterns = {
    "/index.htm",
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
    "/guest"})

public class FrontController extends HttpServlet {

    @EJB(name = "NetworkEventFacade", beanInterface = NetworkEventFacadeLocal.class)
    private NetworkEventFacadeLocal networkEventFacade;
    @EJB(name = "SensorNodeFacade", beanInterface = SensorNodeFacadeLocal.class)
    private SensorNodeFacadeLocal sensorNodeFacade;
    @EJB(name = "NetworkController")
    private NetworkController controller;

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try{
            HttpSession session = request.getSession();
            
            Action action = ActionFactory.create(getActionName(request));
            String url = action.perform(request, response);
            if (url != null) {
                getServletContext().getRequestDispatcher(url).forward(request, response);
            }
        }catch (Exception ex) {
            throw new RuntimeException(ex + " " + Arrays.toString(ex.getStackTrace()));
        }

    }

    /**
     * Parse the path action string and return the value of it
     */
    private String getActionName(HttpServletRequest request) {

        String path = request.getServletPath();
        return path.substring(1);
    }

}
