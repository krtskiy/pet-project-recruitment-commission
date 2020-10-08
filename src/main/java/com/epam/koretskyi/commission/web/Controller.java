package com.epam.koretskyi.commission.web;

import com.epam.koretskyi.commission.constant.ActionType;
import com.epam.koretskyi.commission.constant.Path;
import com.epam.koretskyi.commission.exception.AppException;
import com.epam.koretskyi.commission.web.command.Command;
import com.epam.koretskyi.commission.web.command.CommandContainer;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author D.Koretskyi on 22.09.2020.
 */
public class Controller extends HttpServlet {
    private static final long serialVersionUID = 3833633923651594347L;

    private static final Logger LOG = Logger.getLogger(Controller.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response, ActionType.POST);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response, ActionType.GET);
    }

    private void process(HttpServletRequest request, HttpServletResponse response, ActionType actionType)
            throws ServletException, IOException {
        LOG.debug("Controller starts");

        // extract command name from the request
        String commandName = request.getParameter("command");
        LOG.trace("Request parameter: command --> " + commandName);

        // obtain command object by its name
        Command command = CommandContainer.getCommand(commandName);
        LOG.trace("Obtained command --> " + command);

        // execute command and get forward address
        String forward = Path.PAGE_ERROR;
        try {
            forward = command.execute(request, response);
        } catch (AppException e) {
            request.setAttribute("errorMessage", e.getMessage());
        }
        LOG.trace("Forward address --> " + forward);

        LOG.debug("Controller finished, now go to forward address --> " + forward);

        // go to forward
        request.getRequestDispatcher(forward).forward(request, response);
    }
}
