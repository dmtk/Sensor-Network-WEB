package com.github.dmtk;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SettingsAction implements Action{
    
    
    @Override
    public String perform(HttpServletRequest request, HttpServletResponse response) {

        return "/jsp/settings.jsp";
    }
}
