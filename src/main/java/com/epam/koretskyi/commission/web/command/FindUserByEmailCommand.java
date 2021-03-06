package com.epam.koretskyi.commission.web.command;

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
 * Command for search for a user by email on the user management page in admin panel.
 *
 * @author D.Koretskyi on 07.10.2020.
 */
public class FindUserByEmailCommand extends Command {
    private static final long serialVersionUID = -1533986122206259365L;

    private static final Logger LOG = Logger.getLogger(FindUserByEmailCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Commands starts");

        HttpSession session = request.getSession();

        String page = (String) session.getAttribute("page");
        String sort = (String) session.getAttribute("sort");

        if (page == null) {
            page = request.getParameter("page");
        }
        if (sort == null) {
            sort = request.getParameter("sort");
        }

        DBManager manager = DBManager.getInstance();
        String email = request.getParameter("email");
        LOG.trace("Request parameter: email --> " + email);
        User foundUser = manager.findUserByEmail(email);
        LOG.trace("Found in DB: findUser --> " + foundUser);
        session.setAttribute("foundUser", foundUser);
        LOG.trace("Set the session attribute: foundUser --> " + foundUser);

        if (foundUser != null) {
            String status = manager.findUserStatusById(foundUser.getStatusId());
            session.setAttribute("userStatus", status);
            LOG.trace("Set the session attribute: userStatus --> " + status);
        }

        if (foundUser == null && email != null) {
            String notFoundMessage = "There is no such user!";
            session.setAttribute("notFoundMessage", notFoundMessage);
            LOG.trace("Set the session attribute: notFoundMessage --> " + notFoundMessage);
        }

        LOG.debug("Commands finished");
        return Path.COMMAND_USERS_WITH_PARAMS + "&page=" + page + "&sort=" + sort;
    }
}
