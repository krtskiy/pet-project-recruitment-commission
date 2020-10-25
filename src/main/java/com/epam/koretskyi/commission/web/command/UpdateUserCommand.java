package com.epam.koretskyi.commission.web.command;

import com.epam.koretskyi.commission.util.constant.Path;
import com.epam.koretskyi.commission.db.DBManager;
import com.epam.koretskyi.commission.util.MD5Util;
import com.epam.koretskyi.commission.db.entity.User;
import com.epam.koretskyi.commission.exception.AppException;
import com.epam.koretskyi.commission.util.validation.UserValidation;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Invoked when user changes his email or password.
 *
 * @author D.Koretskyi on 06.10.2020.
 */
public class UpdateUserCommand extends Command {
    private static final long serialVersionUID = 8915993138465695042L;

    private static final Logger LOG = Logger.getLogger(UpdateUserCommand.class);


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");

        String newEmail = request.getParameter("email");
        LOG.trace("Request parameter: email --> " + newEmail);

        String newPassword = request.getParameter("password");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (DBManager.getInstance().findUserByEmail(newEmail) != null) {
            throw new AppException("This email is already taken!");
        }

        if (!StringUtils.isBlank(newEmail)) {
            user.setEmail(newEmail);
        }

        if (!StringUtils.isBlank(newPassword)) {
            if (!UserValidation.validateUserPassword(newPassword)) {
                throw new AppException("Password must be between 6 and 32 characters!");
            }
            if (UserValidation.isPasswordSame(user, newPassword)) {
                throw new AppException("You entered your old password!");
            }
            user.setPassword(MD5Util.md5Apache(newPassword));
        }

        DBManager.getInstance().updateUser(user);
        session.setAttribute("user", user);
        LOG.trace("Set the request attribute: user --> " + user);

        if (!StringUtils.isBlank(newEmail) || !StringUtils.isBlank(newPassword)) {
            String successMessage = "Login data changed successfully";
            session.setAttribute("successMessage", successMessage);
            LOG.trace("Set the session attribute: successMessage --> " + successMessage);
        }

        LOG.debug("Command finished");
        return Path.COMMAND_PRIVATE_OFFICE;
    }
}
