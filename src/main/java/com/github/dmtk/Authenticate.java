package com.github.dmtk;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "authenticate", urlPatterns = {"/authenticate"})

public class Authenticate extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        
        String login = (String) request.getParameter("login");
        String password = (String) request.getParameter("password");
        System.out.println("st");
        if ("admin".equals(login)) {
            
            boolean authenticated = true;
            HttpSession session = request.getSession();
            session.setAttribute("user", login);
            session.setAttribute("password", password);
            session.setAttribute("authenticated", authenticated);
            request.getRequestDispatcher("/controller").forward(request, response);

        } else {
            
            request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
        }
        

    }

}
