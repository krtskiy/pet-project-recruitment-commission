package com.epam.koretskyi.commission.web.command;

import com.epam.koretskyi.commission.util.constant.Path;
import com.epam.koretskyi.commission.db.DBManager;
import com.epam.koretskyi.commission.util.MD5Util;
import com.epam.koretskyi.commission.db.Role;
import com.epam.koretskyi.commission.db.UserStatus;
import com.epam.koretskyi.commission.db.entity.User;
import com.epam.koretskyi.commission.exception.AppException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author D.Koretskyi on 23.09.2020.
 */
public class LoginCommand extends Command {
    private static final long serialVersionUID = -5619056046383841363L;

    private static final Logger LOG = Logger.getLogger(LoginCommand.class);


    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        LOG.debug("Command starts");

        HttpSession httpSession = request.getSession();

        // obtain email and password from a request
        DBManager dbManager = DBManager.getInstance();
        String email = request.getParameter("email");
        LOG.trace("Request parameter: email --> " + email);

        String password = request.getParameter("password");

        if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
            throw new AppException("Email/password can not be empty");
        }

        User user = dbManager.findUserByEmail(email);
        LOG.trace("Found in DB: user --> " + user);

        if (user == null || !MD5Util.md5Apache(password).equals(user.getPassword())) {
            throw new AppException("Cannot find user with such email/password");
        }

        Role userRole = Role.getRole(user);
        LOG.trace("userRole --> " + userRole);


        String forward = Path.PAGE_ERROR;
        if (userRole == Role.ADMIN) {
            forward = Path.COMMAND_ADMIN_PANEL;
        }

        if (userRole == Role.USER) {
            forward = Path.PAGE_WELCOME;
        }

        UserStatus userStatus = UserStatus.getStatus(user);
        LOG.trace("userStatus --> " + userStatus);

        httpSession.setAttribute("user", user);
        LOG.trace("Set the session attribute: user --> " + user);

        httpSession.setAttribute("userRole", userRole);
        LOG.trace("Set the session attribute: userRole --> " + userRole);

        httpSession.setAttribute("userStatus", userStatus);
        LOG.trace("Set the session attribute: userStatus --> " + userStatus);

        LOG.info("User " + user + " logged as " + userRole.toString().toLowerCase());

        if (userStatus == UserStatus.BLOCKED) {
            forward = Path.COMMAND_BLOCKED_USER;
        }

        LOG.debug("Command finished");
        return forward;
    }
}
