package com.epam.koretskyi.commission.constant;

/**
 * @author D.Koretskyi on 23.09.2020.
 */
public final class Path {



    private Path() {
    }

    // common
    public static final String PAGE_WELCOME = "/welcome.jsp";
    public static final String PAGE_ERROR = "/WEB-INF/jsp/error_page.jsp";
    public static final String PAGE_BLOCKED_USER = "/WEB-INF/jsp/blocked_user_page.jsp";
    public static final String PAGE_LOGIN = "/WEB-INF/jsp/login.jsp";
    public static final String PAGE_REGISTER = "/WEB-INF/jsp/register.jsp";

    // user
    public static final String PAGE_FACULTIES = "/WEB-INF/jsp/user/faculties.jsp";
    public static final String PAGE_PRIVATE_OFFICE = "/WEB-INF/jsp/user/private_office.jsp";
    public static final String PAGE_UPDATE_PROFILE = "/WEB-INF/jsp/user/update_profile.jsp";
    public static final String PAGE_ENTRANTS_APPLICATIONS = "/WEB-INF/jsp/user/entrants_applications.jsp";

    // admin
    public static final String PAGE_ADMIN_PANEL = "/WEB-INF/jsp/admin/admin_panel.jsp";
    public static final String PAGE_ADMIN_USERS = "/WEB-INF/jsp/admin/users.jsp";
    public static final String PAGE_UPDATE_FACULTY = "/WEB-INF/jsp/admin/update_faculty.jsp";
    public static final String PAGE_FACULTIES_ADMIN = "/WEB-INF/jsp/admin/faculties_admin.jsp";
    public static final String PAGE_CREATE_NEW_FACULTY = "/WEB-INF/jsp/admin/new_faculty.jsp";


    public static final String COMMAND_BLOCKED_USER = "/controller?command=blockedUserPage";
    public static final String COMMAND_ERROR_PAGE = "controller?command=errorPage";
    public static final String COMMAND_PRIVATE_OFFICE = "/controller?command=privateOfficePage";
    public static final String COMMAND_ADMIN_PANEL = "/controller?command=adminPanelPage";
    public static final String COMMAND_LOGIN_PAGE = "/controller?command=loginPage";
    public static final String COMMAND_FACULTIES = "/controller?command=listOfFaculties&sort=nameAsc";
    public static final String COMMAND_USERS = "/controller?command=listOfUsers&page=1&sort=id";
    public static final String COMMAND_FIND_USER_BY_EMAIL = "/controller?command=findUserByEmail";

}
