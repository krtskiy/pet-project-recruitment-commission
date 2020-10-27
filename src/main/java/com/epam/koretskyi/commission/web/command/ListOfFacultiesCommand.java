package com.epam.koretskyi.commission.web.command;

import com.epam.koretskyi.commission.db.DBManager;
import com.epam.koretskyi.commission.db.Role;
import com.epam.koretskyi.commission.db.bean.FacultyApplicationsBean;
import com.epam.koretskyi.commission.db.bean.UserFacultiesBean;
import com.epam.koretskyi.commission.db.entity.Faculty;
import com.epam.koretskyi.commission.db.entity.User;
import com.epam.koretskyi.commission.exception.AppException;
import com.epam.koretskyi.commission.util.constant.Path;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Invoked when user wants to see all faculties that exist on current time.
 *
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
        User user = (User) httpSession.getAttribute("user");

        // get faculties list
        List<Faculty> faculties = DBManager.getInstance().findAllFaculties();
        LOG.trace("Found in DB: faculties --> " + faculties);

        String sortParam = request.getParameter("sort");
        
        // sorting according to the selected parameter
        switch (sortParam) {
            case "number" :
                faculties.sort(Comparator.comparing(Faculty::getId));
                LOG.trace("Faculties sorted by faculty number in ascending order");
                break;
            case "nameAsc" :
                faculties.sort(Comparator.comparing(Faculty::getNameEn));
                LOG.trace("Faculties sorted by name in ascending order");
                break;
            case "nameDesc" :
                faculties.sort(Comparator.comparing(Faculty::getNameEn).reversed());
                LOG.trace("Faculties sorted by name in descending order");
                break;
            case "budgetSeats" :
                faculties.sort(Comparator.comparingInt(Faculty::getBudgetSeats).reversed());
                LOG.trace("Faculties sorted by amount of budget seats");
                break;
            case "totalSeats" :
                faculties.sort(Comparator.comparingInt(Faculty::getTotalSeats).reversed());
                LOG.trace("Faculties sorted by total amount of seats");
                break;
            default:
                break;
        }

        // techTask
        Map<Faculty, List<FacultyApplicationsBean>> facultyApplicationsMap = new LinkedHashMap<>();
        for (Faculty faculty : faculties) {
            List<FacultyApplicationsBean> facultyApplications = DBManager.getInstance().findFacultyApplicationsByFacultyId(faculty.getId());
            facultyApplicationsMap.put(faculty, facultyApplications);
        }
        request.setAttribute("facultyApplicationsMap", facultyApplicationsMap);
        // /techTask

        if (user != null) {
            List<UserFacultiesBean> userFaculties = DBManager.getInstance().findUserFacultiesByUserId(user.getId());
            request.setAttribute("userFaculties", userFaculties);
            LOG.trace("Set the request attribute: userFaculties --> " + userFaculties);
        }

        if (userRole == Role.ADMIN) {
            return Path.PAGE_FACULTIES_ADMIN;
        }

        LOG.debug("Command finished");
        return Path.PAGE_FACULTIES;
    }
}
