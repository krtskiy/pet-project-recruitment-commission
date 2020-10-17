package com.epam.koretskyi.commission.web.command;

import com.epam.koretskyi.commission.util.constant.Path;
import com.epam.koretskyi.commission.db.DBManager;
import com.epam.koretskyi.commission.db.entity.Criterion;
import com.epam.koretskyi.commission.exception.AppException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Command that forwards to the faculty creation page.
 *
 * @author D.Koretskyi on 08.10.2020.
 */
public class CreateNewFacultyPageCommand extends Command {
    private static final long serialVersionUID = 1800759918401501700L;

    private static final Logger LOG = Logger.getLogger(CreateNewFacultyPageCommand.class);


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");

        List<Criterion> criteria = DBManager.getInstance().findAllCriteria();
        request.setAttribute("criteria", criteria);
        LOG.trace("Set the request attribute: criteria --> " + criteria);

        LOG.debug("Command finished");
        return Path.PAGE_CREATE_NEW_FACULTY;
    }
}
