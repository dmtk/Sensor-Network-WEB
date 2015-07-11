package com.github.dmtk;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dmytro
 */
class ExportAction implements Action {

    @Override
    public String perform(HttpServletRequest request, HttpServletResponse response) {
        return "/jsp/export.jsp";
    }
}
