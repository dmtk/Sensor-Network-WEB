package com.github.dmtk.action;

import com.github.dmtk.entity.SiteUser;
import com.github.dmtk.dao.UserDAOLocal;
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

    private UserDAOLocal userFacadeLocal;

    public LoginAction() {
        try {

            InitialContext ic = new InitialContext();
            userFacadeLocal = (UserDAOLocal) ic.lookup("java:comp/env/User");

        } catch (NamingException ex) {

        }
    }

    @Override
    public String perform(HttpServletRequest request, HttpServletResponse response) {

        if (request.getParameter("login") != null) {
            
            String login = (String) request.getParameter("login").trim().toLowerCase();
            SiteUser user = userFacadeLocal.find(login);
            if (user != null && request.getParameter("password").equals(user.getPassword())) {
                
                String password = (String) request.getParameter("password");
                boolean authenticated = true;
                HttpSession session = request.getSession();
                session.setAttribute("user", login.substring(0, login.lastIndexOf("@")));
                session.setAttribute("password", password);
                session.setAttribute("authenticated", authenticated);
                return new OverviewAction().perform(request, response);

            }else if("guest".equals(login)){
                
                boolean authenticated = true;
                HttpSession session = request.getSession();
                session.setAttribute("user", "guest");
                session.setAttribute("authenticated", authenticated);
                return new OverviewAction().perform(request, response);
            }else{
                request.setAttribute("error", "Wrong username/password");
            }
        }

        return new BootstrapAction().perform(request, response);

    }

}
