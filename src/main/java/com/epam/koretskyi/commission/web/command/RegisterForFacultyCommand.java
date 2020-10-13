package com.epam.koretskyi.commission.web.command;

import com.epam.koretskyi.commission.db.bean.UserFacultiesBean;
import com.epam.koretskyi.commission.util.constant.Path;
import com.epam.koretskyi.commission.db.DBManager;
import com.epam.koretskyi.commission.db.entity.Criterion;
import com.epam.koretskyi.commission.db.entity.Faculty;
import com.epam.koretskyi.commission.db.entity.User;
import com.epam.koretskyi.commission.db.entity.UserMark;
import com.epam.koretskyi.commission.exception.AppException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author D.Koretskyi on 11.10.2020.
 */
public class RegisterForFacultyCommand extends Command {
    private static final long serialVersionUID = 4917912591270427200L;

    private static final Logger LOG = Logger.getLogger(RegisterForFacultyCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        List<UserMark> userMarks = new ArrayList<>();
        Faculty faculty = (Faculty) session.getAttribute("faculty");
        List<Criterion> criteria = faculty.getCriteria();

        String[] marks = request.getParameterValues("marks");
        System.out.println(Arrays.toString(marks));
        for (int i = 0; i < marks.length; i++) {
            UserMark uMark = new UserMark();
            uMark.setUserId(user.getId());
            uMark.setCriterionId(criteria.get(i).getId());
            uMark.setMark(Integer.parseInt(marks[i]));
            userMarks.add(uMark);
        }

        // TODO this should be as a transaction
        DBManager.getInstance().insertApplication(user.getId(), faculty.getId());
        DBManager.getInstance().insertUserMarks(userMarks);

        session.setAttribute("facultyRegisteredFor", faculty);
        LOG.trace("Set the session attribute: facultyRegisteredFor --> " + faculty);

        List<UserFacultiesBean> userFacultiesBean = DBManager.getInstance().findUserFacultiesByUserId(user.getId());
        session.setAttribute("userFaculties", userFacultiesBean);
        LOG.trace("Set the session attribute: userFaculties --> " + userFacultiesBean);

        LOG.debug("Command finished");
        return Path.COMMAND_FACULTIES;
    }
}
