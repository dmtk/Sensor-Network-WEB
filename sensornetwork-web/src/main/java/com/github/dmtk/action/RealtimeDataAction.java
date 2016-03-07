package com.github.dmtk.action;

import com.github.dmtk.logic.NetworkController;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dmytro
 */
public class RealtimeDataAction implements Action {

    NetworkController controller;

    public RealtimeDataAction() {
        
        try {
        
            InitialContext ic = new InitialContext();
            controller = (NetworkController) ic.lookup("java:comp/env/NetworkController");

        } catch (NamingException ex) {
            
        }
    }

    @Override
    public String perform(HttpServletRequest request, HttpServletResponse response) {

        return "/jsp/rawdata.jsp";
    }
}
