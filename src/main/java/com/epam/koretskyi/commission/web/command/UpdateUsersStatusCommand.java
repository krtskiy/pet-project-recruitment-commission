package com.epam.koretskyi.commission.web.command;

import com.epam.koretskyi.commission.constant.Path;
import com.epam.koretskyi.commission.db.DBManager;
import com.epam.koretskyi.commission.db.entity.User;
import com.epam.koretskyi.commission.exception.AppException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author D.Koretskyi on 07.10.2020.
 */
public class UpdateUsersStatusCommand extends Command {
    private static final long serialVersionUID = 8488995954443266809L;

    private static final Logger LOG = Logger.getLogger(UpdateUsersStatusCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");
        String status = request.getParameter("status");
        LOG.trace("Request parameter: status --> " + status);

        HttpSession session = request.getSession();
        User foundUser = (User) session.getAttribute("foundUser");

        if (request.getParameter("userId") != null) {
            int userId = Integer.parseInt(request.getParameter("userId"));
            DBManager.getInstance().updateUserStatus(Integer.parseInt(status), userId);
            if (foundUser != null) {
                return Path.COMMAND_FIND_USER_BY_EMAIL + "&email=" + foundUser.getEmail();
            }
            return Path.COMMAND_FIND_USER_BY_EMAIL;
        }

        int userId = foundUser.getId();
        DBManager.getInstance().updateUserStatus(Integer.parseInt(status), userId);

        LOG.debug("Command finished");
        return Path.COMMAND_FIND_USER_BY_EMAIL + "&email=" + foundUser.getEmail();
    }
}
