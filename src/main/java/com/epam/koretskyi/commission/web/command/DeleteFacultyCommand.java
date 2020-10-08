package com.epam.koretskyi.commission.web.command;

import com.epam.koretskyi.commission.constant.Path;
import com.epam.koretskyi.commission.db.DBManager;
import com.epam.koretskyi.commission.exception.AppException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author D.Koretskyi on 08.10.2020.
 */
public class DeleteFacultyCommand extends Command {
    private static final long serialVersionUID = -5185350998986377634L;

    private static final Logger LOG = Logger.getLogger(DeleteFacultyCommand.class);


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");

        String facultyId = request.getParameter("facultyId");
        LOG.trace("Request parameter: Id --> " + facultyId);

        DBManager.getInstance().deleteFaculty(Integer.parseInt(facultyId));

        return Path.COMMAND_FACULTIES;
    }
}
