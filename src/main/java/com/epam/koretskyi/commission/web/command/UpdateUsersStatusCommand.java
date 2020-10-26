package com.epam.koretskyi.commission.web.command;

import com.epam.koretskyi.commission.db.UserStatus;
import com.epam.koretskyi.commission.util.constant.Path;
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
 * Invoked when admin changes lock status of user.
 *
 * @author D.Koretskyi on 07.10.2020.
 */
public class UpdateUsersStatusCommand extends Command {
    private static final long serialVersionUID = 8488995954443266809L;

    private static final Logger LOG = Logger.getLogger(UpdateUsersStatusCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");

        HttpSession session = request.getSession();
        DBManager manager = DBManager.getInstance();

        int status = Integer.parseInt(request.getParameter("status"));
        LOG.trace("Request parameter: status --> " + status);

        String page = request.getParameter("page");
        session.setAttribute("page", page);
        String sort = request.getParameter("sort");
        session.setAttribute("sort", sort);

        User foundUser = (User) session.getAttribute("foundUser");

        String path = Path.PAGE_ERROR;
        if (request.getParameter("userId") != null) {
            int userId = Integer.parseInt(request.getParameter("userId"));
            manager.updateUserStatus(status, userId);
            if (UserStatus.values()[status] == UserStatus.BLOCKED) {
                manager.deleteAllUserApplications(userId);
            }
            if (foundUser != null) {
                path = Path.COMMAND_FIND_USER_BY_EMAIL + "&email=" + foundUser.getEmail();
            } else {
                path = Path.COMMAND_USERS + "&page=" + page + "&sort=" + sort;
            }
        }

        if (foundUser != null && request.getParameter("userId") == null) {
            manager.updateUserStatus(status, foundUser.getId());
            if (UserStatus.values()[status] == UserStatus.BLOCKED) {
                manager.deleteAllUserApplications(foundUser.getId());
            }
            path = Path.COMMAND_FIND_USER_BY_EMAIL + "&email=" + foundUser.getEmail();
        }

        if (request.getParameter("profileOwnerId") != null) {
            int profileOwnerId = Integer.parseInt(request.getParameter("profileOwnerId"));
            manager.updateUserStatus(status, profileOwnerId);
            if (UserStatus.values()[status] == UserStatus.BLOCKED) {
                manager.deleteAllUserApplications(profileOwnerId);
            }
            path = Path.COMMAND_USER_PROFILE + "&userId=" + profileOwnerId;
        }

        LOG.debug("Command finished");
        return path;
    }
}
