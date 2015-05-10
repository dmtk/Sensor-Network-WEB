package com.github.dmtk;

import java.io.IOException;
import java.util.LinkedList;
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
   
   @EJB SensorNode node;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        if (session.getAttribute("authenticated") != null && session.getAttribute("authenticated").equals(true)) {

            
            List<SensorNode>sn =new LinkedList();
            for(int i=0;i<10;i++){
                sn.add(node);
            }
                       
            session.setAttribute("sn", sn);
            session.setAttribute("nodes", sn);
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
