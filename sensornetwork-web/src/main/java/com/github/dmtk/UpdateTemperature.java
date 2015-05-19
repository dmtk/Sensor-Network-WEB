package com.github.dmtk;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/update"})
public class UpdateTemperature extends HttpServlet {

    @EJB
    private ComPortFacade comport;

    @Override
    public void init() {
        comport.emulate();
    }

    public UpdateTemperature() {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Map<String, Object> map = new HashMap<>();
        boolean isValid = false;
        String temperature = request.getParameter("temperature");
        isValid = true;
        temperature = comport.getData();
        map.put("temperature", temperature);
        map.put("isValid", isValid);
        write(response, map);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    private void write(HttpServletResponse response, Map<String, Object> map) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new Gson().toJson(map));
    }

}
