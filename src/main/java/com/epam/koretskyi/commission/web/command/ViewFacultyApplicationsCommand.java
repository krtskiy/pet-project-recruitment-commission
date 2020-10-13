package com.epam.koretskyi.commission.web.command;

import com.epam.koretskyi.commission.db.DBManager;
import com.epam.koretskyi.commission.db.bean.FacultyApplicationsBean;
import com.epam.koretskyi.commission.db.bean.UserMarksBean;
import com.epam.koretskyi.commission.db.entity.Faculty;
import com.epam.koretskyi.commission.db.entity.User;
import com.epam.koretskyi.commission.db.entity.UserMark;
import com.epam.koretskyi.commission.exception.AppException;
import com.epam.koretskyi.commission.util.constant.Path;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

/**
 * @author D.Koretskyi on 08.10.2020.
 */
public class ViewFacultyApplicationsCommand extends Command {
    private static final long serialVersionUID = -392252550961277009L;

    private static final Logger LOG = Logger.getLogger(ViewFacultyApplicationsCommand.class);


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");

        HttpSession session = request.getSession();
        DBManager manager = DBManager.getInstance();

        int facultyId = Integer.parseInt(request.getParameter("facultyId"));

        Faculty faculty = manager.findFacultyById(facultyId);
        request.setAttribute("faculty", faculty);
        LOG.trace("Set request attribute: faculty --> " + faculty);

        List<FacultyApplicationsBean> facultyApplications = manager.findFacultyApplicationsByFacultyId(facultyId);

        // sort by sum of marks
        facultyApplications.sort(Comparator.comparingInt(FacultyApplicationsBean::sumOfMarks).reversed());

        request.setAttribute("facultyApplications", facultyApplications);
        LOG.trace("Set request attribute: facultyApplications --> " + facultyApplications);

        LOG.debug("Command finished");
        return Path.PAGE_FACULTY_ENTRANTS;
    }
}
