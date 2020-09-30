package com.epam.koretskyi.commission.web.command;

import org.apache.log4j.Logger;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author D.Koretskyi on 22.09.2020.
 */
public class CommandContainer {

    private static Map<String, Command> commands = new TreeMap<>();

    private static final Logger LOG = Logger.getLogger(CommandContainer.class);


    static {
        commands.put("noCommand", new NoCommand());
        commands.put("login", new LoginCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("registerUser", new RegisterCommand());
        commands.put("loginPage", new LoginPageCommand());
        commands.put("registerUserPage", new RegisterUserPageCommand());
        commands.put("adminPanelPage", new AdminPanelPageCommand());
        commands.put("facultiesPage", new FacultiesPageCommand());
        commands.put("privateOfficePage", new PrivateOfficePageCommand());

        LOG.debug("Command container was successfully initialized");
        LOG.trace("Number of commands --> " + commands.size());
    }

    public static Command getCommand(String commandName) {
        if (commandName == null || !commands.containsKey(commandName)) {
            LOG.trace("Command not found, name --> " + commandName);
            return commands.get("noCommand");
        }
        return commands.get(commandName);
    }
}
