package com.github.dmtk.action;

import com.github.dmtk.dao.NetworkEventDAOLocal;
import com.github.dmtk.utils.ExcelExport;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dmytro
 */
class ExportAction implements Action {

    NetworkEventDAOLocal networkEventFacade;

    public ExportAction() {

        try {

            InitialContext ic = new InitialContext();
            networkEventFacade = (NetworkEventDAOLocal) ic.lookup("java:comp/env/NetworkEventFacade");

        } catch (NamingException ex) {

        }
    }

    @Override
    public String perform(HttpServletRequest request, HttpServletResponse response) {

        String action = request.getParameter("action");
        if ("results-to-excel".equals(action)) {
            try {
                File exelFile = new ExcelExport().exportExperiments(networkEventFacade.findByDate(1000));
                sendFile("experiments.xls", exelFile, response);
            } catch (ServletException | IOException ex) {
                Logger.getLogger(ExportAction.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return "/jsp/export.jsp";

    }

    private void sendFile(String fileName, File excelFile, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment; filename=" + fileName);
        OutputStream out = response.getOutputStream();
        FileInputStream in = new FileInputStream(excelFile);
        byte[] buffer = new byte[4096];
        int length;
        while ((length = in.read(buffer)) > 0) {
            out.write(buffer, 0, length);
        }
        in.close();
        out.flush();
        excelFile.delete();
    }
}
