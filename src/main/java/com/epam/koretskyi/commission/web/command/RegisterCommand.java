package com.epam.koretskyi.commission.web.command;

import com.epam.koretskyi.commission.constant.Path;
import com.epam.koretskyi.commission.db.DBManager;
import com.epam.koretskyi.commission.db.MD5Util;
import com.epam.koretskyi.commission.db.Role;
import com.epam.koretskyi.commission.db.entity.User;
import com.epam.koretskyi.commission.exception.AppException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author D.Koretskyi on 23.09.2020.
 */
public class RegisterCommand extends Command {
    private static final long serialVersionUID = -2318239355277653587L;

    private static final Logger LOG = Logger.getLogger(RegisterCommand.class);

    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        LOG.debug("Command starts");
        User user = new User();

        String email = request.getParameter("email");
        user.setEmail(email);
        LOG.trace("Request parameter: email --> " + email);

        String password = MD5Util.md5Apache(request.getParameter("password"));
        user.setPassword(password);
        LOG.trace("Request parameter: password --> " + password);

        String name = request.getParameter("name");
        user.setName(name);
        LOG.trace("Request parameter: name --> " + name);

        String surname = request.getParameter("surname");
        user.setSurname(surname);
        LOG.trace("Request parameter: surname --> " + surname);

        String patronymic = request.getParameter("patronymic");
        user.setPatronymic(patronymic);
        LOG.trace("Request parameter: patronymic --> " + patronymic);

        String region = request.getParameter("region");
        user.setRegion(region);
        LOG.trace("Request parameter: region --> " + region);

        String city = request.getParameter("city");
        user.setCity(city);
        LOG.trace("Request parameter: city --> " + city);

        String institutionName = request.getParameter("institutionName");
        user.setInstitutionName(institutionName);
        LOG.trace("Request parameter: institution name --> " + institutionName);

        if (email.equals("") || password.equals("") || name.equals("") || surname.equals("") ||
                patronymic.equals("") || region.equals("") ||
                city.equals("") || institutionName.equals("")) {
            throw new AppException("Fields can not be empty!");
        }

        DBManager.getInstance().insertUser(user);

        HttpSession session = request.getSession();
        Role userRole = (Role) session.getAttribute("userRole");

        String successRegMessage = "Registered successfully";
        session.setAttribute("successRegMessage", successRegMessage);
        LOG.trace("Set the session attribute: successRegMessage --> " + successRegMessage);

        if (userRole == Role.ADMIN) {
            LOG.debug("Command finished");
            return Path.COMMAND_USERS;
        }

        LOG.debug("Command finished");
        return Path.COMMAND_LOGIN_PAGE;
    }
}
