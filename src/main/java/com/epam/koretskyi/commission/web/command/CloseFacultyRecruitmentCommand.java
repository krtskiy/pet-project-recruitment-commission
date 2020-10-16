package com.epam.koretskyi.commission.web.command;

import com.epam.koretskyi.commission.db.DBManager;
import com.epam.koretskyi.commission.db.bean.FacultyApplicationsBean;
import com.epam.koretskyi.commission.db.entity.Faculty;
import com.epam.koretskyi.commission.exception.AppException;
import com.epam.koretskyi.commission.exception.Messages;
import com.epam.koretskyi.commission.util.CommunicationHelper;
import com.epam.koretskyi.commission.util.constant.Path;
import org.apache.log4j.Logger;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author D.Koretskyi on 15.10.2020.
 */
public class CloseFacultyRecruitmentCommand extends Command {
    private static final long serialVersionUID = -1567150592844709847L;

    private static final Logger LOG = Logger.getLogger(CloseFacultyRecruitmentCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");

        DBManager manager = DBManager.getInstance();

        int statusId = Integer.parseInt(request.getParameter("status"));
        LOG.trace("Request parameter: status --> " + statusId);

        int facultyId = Integer.parseInt(request.getParameter("facultyId"));
        LOG.trace("Request parameter: facultyId --> " + facultyId);

        Faculty faculty = manager.findFacultyById(facultyId);
        faculty.setStatusId(statusId);

        int totalSeats = faculty.getTotalSeats();
        int budgetSeats = faculty.getBudgetSeats();

        manager.updateFaculty(faculty);

        List<FacultyApplicationsBean> facultyApplications = manager.findFacultyApplicationsByFacultyId(facultyId);
        facultyApplications.sort(Comparator.comparingInt(FacultyApplicationsBean::sumOfMarks).reversed());

        if (facultyApplications.size() > faculty.getTotalSeats()) {
            facultyApplications = facultyApplications.subList(0, faculty.getTotalSeats());
        }
// todo
        List<String> budgetEmails = new ArrayList<>();
        for (int i = 0; i < budgetSeats; i++) {
            budgetEmails.add(facultyApplications.get(i).getUserEmail());
        }

        List<String> contractEmails = new ArrayList<>();
        for (int i = budgetSeats; i < totalSeats; i++) {
            contractEmails.add(facultyApplications.get(i).getUserEmail());
        }

        String mailMessageBudget = CommunicationHelper.createMail("budget", faculty.getNameEn());
        String mailMessageContract = CommunicationHelper.createMail("contract", faculty.getNameEn());

        try {
            CommunicationHelper.sendMail(budgetEmails, mailMessageBudget);
            LOG.debug("Emails for budget form entrants were sent");
            CommunicationHelper.sendMail(contractEmails, mailMessageContract);
            LOG.debug("Emails for contract form entrants were sent");

            String successMailingMessage = "Emails were sent to all accepted applicants";
            request.getSession().setAttribute("successMailingMessage", successMailingMessage);
            LOG.trace("Set the session attribute: successMailingMessage --> " + successMailingMessage);
        } catch (MessagingException e) {
            LOG.error(Messages.ERR_CANNOT_SEND_EMAILS);
        }

        LOG.debug("Command finished");
        return Path.COMMAND_VIEW_REPORT_SHEET + "&facultyId=" + facultyId;
    }

}
