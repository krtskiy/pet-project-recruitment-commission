package com.epam.koretskyi.commission.web.command;

import com.epam.koretskyi.commission.constant.Path;
import com.epam.koretskyi.commission.db.DBManager;
import com.epam.koretskyi.commission.db.Role;
import com.epam.koretskyi.commission.db.entity.Criterion;
import com.epam.koretskyi.commission.db.entity.Faculty;
import com.epam.koretskyi.commission.exception.AppException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

/**
 * @author D.Koretskyi on 02.10.2020.
 */
public class ListOfFacultiesCommand extends Command {
    private static final long serialVersionUID = 4098462857096504435L;

    private static final Logger LOG = Logger.getLogger(ListOfFacultiesCommand.class);


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");
        HttpSession httpSession = request.getSession();
        Role userRole = (Role) httpSession.getAttribute("userRole");

        // get faculties list
        List<Faculty> faculties = DBManager.getInstance().findAllFaculties();
        LOG.trace("Found in DB: faculties --> " + faculties);

        String sortParam = request.getParameter("sort");
        // sort faculties by name asc
        if ("nameAsc".equals(sortParam)) {
            faculties.sort(Comparator.comparing(Faculty::getNameEn));
            LOG.trace("Faculties sorted by name in ascending order");
        }
        // sort faculties by name desc
        if ("nameDesc".equals(sortParam)) {
            faculties.sort(Comparator.comparing(Faculty::getNameEn).reversed());
            LOG.trace("Faculties sorted by name in descending order");
        }
        // sort faculties by budget seats
        if ("budgetSeats".equals(sortParam)) {
            faculties.sort(Comparator.comparingInt(Faculty::getBudgetSeats).reversed());
            LOG.trace("Faculties sorted by amount of budget seats");
        }
        // sort faculties by total seats
        if ("totalSeats".equals(sortParam)) {
            faculties.sort(Comparator.comparingInt(Faculty::getTotalSeats).reversed());
            LOG.trace("Faculties sorted by total amount of seats");
        }

        // set criteria for each faculty
        for (int i = 0; i < faculties.size(); i++) {
            List<Criterion> criteria = DBManager.getInstance().findFacultyCriteriaByFacultyId(i + 1);
            LOG.trace("Set the criteria for faculty # " + (i + 1) + " --> " + criteria);
            faculties.get(i).setCriteria(criteria);
        }

        // put faculties list to the request
        request.setAttribute("faculties", faculties);
        LOG.trace("Set the request attribute: faculties --> " + faculties);

        if (userRole == Role.ADMIN) {
            return Path.PAGE_FACULTIES_ADMIN;
        }

        LOG.debug("Command finished");
        return Path.PAGE_FACULTIES;
    }
}
