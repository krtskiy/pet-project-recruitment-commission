package com.epam.koretskyi.commission.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author D.Koretskyi on 22.09.2020.
 */
public class ContextListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        initLog4J(servletContextEvent.getServletContext());
        initCommandContainer();
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        // log destruction
    }

    private void initLog4J(ServletContext servletContext) {

    }

    private void initCommandContainer() {

    }
}
