package com.epam.koretskyi.commission.web.command;

import org.apache.log4j.Logger;

import java.util.Map;
import java.util.TreeMap;

/**
 * Class that contains all commands.
 *
 * @author D.Koretskyi on 22.09.2020.
 */
public class CommandContainer {

    private CommandContainer() {
    }

    private static Map<String, Command> commands = new TreeMap<>();

    private static final Logger LOG = Logger.getLogger(CommandContainer.class);

    static {
        // user
        commands.put("noCommand", new NoCommand());
        commands.put("login", new LoginCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("registerUser", new RegisterUserCommand());
        commands.put("listOfFaculties", new ListOfFacultiesCommand());
        commands.put("updateUser", new UpdateUserCommand());
        commands.put("registerForFaculty", new RegisterForFacultyCommand());
        commands.put("viewFacultyApplications", new ViewFacultyApplicationsCommand());
        commands.put("deleteUserApplication", new DeleteUserApplicationCommand());
        commands.put("viewReportSheetPage", new ViewReportSheetPage());
        commands.put("resetPassword", new ResetPasswordCommand());

        // admin
        commands.put("listOfUsers", new ListOfUsersCommand());
        commands.put("adminPanelPage", new AdminPanelPageCommand());
        commands.put("findUserByEmail", new FindUserByEmailCommand());
        commands.put("updateUsersStatus", new UpdateUsersStatusCommand());
        commands.put("deleteFaculty", new DeleteFacultyCommand());
        commands.put("updateFaculty", new UpdateFacultyCommand());
        commands.put("createNewFaculty", new CreateNewFacultyCommand());
        commands.put("viewUserProfilePage", new UserProfilePageCommand());
        commands.put("closeFacultyRecruitment", new CloseFacultyRecruitmentCommand());
        commands.put("downloadFile", new DownloadFileCommand());

        // get page
        commands.put("loginPage", new LoginPageCommand());
        commands.put("registerUserPage", new RegisterUserPageCommand());
        commands.put("privateOfficePage", new PrivateOfficePageCommand());
        commands.put("updateUserPage", new UpdateUserPageCommand());
        commands.put("errorPage", new ErrorPageCommand());
        commands.put("blockedUserPage", new BlockedUserPageCommand());
        commands.put("registerForFacultyPage", new RegisterForFacultyPageCommand());
        commands.put("updateFacultyPage", new UpdateFacultyPageCommand());
        commands.put("createNewFacultyPage", new CreateNewFacultyPageCommand());
        commands.put("resetPasswordPage", new ResetPasswordPageCommand());

        LOG.debug("Command container was successfully initialized");
        LOG.trace("Number of commands --> " + commands.size());
    }

    /**
     * Returns command object which execution will give path to the resource.
     *
     * @param commandName Name of the command.
     * @return Command object if container contains such command, otherwise
     * specific <code>noCommand</code object will be returned.
     */
    public static Command getCommand(String commandName) {
        if (commandName == null || !commands.containsKey(commandName)) {
            LOG.trace("Command not found, name --> " + commandName);
            return commands.get("noCommand");
        }
        return commands.get(commandName);
    }
}
