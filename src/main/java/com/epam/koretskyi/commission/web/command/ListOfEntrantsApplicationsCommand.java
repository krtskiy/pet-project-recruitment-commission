package com.epam.koretskyi.commission.web.command;

import com.epam.koretskyi.commission.constant.Path;
import com.epam.koretskyi.commission.exception.AppException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author D.Koretskyi on 07.10.2020.
 */
public class ListOfEntrantsApplicationsCommand extends Command {
    private static final long serialVersionUID = 1569015900222786982L;

    private static final Logger LOG = Logger.getLogger(ListOfEntrantsApplicationsCommand.class);


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        return Path.PAGE_ENTRANTS_APPLICATIONS;
    }
}
