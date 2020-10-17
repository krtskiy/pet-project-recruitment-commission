package com.epam.koretskyi.commission.web.command;

import com.epam.koretskyi.commission.db.DBManager;
import com.epam.koretskyi.commission.db.bean.FacultyApplicationsBean;
import com.epam.koretskyi.commission.db.entity.Faculty;
import com.epam.koretskyi.commission.exception.AppException;
import com.epam.koretskyi.commission.util.constant.Path;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

/**
 * Command to view final report sheet of the faculty.
 *
 * @author D.Koretskyi on 15.10.2020.
 */
public class ViewReportSheetPage extends Command {
    private static final long serialVersionUID = 4361985638539322697L;

    private static final Logger LOG = Logger.getLogger(ViewReportSheetPage.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");

        DBManager manager = DBManager.getInstance();

        int facultyId = Integer.parseInt(request.getParameter("facultyId"));
        Faculty faculty = manager.findFacultyById(facultyId);
        request.setAttribute("faculty", faculty);
        LOG.trace("Set request attribute: faculty --> " + faculty);

        int totalSeats = faculty.getTotalSeats();

        List<FacultyApplicationsBean> facultyApplications = manager.findFacultyApplicationsByFacultyId(facultyId);
        facultyApplications.sort(Comparator.comparingInt(FacultyApplicationsBean::sumOfMarks).reversed());

        if (facultyApplications.size() < totalSeats) {
            LOG.trace("Set request attribute: facultyApplications --> " + facultyApplications);
            request.setAttribute("facultyApplications", facultyApplications);
        } else {
            LOG.trace("Set request attribute: facultyApplications --> " + facultyApplications);
            request.setAttribute("facultyApplications", facultyApplications.subList(0, totalSeats));
        }

        LOG.debug("Command finished");
        return Path.PAGE_REPORT_SHEET;
    }
}
