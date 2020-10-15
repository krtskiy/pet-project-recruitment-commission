package com.epam.koretskyi.commission.web.command;

import com.epam.koretskyi.commission.db.DBManager;
import com.epam.koretskyi.commission.db.entity.Faculty;
import com.epam.koretskyi.commission.exception.AppException;
import com.epam.koretskyi.commission.util.constant.Path;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author D.Koretskyi on 15.10.2020.
 */
public class CloseFacultyRecruitmentCommand extends Command {
    private static final long serialVersionUID = -1567150592844709847L;

    private static final Logger LOG = Logger.getLogger(CloseFacultyRecruitmentCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");

        DBManager manager = DBManager.getInstance();

        int statusId = Integer.parseInt(request.getParameter("status"));
        LOG.trace("Request parameter: status --> " + statusId);

        int facultyId = Integer.parseInt(request.getParameter("facultyId"));
        LOG.trace("Request parameter: facultyId --> " + facultyId);

        Faculty faculty = manager.findFacultyById(facultyId);
        faculty.setStatusId(statusId);

        manager.updateFaculty(faculty);

        String successFacCloseMessage = "Faculty recruitment was closed successfully";
        request.getSession().setAttribute("successFacCloseMessage", successFacCloseMessage);
        LOG.trace("Set the session attribute: successFacCloseMessage --> " + successFacCloseMessage);

        LOG.debug("Command finished");
        return Path.COMMAND_VIEW_REPORT_SHEET + "&facultyId=" + facultyId;
    }

}
