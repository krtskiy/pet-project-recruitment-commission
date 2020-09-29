package com.epam.koretskyi.commission.web.command;

import com.epam.koretskyi.commission.constant.Path;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author D.Koretskyi on 27.09.2020.
 */
public class LoginPageCommand extends Command {
    private static final long serialVersionUID = 5658121277344189442L;

    private static final Logger LOG = Logger.getLogger(LoginPageCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("Command starts");

        LOG.debug("Command finished");
        return Path.PAGE_LOGIN;
    }
}
