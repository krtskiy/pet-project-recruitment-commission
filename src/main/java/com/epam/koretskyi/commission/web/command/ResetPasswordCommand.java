package com.epam.koretskyi.commission.web.command;

import com.epam.koretskyi.commission.db.DBManager;
import com.epam.koretskyi.commission.db.entity.User;
import com.epam.koretskyi.commission.exception.AppException;
import com.epam.koretskyi.commission.exception.Messages;
import com.epam.koretskyi.commission.util.CommunicationHelper;
import com.epam.koretskyi.commission.util.MD5Util;
import com.epam.koretskyi.commission.util.constant.Path;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

/**
 * Invoked when user resets their password.
 *
 * @author D.Koretskyi on 21.10.2020.
 */
public class ResetPasswordCommand extends Command {
    private static final long serialVersionUID = 4655940934375603217L;

    private static final Logger LOG = Logger.getLogger(ResetPasswordCommand.class);


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");

        String email = request.getParameter("email");
        User user = DBManager.getInstance().findUserByEmail(email);
        if (user == null || !StringUtils.isAllBlank(user.getName(), user.getSurname()) || user.getRoleId() == 1) {
            throw new AppException("User with such data does not exist!");
        }

        String newPassword = RandomStringUtils.randomAlphanumeric(10);

        String mailTitleNewPassword = "Your password has been changed";
        String mailMessageNewPassword = CommunicationHelper.newPassword(newPassword);

        try {
            CommunicationHelper.sendMail(Collections.singletonList(email), mailTitleNewPassword, mailMessageNewPassword);
        } catch (MessagingException e) {
            LOG.error(Messages.ERR_CANNOT_RESET_PASSWORD);
            request.getSession().setAttribute("cannotReset", Messages.ERR_CANNOT_RESET_PASSWORD);
            LOG.trace("Set the session attribute: cannotReset --> " + Messages.ERR_CANNOT_RESET_PASSWORD);

            throw new AppException(Messages.ERR_CANNOT_RESET_PASSWORD, e);
        }

        user.setPassword(MD5Util.md5Apache(newPassword));

        DBManager.getInstance().updateUser(user);

        String successPassResetMessage = "New password has been sent to your email!";
        request.getSession().setAttribute("successPassResetMessage", successPassResetMessage);
        LOG.trace("Set the session attribute: successPassResetMessage --> " + successPassResetMessage);

        LOG.debug("Command finished");
        return Path.COMMAND_LOGIN_PAGE;
    }
}
