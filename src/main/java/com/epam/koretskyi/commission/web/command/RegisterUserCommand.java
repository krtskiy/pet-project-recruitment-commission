package com.epam.koretskyi.commission.web.command;

import com.epam.koretskyi.commission.util.constant.Path;
import com.epam.koretskyi.commission.db.DBManager;
import com.epam.koretskyi.commission.util.MD5Util;
import com.epam.koretskyi.commission.db.Role;
import com.epam.koretskyi.commission.db.entity.User;
import com.epam.koretskyi.commission.exception.AppException;
import com.epam.koretskyi.commission.util.validation.UserValidation;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Invoked when client registers in system.
 *
 * @author D.Koretskyi on 23.09.2020.
 */
public class RegisterUserCommand extends Command {
    private static final long serialVersionUID = -2318239355277653587L;

    private static final Logger LOG = Logger.getLogger(RegisterUserCommand.class);

    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        LOG.debug("Command starts");
        User user = new User();

        String email = request.getParameter("email");
        User checkEmail = DBManager.getInstance().findUserByEmail(email);
        if (checkEmail != null) {
            throw new AppException("This email already registered!");
        }
        user.setEmail(email);
        LOG.trace("Request parameter: email --> " + email);

        String password = request.getParameter("password");
        if (!UserValidation.validateUserPassword(password)) {
            throw new AppException("Password must be between 6 and 32 characters!");
        }
        user.setPassword(MD5Util.md5Apache(password));

        user.setName(request.getParameter("name"));
        LOG.trace("Request parameter: name --> " + user.getName());
        user.setSurname(request.getParameter("surname"));
        LOG.trace("Request parameter: surname --> " + user.getSurname());
        user.setPatronymic(request.getParameter("patronymic"));
        LOG.trace("Request parameter: patronymic --> " + user.getPatronymic());
        user.setRegion(request.getParameter("region"));
        LOG.trace("Request parameter: region --> " + user.getRegion());
        user.setCity(request.getParameter("city"));
        LOG.trace("Request parameter: city --> " + user.getCity());
        user.setInstitutionName(request.getParameter("institutionName"));
        LOG.trace("Request parameter: institutionName --> " + user.getInstitutionName());

        String roleId = request.getParameter("roleId");
        if (roleId == null) {
            user.setRoleId(0);
            LOG.trace("New user role id -->  " + 0);
        } else {
            user.setRoleId(Integer.parseInt(roleId));
            LOG.trace("New user role id -->  " + roleId);
        }

        if (UserValidation.isFieldsBlank(user)) {
            throw new AppException("Fields can not be empty!");
        }

        DBManager.getInstance().insertUser(user);

        HttpSession session = request.getSession();
        Role userRole = (Role) session.getAttribute("userRole");

        String successRegMessage = "Registered successfully";
        session.setAttribute("successRegMessage", successRegMessage);
        LOG.trace("Set the session attribute: successRegMessage --> " + successRegMessage);

        String path = Path.COMMAND_LOGIN_PAGE;
        if (userRole == Role.ADMIN) {
            LOG.debug("Command finished");
            path = Path.COMMAND_USERS + "&page=1" + "&sort=id";
        }

        LOG.debug("Command finished");
        return path;
    }
}
