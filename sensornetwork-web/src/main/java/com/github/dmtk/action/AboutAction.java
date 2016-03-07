package com.github.dmtk.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AboutAction implements Action {

      
    @Override
    public String perform(HttpServletRequest request, HttpServletResponse response) {

        return "/jsp/about.jsp";
    }
}
