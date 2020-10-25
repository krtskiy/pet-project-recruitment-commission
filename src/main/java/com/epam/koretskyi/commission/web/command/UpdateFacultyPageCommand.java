package com.epam.koretskyi.commission.web.command;

import com.epam.koretskyi.commission.db.bean.FacultyApplicationsBean;
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
import java.util.List;

/**
 * Command that forwards to faculty update page.
 *
 * @author D.Koretskyi on 08.10.2020.
 */
public class UpdateFacultyPageCommand extends Command {
    private static final long serialVersionUID = -5118379833410184046L;

    private static final Logger LOG = Logger.getLogger(UpdateFacultyPageCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");

        DBManager manager = DBManager.getInstance();

        Faculty faculty = DBManager.getInstance().findFacultyById(Integer.parseInt(request.getParameter("facultyId")));
        LOG.trace("Request parameter: facultyId --> " + faculty.getId());
        request.setAttribute("faculty", faculty);
        LOG.trace("Set the request attribute: faculty --> " + faculty);

        List<FacultyApplicationsBean> facultyApplications = manager.findFacultyApplicationsByFacultyId(faculty.getId());
        if (!facultyApplications.isEmpty()) {
            request.setAttribute("isEmpty", true);
        }

        List<Criterion> criteria = manager.findAllCriteria();
        request.setAttribute("criteria", criteria);
        LOG.trace("Set the request attribute: criteria --> " + criteria);

        LOG.debug("Command finished");
        return Path.PAGE_UPDATE_FACULTY;
    }
}
