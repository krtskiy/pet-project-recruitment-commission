package com.epam.koretskyi.commission.web.command;

import com.epam.koretskyi.commission.exception.AppException;
import com.epam.koretskyi.commission.util.constant.Path;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Command that forwards to reset password page.
 *
 * @author D.Koretskyi on 21.10.2020.
 */
public class ResetPasswordPageCommand extends Command {
    private static final long serialVersionUID = 9197787222931773348L;

    private static final Logger LOG = Logger.getLogger(ResetPasswordPageCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");

        LOG.debug("Command finished");
        return Path.PAGE_RESET_PASSWORD;
    }
}
