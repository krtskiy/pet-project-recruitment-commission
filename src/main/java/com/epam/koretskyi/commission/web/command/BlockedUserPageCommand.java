package com.epam.koretskyi.commission.web.command;

import com.epam.koretskyi.commission.util.constant.Path;
import com.epam.koretskyi.commission.exception.AppException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author D.Koretskyi on 10.10.2020.
 */
public class BlockedUserPageCommand extends Command {
    private static final long serialVersionUID = 2643186100998019266L;

    private static final Logger LOG = Logger.getLogger(BlockedUserPageCommand.class);


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Commands starts");

        LOG.debug("Commands finished");
        return Path.PAGE_BLOCKED_USER;
    }
}
