package com.epam.koretskyi.commission.web.command;

import com.epam.koretskyi.commission.constant.Path;
import com.epam.koretskyi.commission.db.DBManager;
import com.epam.koretskyi.commission.db.MD5Util;
import com.epam.koretskyi.commission.db.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author D.Koretskyi on 23.09.2020.
 */
public class RegisterCommand extends Command {
    private static final long serialVersionUID = -2318239355277653587L;

    private static final Logger LOG = Logger.getLogger(RegisterCommand.class);

    public String execute(HttpServletRequest request, HttpServletResponse response) {
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

        DBManager.getInstance().insertUser(user);

        LOG.debug("Command finished");
        return Path.PAGE_LOGIN;
    }
}
