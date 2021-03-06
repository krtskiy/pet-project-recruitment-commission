package com.epam.koretskyi.commission.web.command;

import com.epam.koretskyi.commission.db.DBManager;
import com.epam.koretskyi.commission.db.bean.FacultyApplicationsBean;
import com.epam.koretskyi.commission.db.entity.Faculty;
import com.epam.koretskyi.commission.exception.AppException;
import com.epam.koretskyi.commission.exception.Messages;
import com.epam.koretskyi.commission.util.CommunicationHelper;
import com.epam.koretskyi.commission.util.FileCreator;
import com.epam.koretskyi.commission.util.constant.Path;
import org.apache.log4j.Logger;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * A command to close the faculty recruitment and send emails with notifications.
 * Redirects to the faculty report sheet page.
 *
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

        // set faculty status to closed
        manager.updateFaculty(faculty);

        // creating .pdf and .txt report sheets in project root
        FileCreator.createReportSheets(facultyId, request);

        // distribution of admission results to entrants
        List<FacultyApplicationsBean> facultyApplications = manager.findFacultyApplicationsByFacultyId(facultyId);
        facultyApplications.sort(Comparator.comparingInt(FacultyApplicationsBean::sumOfMarks).reversed());

        List<String> budgetEmails = new ArrayList<>();
        List<String> contractEmails = new ArrayList<>();
        List<String> failedEmails = new ArrayList<>();

        // filling the lists of entrant's emails depending on the form of education
        if (facultyApplications.size() < budgetSeats) {
            // if the number of entrants is less then the amount of budget seats
            for (FacultyApplicationsBean application : facultyApplications) {
                budgetEmails.add(application.getUserEmail());
            }
        } else if (facultyApplications.size() > budgetSeats && facultyApplications.size() < totalSeats) {
            // if the number of entrants is greater then amount of budget seats but less then total amount of seats
            for (int i = 0; i < budgetSeats; i++) {
                budgetEmails.add(facultyApplications.get(i).getUserEmail());
            }
            for (int i = budgetSeats; i < facultyApplications.size(); i++) {
                contractEmails.add(facultyApplications.get(i).getUserEmail());
            }
        } else {
            // if the number of entrants is greater then total amount of seats
            for (int i = 0; i < budgetSeats; i++) {
                budgetEmails.add(facultyApplications.get(i).getUserEmail());
            }
            for (int i = budgetSeats; i < totalSeats; i++) {
                contractEmails.add(facultyApplications.get(i).getUserEmail());
            }
            for (int i = totalSeats; i < facultyApplications.size(); i++) {
                failedEmails.add(facultyApplications.get(i).getUserEmail());
            }
        }

        LOG.trace("Amount of emails to send messages about the budget form of education --> " + budgetEmails.size());
        LOG.trace("Amount of emails to send messages about the contract form of education --> " + contractEmails.size());
        LOG.trace("Amount of emails to send messages about failed enrollment --> " + failedEmails.size());

        // email messages formation
        String mailTitleSuccess = "Congratulations on your admission to KRTSK University!";
        String mailTitleFailed = "We are sorry to deliver bad news...";
        String mailMessageBudget = CommunicationHelper.resultOfAdmission(true, "budget", faculty.getNameEn());
        String mailMessageContract = CommunicationHelper.resultOfAdmission(true, "contract", faculty.getNameEn());
        String mailMessageFailed = CommunicationHelper.resultOfAdmission(false, null, faculty.getNameEn());

        // sending emails to entrants
        try {
            if (!budgetEmails.isEmpty()) {
                CommunicationHelper.sendMail(budgetEmails, mailTitleSuccess, mailMessageBudget);
                LOG.debug("Emails for budget form entrants were sent");
            }
            if (!contractEmails.isEmpty()) {
                CommunicationHelper.sendMail(contractEmails, mailTitleSuccess, mailMessageContract);
                LOG.debug("Emails for contract form entrants were sent");
            }
            if (!failedEmails.isEmpty()) {
                CommunicationHelper.sendMail(failedEmails, mailTitleFailed, mailMessageFailed);
                LOG.debug("Emails for failed entrants were sent");
            }

            String successMailingMessage = "Emails were sent to all accepted applicants";
            request.getSession().setAttribute("successMailingMessage", successMailingMessage);
            LOG.trace("Set the session attribute: successMailingMessage --> " + successMailingMessage);
        } catch (MessagingException e) {
            LOG.error(Messages.ERR_CANNOT_SEND_EMAILS);
            String failedMailingMessage = "Something went wrong with the mailing. Emails were not sent";
            request.getSession().setAttribute("failedMailingMessage", failedMailingMessage);
            LOG.trace("Set the session attribute: failedMailingMessage --> " + failedMailingMessage);
        }

        LOG.debug("Command finished");
        return Path.COMMAND_VIEW_REPORT_SHEET + "&facultyId=" + facultyId;
    }

}
