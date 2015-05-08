package com.github.dmtk;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Controller", urlPatterns = {"/controller", "/index.html","/jsp","/overview"})
public class Controller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        if (session.getAttribute("authenticated") != null && session.getAttribute("authenticated").equals(true)) {

            List<SensorNode> sn = new LinkedList();
            for (int i = 0; i < 100; i++) {
                sn.add(new SensorNode());
            }
            
            List<SensorNode> nodes = new LinkedList();
            for (int i = 0; i < 8; i++) {
                nodes.add(new SensorNode("Sensor node #"+i,i));
            }

            request.setAttribute("sn", sn);
            request.setAttribute("nodes", nodes);
            request.getRequestDispatcher("jsp/overview.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
