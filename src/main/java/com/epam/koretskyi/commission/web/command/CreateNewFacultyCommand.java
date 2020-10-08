package com.epam.koretskyi.commission.web.command;

import com.epam.koretskyi.commission.constant.Path;
import com.epam.koretskyi.commission.db.DBManager;
import com.epam.koretskyi.commission.db.entity.Faculty;
import com.epam.koretskyi.commission.exception.AppException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author D.Koretskyi on 08.10.2020.
 */
public class CreateNewFacultyCommand extends Command {
    private static final long serialVersionUID = 6954630965117330307L;

    private static final Logger LOG = Logger.getLogger(CreateNewFacultyCommand.class);


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");

        Faculty faculty = new Faculty();

        String id = request.getParameter("id");
        faculty.setId(Integer.parseInt(id));
        LOG.trace("Request parameter: id --> " + id);

        String nameEn = request.getParameter("nameEn");
        faculty.setNameEn(nameEn);
        LOG.trace("Request parameter: nameEn --> " + nameEn);

        String nameUk = request.getParameter("nameUk");
        faculty.setNameUk(nameUk);
        LOG.trace("Request parameter: nameUk --> " + nameUk);

        String totalSeats = request.getParameter("totalSeats");
        faculty.setTotalSeats(Integer.parseInt(totalSeats));
        LOG.trace("Request parameter: totalSeats --> " + totalSeats);

        String budgetSeats = request.getParameter("budgetSeats");
        faculty.setBudgetSeats(Integer.parseInt(budgetSeats));
        LOG.trace("Request parameter: budgetSeats --> " + budgetSeats);

        if (id.equals("") || nameEn.equals("") || nameUk.equals("") ||
                totalSeats.equals("") || budgetSeats.equals(""))
        {
            throw new AppException("Fields can not be empty!");
        }

        if (Integer.parseInt(budgetSeats) > Integer.parseInt(totalSeats)) {
            throw new AppException("The number of budget places cannot exceed the total number of places!");
        }

        DBManager.getInstance().insertFaculty(faculty);

        LOG.debug("Command finished");
        return Path.COMMAND_FACULTIES;
    }
}
