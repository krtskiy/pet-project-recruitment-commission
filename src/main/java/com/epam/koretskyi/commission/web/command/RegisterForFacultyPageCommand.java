package com.epam.koretskyi.commission.web.command;

import com.epam.koretskyi.commission.db.bean.UserMarksBean;
import com.epam.koretskyi.commission.db.entity.Criterion;
import com.epam.koretskyi.commission.db.entity.User;
import com.epam.koretskyi.commission.util.constant.Path;
import com.epam.koretskyi.commission.db.DBManager;
import com.epam.koretskyi.commission.db.entity.Faculty;
import com.epam.koretskyi.commission.exception.AppException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Forwards to faculty application page.
 *
 * @author D.Koretskyi on 11.10.2020.
 */
public class RegisterForFacultyPageCommand extends Command {
    private static final long serialVersionUID = 4917912591270427200L;

    private static final Logger LOG = Logger.getLogger(RegisterForFacultyPageCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");

        String facultyId = request.getParameter("facultyId");
        LOG.trace("Request parameter: facultyId --> " + facultyId);
        request.setAttribute("facultyId", facultyId);

        Faculty faculty = DBManager.getInstance().findFacultyById(Integer.parseInt(facultyId));
        User user = (User) request.getSession().getAttribute("user");

        // all faculty criteria
        List<Criterion> criteria = faculty.getCriteria();

        // the marks that the entrant has already entered
        List<UserMarksBean> userMarks = DBManager.getInstance().findUserMarksByUserId(user.getId());

        // correlation of faculty criteria with already entered entrant's marks
        Map<Criterion, UserMarksBean> userMarksMap = new LinkedHashMap<>();
        // filling map
        // key - criterion, value - user mark, if it was entered earlier, or null, if it was not
        criteria.forEach(o -> userMarksMap.put(o, containsMark(userMarks, o.getId())));

        request.setAttribute("userMarksMap", userMarksMap);
        LOG.trace("Set the request attribute: userMarksMap --> " + userMarksMap);

        LOG.debug("Command finished");
        return Path.PAGE_FACULTY_REGISTER;
    }

    /**
     * Checks if the user has previously entered a mark for a specific criterion.
     *
     * @param list   list of marks that entrant has already entered
     * @param criterionId   ID of criterion.
     * @return   UserMarksBean object, if user has already entered a score for this criterion,
     *           null if there is no mark for this criterion yet.
     */
    private UserMarksBean containsMark(final List<UserMarksBean> list, final int criterionId){
        return list.stream()
                .filter(o -> o.getCriterionId() == (criterionId))
                .findFirst()
                .orElse(null);
    }
}
