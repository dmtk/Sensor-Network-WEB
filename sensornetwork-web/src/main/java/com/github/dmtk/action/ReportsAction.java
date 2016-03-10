package com.github.dmtk.action;

import com.github.dmtk.dao.NetworkEventDAOLocal;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReportsAction implements Action {

    NetworkEventDAOLocal networkEventFacade;

    public ReportsAction() {

        try {

            InitialContext ic = new InitialContext();
            networkEventFacade = (NetworkEventDAOLocal) ic.lookup("java:comp/env/NetworkEventFacade");

        } catch (NamingException ex) {

        }
    }

    @Override
    public String perform(HttpServletRequest request, HttpServletResponse response) {

        int itemsPerWebPage = 20;
        request.setAttribute("events", networkEventFacade.findByDate(itemsPerWebPage));
        return "/jsp/reports.jsp";
    }
}
