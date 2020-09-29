package com.epam.koretskyi.commission.web.command;

import com.epam.koretskyi.commission.constant.Path;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author D.Koretskyi on 23.09.2020.
 */
public class LogoutCommand extends Command {
    private static final long serialVersionUID = 5875737764047955505L;

    private static final Logger LOG = Logger.getLogger(LogoutCommand.class);


    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("Command starts");

        HttpSession httpSession = request.getSession(false);
        if (httpSession != null) {
            httpSession.invalidate();
        }

        LOG.debug("Command finished");
        return Path.PAGE_WELCOME;
    }
}
