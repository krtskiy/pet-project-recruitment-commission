package com.epam.koretskyi.commission.web.command;

import com.epam.koretskyi.commission.exception.AppException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author D.Koretskyi on 08.10.2020.
 */
public class ViewFacultyEntrantsCommand extends Command {
    private static final long serialVersionUID = -392252550961277009L;

    private static final Logger LOG = Logger.getLogger(ViewFacultyEntrantsCommand.class);


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        return null;
    }
}
