package com.epam.koretskyi.commission.web.command;

import com.epam.koretskyi.commission.constant.Path;
import com.epam.koretskyi.commission.db.DBManager;
import com.epam.koretskyi.commission.db.entity.*;
import com.epam.koretskyi.commission.exception.AppException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author D.Koretskyi on 01.10.2020.
 */
public class PrivateOfficePageCommand extends Command {
    private static final long serialVersionUID = -4290326623176370114L;

    private static final Logger LOG = Logger.getLogger(PrivateOfficePageCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");

        DBManager manager = DBManager.getInstance();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        List<UserMark> userMarks = manager.findUserMarksByUserId(user.getId());
        request.setAttribute("userMarks", userMarks);
        LOG.trace("Set the request attribute: userMarks --> " + userMarks);

        List<Criterion> criteria = manager.findAllCriteria();
        request.setAttribute("criteria", criteria);
        LOG.trace("Set the request attribute: criteria --> " + criteria);

        List<Application> applications = manager.findUserApplications(user.getId());
        request.setAttribute("applications", applications);
        LOG.trace("Set the request attribute: applications --> " + applications);

        List<Faculty> faculties = manager.findAllFaculties();
        request.setAttribute("faculties", faculties);
        LOG.trace("Set the request attribute: faculties --> " + faculties);

        LOG.debug("Command finished");
        return Path.PAGE_PRIVATE_OFFICE;
    }
}
