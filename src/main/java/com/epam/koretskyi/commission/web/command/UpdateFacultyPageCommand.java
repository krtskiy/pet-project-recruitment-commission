package com.epam.koretskyi.commission.web.command;

import com.epam.koretskyi.commission.constant.Path;
import com.epam.koretskyi.commission.db.DBManager;
import com.epam.koretskyi.commission.db.entity.Faculty;
import com.epam.koretskyi.commission.exception.AppException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author D.Koretskyi on 08.10.2020.
 */
public class UpdateFacultyPageCommand extends Command {
    private static final long serialVersionUID = -5118379833410184046L;

    private static final Logger LOG = Logger.getLogger(UpdateFacultyPageCommand.class);


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");

        HttpSession session = request.getSession();

        String facultyId = request.getParameter("facultyId");
        LOG.trace("Request parameter: Id --> " + facultyId);

        Faculty faculty = DBManager.getInstance().findFacultyById(Integer.parseInt(facultyId));
        session.setAttribute("faculty", faculty);

        LOG.debug("Command finished");
        return Path.PAGE_UPDATE_FACULTY;
    }
}
