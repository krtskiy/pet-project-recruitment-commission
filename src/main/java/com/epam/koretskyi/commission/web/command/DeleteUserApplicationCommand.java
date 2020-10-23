package com.epam.koretskyi.commission.web.command;

import com.epam.koretskyi.commission.db.DBManager;
import com.epam.koretskyi.commission.db.Role;
import com.epam.koretskyi.commission.db.entity.User;
import com.epam.koretskyi.commission.exception.AppException;
import com.epam.koretskyi.commission.util.constant.Path;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Command for deleting a user-submitted application for faculty.
 * Can be called both by admin and user.
 * Redirects back to user's profile if invoked by admin.
 * Redirects back to private office if invoked by user.
 *
 * @author D.Koretskyi on 14.10.2020.
 */
public class DeleteUserApplicationCommand extends Command {
    private static final long serialVersionUID = 5435924656246717772L;

    private static final Logger LOG = Logger.getLogger(DeleteUserApplicationCommand.class);


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        int facultyId = Integer.parseInt(request.getParameter("facultyId"));
        LOG.trace("Request parameter: facultyId --> " + facultyId);

        String userIdStr = request.getParameter("userId");
        int userId;
        if (userIdStr != null) {
            userId = Integer.parseInt(userIdStr);
            LOG.trace("Current user id --> " + userId);
        } else {
            userId = user.getId();
            LOG.trace("Current user id --> " + userId);
        }

        DBManager.getInstance().deleteUserApplication(userId, facultyId);

        String successAppDeleteMessage = "Application deleted successfully";
        request.setAttribute("successAppDeleteMessage", successAppDeleteMessage);
        LOG.trace("Set the session attribute: successAppDeleteMessage --> " + successAppDeleteMessage);

        Role userRole = Role.getRole(user);
        if (userRole == Role.ADMIN) {
            LOG.debug("Command finished");
            return Path.COMMAND_USER_PROFILE;
        }

        LOG.debug("Command finished");
        return Path.COMMAND_PRIVATE_OFFICE;
    }
}
