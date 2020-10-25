package com.epam.koretskyi.commission.web.command;

import com.epam.koretskyi.commission.util.constant.Path;
import com.epam.koretskyi.commission.db.DBManager;
import com.epam.koretskyi.commission.db.entity.Criterion;
import com.epam.koretskyi.commission.db.entity.Faculty;
import com.epam.koretskyi.commission.exception.AppException;
import com.epam.koretskyi.commission.util.validation.FacultyValidation;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Invoked when admin changes faculty.
 *
 * @author D.Koretskyi on 08.10.2020.
 */
public class UpdateFacultyCommand extends Command {
    private static final long serialVersionUID = 1730507812063668683L;

    private static final Logger LOG = Logger.getLogger(UpdateFacultyCommand.class);


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");

        String newNameEn = request.getParameter("nameEn");
        LOG.trace("Request parameter: nameEn --> " + newNameEn);
        String newNameUk = request.getParameter("nameUk");
        LOG.trace("Request parameter: nameUk --> " + newNameUk);
        String newTotalSeats = request.getParameter("totalSeats");
        LOG.trace("Request parameter: totalSeats --> " + newTotalSeats);
        String newBudgetSeats = request.getParameter("budgetSeats");
        LOG.trace("Request parameter: budgetSeats --> " + newBudgetSeats);

        int facultyId = Integer.parseInt(request.getParameter("facultyId"));
        Faculty faculty = DBManager.getInstance().findFacultyById(facultyId);

        String[] criteriaIdArr = request.getParameterValues("criterionId");
        if (criteriaIdArr != null) {
            List<Criterion> criteria = new ArrayList<>();
            for (String criteriaId : criteriaIdArr) {
                criteria.add(DBManager.getInstance().findCriterionById(Integer.parseInt(criteriaId)));
            }

            if (!FacultyValidation.validateFacultyCriteria(criteria)) {
                throw new AppException("The faculty must have from 1 to 4 criteria");
            }

            faculty.setCriteria(criteria);
            LOG.trace("Request parameter: faculty criteria --> " + criteria);
        }

        if (!StringUtils.isBlank(newNameEn)) {
            faculty.setNameEn(newNameEn);
        }
        if (!StringUtils.isBlank(newNameUk)) {
            faculty.setNameUk(newNameUk);
        }
        if (!StringUtils.isBlank(newTotalSeats)) {
            faculty.setTotalSeats(Integer.parseInt(newTotalSeats));
        }
        if (!StringUtils.isBlank(newBudgetSeats)) {
            faculty.setBudgetSeats(Integer.parseInt(newBudgetSeats));
            if (!FacultyValidation.validateFacultySeats(faculty)) {
                throw new AppException("The number of budget places cannot exceed the total number of places!");
            }
        }
        DBManager.getInstance().updateFaculty(faculty);

        if (!StringUtils.isBlank(newNameEn) || !StringUtils.isBlank(newNameUk) || !StringUtils.isBlank(newTotalSeats)
                || !StringUtils.isBlank(newBudgetSeats) || criteriaIdArr != null)
        {
            String successFacEditMessage = "Faculty changed successfully";
            request.getSession().setAttribute("successFacEditMessage", successFacEditMessage);
            LOG.trace("Set the session attribute: successFacEditMessage --> " + successFacEditMessage);
        }

        LOG.debug("Command finished");
        return Path.COMMAND_FACULTIES;
    }
}
