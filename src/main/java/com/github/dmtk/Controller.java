/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.dmtk;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Controller", urlPatterns = {"/controller", "/index.html"})
public class Controller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        SensorNode node1 = new SensorNode();
        SensorNode node2 = new SensorNode();
        SensorNode node3 = new SensorNode();
        request.setAttribute("node1", node1);
        HttpSession session = request.getSession();
        session.setAttribute("node2", node2);
        ServletContext context = getServletContext();
        context.setAttribute("node3", node3);
        request.getRequestDispatcher("jsp/overview.jsp").forward(request, response);

    }

    
}
