package com.github.dmtk;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
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

        List<SensorNode> sn = new LinkedList();
        for (int i = 0; i < 100; i++) {
            sn.add(new SensorNode());
        }

        request.setAttribute("sn", sn);
        /*HttpSession session = request.getSession();
         session.setAttribute("node2", node2);
         ServletContext context = getServletContext();
         context.setAttribute("node3", node3);*/
        request.getRequestDispatcher("jsp/overview.jsp").forward(request, response);

    }

}
