package com.epam.koretskyi.commission.web.command;

import com.epam.koretskyi.commission.util.constant.Path;
import com.epam.koretskyi.commission.db.DBManager;
import com.epam.koretskyi.commission.db.entity.Criterion;
import com.epam.koretskyi.commission.db.entity.Faculty;
import com.epam.koretskyi.commission.exception.AppException;
import com.epam.koretskyi.commission.util.validation.FacultyValidation;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Invoked when admin adds a new faculty.
 *
 * @author D.Koretskyi on 08.10.2020.
 */
public class CreateNewFacultyCommand extends Command {
    private static final long serialVersionUID = 6954630965117330307L;

    private static final Logger LOG = Logger.getLogger(CreateNewFacultyCommand.class);


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");

        Faculty faculty = new Faculty();

        faculty.setId(Integer.parseInt(request.getParameter("id")));
        LOG.trace("Request parameter: id --> " + faculty.getId());
        faculty.setNameEn(request.getParameter("nameEn"));
        LOG.trace("Request parameter: nameEn --> " + faculty.getNameEn());
        faculty.setNameUk(request.getParameter("nameUk"));
        LOG.trace("Request parameter: nameUk --> " + faculty.getNameUk());
        faculty.setTotalSeats(Integer.parseInt(request.getParameter("totalSeats")));
        LOG.trace("Request parameter: totalSeats --> " + faculty.getTotalSeats());
        faculty.setBudgetSeats(Integer.parseInt(request.getParameter("budgetSeats")));
        LOG.trace("Request parameter: budgetSeats --> " + faculty.getBudgetSeats());

        String[] criteriaIdArr = request.getParameterValues("criterionId");
        List<Criterion> criteria = new ArrayList<>();
        if (criteriaIdArr != null) {
            for (String criteriaId : criteriaIdArr) {
                criteria.add(DBManager.getInstance().findCriterionById(Integer.parseInt(criteriaId)));
            }
        }

        if (!FacultyValidation.validateFacultyCriteria(criteria)) {
            throw new AppException("The faculty must have from 1 to 4 criteria");
        }
        faculty.setCriteria(criteria);
        LOG.trace("Request parameter: faculty criteria --> " + criteria);

        if (FacultyValidation.validateFacultyFieldsIsEmpty(faculty)) {
            throw new AppException("Fields can not be empty!");
        }
        if (!FacultyValidation.validateFacultySeats(faculty)) {
            throw new AppException("The number of budget places cannot exceed the total number of places!");
        }

        DBManager.getInstance().insertFaculty(faculty);

        String successNewFacMessage = "Faculty registered successfully";
        request.getSession().setAttribute("successNewFacMessage", successNewFacMessage);
        LOG.trace("Set the session attribute: successNewFacMessage --> " + successNewFacMessage);

        LOG.debug("Command finished");
        return Path.COMMAND_FACULTIES;
    }
}
