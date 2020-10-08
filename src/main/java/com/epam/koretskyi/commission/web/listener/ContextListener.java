package com.epam.koretskyi.commission.web.listener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

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

        System.out.println("[ContextListener] Servlet context initialization finished");
        String localesFileName = servletContext.getInitParameter("locales");

        // obtain real path on server
        String localesFileRealPath = servletContext.getRealPath(localesFileName);

        // locale descriptions
        Properties locales = new Properties();
        try {
            locales.load(new FileInputStream(localesFileRealPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // save descriptions to servlet context
        servletContext.setAttribute("locales", locales);
        locales.list(System.out);
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
