package com.epam.koretskyi.commission.web.command;

import com.epam.koretskyi.commission.util.constant.Path;
import com.epam.koretskyi.commission.db.DBManager;
import com.epam.koretskyi.commission.db.entity.Faculty;
import com.epam.koretskyi.commission.exception.AppException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Forwards to faculty application page.
 *
 * @author D.Koretskyi on 11.10.2020.
 */
public class RegisterForFacultyPageCommand extends Command {
    private static final long serialVersionUID = 4917912591270427200L;

    private static final Logger LOG = Logger.getLogger(RegisterForFacultyPageCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");

        String facultyId = request.getParameter("facultyId");
        LOG.trace("Request parameter: Id --> " + facultyId);

        Faculty faculty = DBManager.getInstance().findFacultyById(Integer.parseInt(facultyId));
        request.getSession().setAttribute("faculty", faculty);
        LOG.trace("Set the session attribute: faculty --> " + faculty);

        LOG.debug("Command finished");
        return Path.PAGE_FACULTY_REGISTER;
    }
}
