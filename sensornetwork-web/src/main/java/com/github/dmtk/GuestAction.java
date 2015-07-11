package com.github.dmtk;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

class GuestAction implements Action {

    @Override
    public String perform(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        boolean authenticated = true;
        session.setAttribute("user", "Guest");
        session.setAttribute("password", "");
        session.setAttribute("authenticated", authenticated);
        return new OverviewAction().perform(request, response);

    }
}
