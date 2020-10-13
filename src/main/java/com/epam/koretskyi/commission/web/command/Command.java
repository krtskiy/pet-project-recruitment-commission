package com.epam.koretskyi.commission.web.command;

import com.epam.koretskyi.commission.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * Main parent for the Command pattern implementation.
 *
 * @author D.Koretskyi on 22.09.2020.
 */
public abstract class Command implements Serializable {
    private static final long serialVersionUID = 6980195259452967649L;

    /**
     * Execution method for command. Returns path to go to based on the client
     * request.
     *
     * @param request  - client request
     * @param response - server response
     * @return Address to go once the command is executed.
     * @throws IOException
     * @throws ServletException
     * @throws AppException
     */
    public abstract String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException,
            AppException;

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
