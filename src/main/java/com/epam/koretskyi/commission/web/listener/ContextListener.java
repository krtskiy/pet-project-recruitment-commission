package com.epam.koretskyi.commission.web.listener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author D.Koretskyi on 22.09.2020.
 */
public class ContextListener implements ServletContextListener {

    private static final Logger LOG = Logger.getLogger(ContextListener.class);


    public void contextInitialized(ServletContextEvent event) {
        System.out.println("[ContextListener] Servlet context initialization starts");

        ServletContext servletContext = event.getServletContext();
        initLog4J(servletContext);
        initCommandContainer();

        System.out.println("Servlet context initialization finished");

        // TODO locales
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("[ContextListener] Servlet context destruction starts");

        System.out.println("[ContextListener] Servlet context destruction finished");
    }

    private void initLog4J(ServletContext servletContext) {
        System.out.println("[ContextListener] Log4j initialization started");
        try {
            PropertyConfigurator.configure(servletContext.getRealPath("WEB-INF/log4j.properties"));
            LOG.debug("[ContextListener] Log4j has been initialized");
        } catch (Exception ex) {
            System.out.println("[ContextListener] Cannot configure Log4j");
            ex.printStackTrace();
        }
        System.out.println("[ContextListener] Log4J initialization finished");

    }

    private void initCommandContainer() {
        try {
            Class.forName("com.epam.koretskyi.commission.web.command.CommandContainer");
        } catch (ClassNotFoundException ex) {
            throw new IllegalStateException("Cannot initialize Command Container");
        }
    }
}
