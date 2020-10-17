package com.epam.koretskyi.commission.web.command;

import com.epam.koretskyi.commission.db.DBManager;
import com.epam.koretskyi.commission.db.bean.UserFacultiesBean;
import com.epam.koretskyi.commission.db.bean.UserMarksBean;
import com.epam.koretskyi.commission.db.entity.User;
import com.epam.koretskyi.commission.exception.AppException;
import com.epam.koretskyi.commission.util.constant.Path;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Command to forward admin to user profile.
 *
 * @author D.Koretskyi on 14.10.2020.
 */
public class UserProfilePageCommand extends Command {
    private static final long serialVersionUID = 5012539800450000478L;

    private static final Logger LOG = Logger.getLogger(UserProfilePageCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");

        DBManager manager = DBManager.getInstance();

        int userId = Integer.parseInt(request.getParameter("userId"));

        List<UserMarksBean> userMarks = manager.findUserMarksByUserId(userId);
        request.setAttribute("userMarks", userMarks);
        LOG.trace("Set the request attribute: userMarks --> " + userMarks);

        List<UserFacultiesBean> userFaculties = manager.findUserFacultiesByUserId(userId);
        request.setAttribute("userFaculties", userFaculties);
        LOG.trace("Set the request attribute: userFaculties --> " + userFaculties);

        User profileOwner = manager.findUserById(userId);
        request.setAttribute("profileOwner", profileOwner);
        LOG.trace("Set the request attribute: profileOwner --> " + profileOwner);

        LOG.debug("Command finished");
        return Path.PAGE_USER_PROFILE;
    }
}
