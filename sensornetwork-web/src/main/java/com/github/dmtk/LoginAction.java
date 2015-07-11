package com.github.dmtk;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author dmytro
 */
public class LoginAction implements Action {

    @Override
    public String perform(HttpServletRequest request, HttpServletResponse response) {
        String login = (String) request.getParameter("login");
        String password = (String) request.getParameter("password");
        if ("admin@wsnet.me".equals(login)) {
            boolean authenticated = true;
            HttpSession session = request.getSession();
            session.setAttribute("user", login.substring(0, login.lastIndexOf("@")));
            session.setAttribute("password", password);
            session.setAttribute("authenticated", authenticated);
            return new OverviewAction().perform(request, response);
        } else {
            return new BootstrapAction().perform(request, response);
        }
    }

}
