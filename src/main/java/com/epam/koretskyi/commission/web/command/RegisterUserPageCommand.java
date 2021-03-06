package com.epam.koretskyi.commission.web.command;

import com.epam.koretskyi.commission.util.constant.Path;
import com.epam.koretskyi.commission.exception.AppException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Command that forwards to registration page.
 *
 * @author D.Koretskyi on 27.09.2020.
 */
public class RegisterUserPageCommand extends Command {
    private static final long serialVersionUID = 8554790514858104479L;

    private static final Logger LOG = Logger.getLogger(RegisterUserPageCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException,
            AppException {
        LOG.debug("Command starts");

        LOG.debug("Command finished");
        return Path.PAGE_REGISTER;
    }
}
