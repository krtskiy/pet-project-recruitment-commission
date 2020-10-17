package com.epam.koretskyi.commission.web.command;

import com.epam.koretskyi.commission.util.constant.Path;
import com.epam.koretskyi.commission.exception.AppException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Command to forward user to an error page if any exceptions were thrown.
 *
 * @author D.Koretskyi on 09.10.2020.
 */
public class ErrorPageCommand extends Command {
    private static final long serialVersionUID = 373993252447786855L;

    private static final Logger LOG = Logger.getLogger(ErrorPageCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");

        LOG.debug("Command finished");
        return Path.PAGE_ERROR;
    }
}
