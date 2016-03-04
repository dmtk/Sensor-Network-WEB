package com.github.dmtk;

import com.github.dmtk.entity.SiteUser;
import com.github.dmtk.entity.UserFacadeLocal;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author dmytro
 */
public class LoginAction implements Action {

    private UserFacadeLocal userFacadeLocal;

    public LoginAction() {
        try {

            InitialContext ic = new InitialContext();
            userFacadeLocal = (UserFacadeLocal) ic.lookup("java:comp/env/User");

        } catch (NamingException ex) {

        }
    }

    @Override
    public String perform(HttpServletRequest request, HttpServletResponse response) {

        SiteUser user = userFacadeLocal.find(request.getParameter("login").trim().toLowerCase());
        if (user != null && request.getParameter("password").equals(user.getPassword())) {
            String login = (String) request.getParameter("login");
            String password = (String) request.getParameter("password");
            boolean authenticated = true;
            HttpSession session = request.getSession();
            session.setAttribute("user", login.substring(0, login.lastIndexOf("@")));
            session.setAttribute("password", password);
            session.setAttribute("authenticated", authenticated);
            return new OverviewAction().perform(request, response);

        } else {

            return new LoginAction().perform(request, response);

        }

    }

}
