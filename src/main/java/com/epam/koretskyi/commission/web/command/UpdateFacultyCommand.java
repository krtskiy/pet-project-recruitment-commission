package com.epam.koretskyi.commission.web.command;

import com.epam.koretskyi.commission.util.constant.Path;
import com.epam.koretskyi.commission.db.DBManager;
import com.epam.koretskyi.commission.db.entity.Criterion;
import com.epam.koretskyi.commission.db.entity.Faculty;
import com.epam.koretskyi.commission.exception.AppException;
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

        HttpSession session = request.getSession();
        Faculty faculty = (Faculty) session.getAttribute("faculty");

        String[] criteriaIdArr = request.getParameterValues("criterionId");
        if (criteriaIdArr != null) {
            List<Criterion> criteria = new ArrayList<>();
            for (String criteriaId : criteriaIdArr) {
                criteria.add(DBManager.getInstance().findCriterionById(Integer.parseInt(criteriaId)));
            }
            faculty.setCriteria(criteria);
            LOG.trace("Request parameter: faculty criteria --> " + criteria);
        }

        if (!newNameEn.equals("")) {
            faculty.setNameEn(newNameEn);
        }

        if (!newNameUk.equals("")) {
            faculty.setNameUk(newNameUk);
        }

        if (!newTotalSeats.equals("")) {
            faculty.setTotalSeats(Integer.parseInt(newTotalSeats));
        }

        if (!newBudgetSeats.equals("")) {
            if (Integer.parseInt(newBudgetSeats) > faculty.getTotalSeats()) {
                throw new AppException("The number of budget places cannot exceed the total number of places!");
            }
            faculty.setBudgetSeats(Integer.parseInt(newBudgetSeats));
        }

        DBManager.getInstance().updateFaculty(faculty);

        if (!newNameEn.equals("") || !newNameUk.equals("") || !newTotalSeats.equals("")
                || !newBudgetSeats.equals("") || criteriaIdArr != null)
        {
            String successFacEditMessage = "Faculty changed successfully";
            session.setAttribute("successFacEditMessage", successFacEditMessage);
            LOG.trace("Set the session attribute: successFacEditMessage --> " + successFacEditMessage);
        }

        LOG.debug("Command finished");
        return Path.COMMAND_FACULTIES;
    }
}
