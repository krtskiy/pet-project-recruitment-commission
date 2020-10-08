package com.epam.koretskyi.commission.web.filter;

import com.epam.koretskyi.commission.constant.Path;
import com.epam.koretskyi.commission.db.Role;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/**
 * @author D.Koretskyi on 05.10.2020.
 */
public class CommandAccessFilter implements Filter {

    private static final Logger LOG = Logger.getLogger(CommandAccessFilter.class);

    //command access
    private Map<Role, List<String>> accessMap = new HashMap<>();
    private List<String> commons = new ArrayList<>();
    private List<String> woAccessControl = new ArrayList<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOG.debug("Filter initialization starts");

        // roles
        accessMap.put(Role.ADMIN, asList(filterConfig.getInitParameter("admin")));
        accessMap.put(Role.USER, asList(filterConfig.getInitParameter("user")));
        LOG.trace("Access map --> " + accessMap);

        // commons
        commons = asList(filterConfig.getInitParameter("common"));
        LOG.trace("Common commands --> " + commons);

        // without  access control
        woAccessControl = asList(filterConfig.getInitParameter("without-access-control"));
        LOG.trace("Out of control commands --> " + woAccessControl);

        LOG.debug("Filter initialization finished");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        LOG.debug("Filter starts");

        if (accessAllowed(servletRequest)) {
            LOG.debug("Filter finished");
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            String errorMessage = "You do not have permission to access the requested resource";

            servletRequest.setAttribute("errorMessage", errorMessage);
            LOG.trace("Set the request attribute: errorMessage --> " + errorMessage);

            servletRequest.getRequestDispatcher(Path.PAGE_ERROR).forward(servletRequest, servletResponse);
        }
    }

    private boolean accessAllowed(ServletRequest request) {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String commandName = request.getParameter("command");
        if (commandName == null || commandName.isEmpty()) {
            return false;
        }

        if (woAccessControl.contains(commandName)) {
            return true;
        }

        HttpSession session = httpRequest.getSession();
        if (session == null) {
            return false;
        }

        Role userRole = (Role) session.getAttribute("userRole");
        if (userRole == null) {
            return false;
        }

        return accessMap.get(userRole).contains(commandName) || commons.contains(commandName);
    }

    @Override
    public void destroy() {
        LOG.debug("Filter destruction starts");

        LOG.debug("Filter destruction finished");
    }

    private List<String> asList(String string) {
        List<String> list = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(string);
        while (tokenizer.hasMoreTokens()) {
            list.add(tokenizer.nextToken());
        }
        return list;
    }
}
