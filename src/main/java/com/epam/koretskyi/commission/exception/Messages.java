package com.epam.koretskyi.commission.exception;

/**
 * Messages holder for custom exceptions.
 *
 * @author D.Koretskyi on 29.09.2020.
 */
public class Messages {


    private Messages() {
    }

    // user-related
    public static final String ERR_CANNOT_DOWNLOAD_FILE = "Cannot download file!";
    public static final String ERR_CANNOT_OBTAIN_USER_STATUS_BY_ID = "Cannot obtain user status by id!";
    public static final String ERR_CANNOT_INSERT_USER = "Cannot insert user!";
    public static final String ERR_CANNOT_UPDATE_USER = "Cannot update user!";
    public static final String ERR_CANNOT_UPDATE_STATUS = "Cannot update user status!";
    public static final String ERR_CANNOT_OBTAIN_USER_BY_EMAIL = "Cannot obtain user with this email!";
    public static final String ERR_CANNOT_FIND_USERS = "Cannot find users!";

    public static final String ERR_CANNOT_INSERT_USER_MARKS = "Cannot insert user marks!";
    public static final String ERR_CANNOT_FIND_USER_MARKS = "Cannot find user marks!";
    public static final Object ERR_CANNOT_SEND_EMAILS = "Cannot send emails!";

    // applications-related
    public static final String ERR_CANNOT_INSERT_APPLICATION = "Cannot insert application!";
    public static final String ERR_CANNOT_FIND_USER_APPLICATIONS = "Cannot find user application!";
    public static final String ERR_CANNOT_FIND_FACULTY_APPLICATIONS = "Cannot  find faculty applications!";
    public static final String ERR_CANNOT_DELETE_APPLICATION = "Cannot delete application!";

    // faculty-related
    public static final String ERR_CANNOT_UPDATE_FACULTY = "Cannot update faculty!";
    public static final String ERR_CANNOT_INSERT_FACULTY = "Cannot insert faculty!";
    public static final String ERR_CANNOT_FIND_FACULTIES = "Cannot find faculties!";
    public static final String ERR_CANNOT_DELETE_FACULTY = "Cannot delete faculty!";
    public static final String ERR_CANNOT_OBTAIN_FACULTY_BY_ID = "Cannot obtain faculty by this id!";

    // faculty criteria-related
    public static final String ERR_CANNOT_FIND_FACULTY_CRITERIA = "Cannot find faculty criteria!";
    public static final String ERR_CANNOT_FIND_ALL_CRITERIA = "Cannot find all criteria!";
    public static final String ERR_CANNOT_OBTAIN_CRITERION_BY_ID = "Cannot obtain criterion by id!";

    // program-related
    public static final String ERR_CANNOT_OBTAIN_CONNECTION = "Cannot obtain connection from the pool!";
    public static final String ERR_CANNOT_OBTAIN_DATA_SOURCE = "Cannot obtain the data source!";
    public static final String ERR_CANNOT_CLOSE_RESULTSET = "Cannot close resultset!";
    public static final String ERR_CANNOT_ROLLBACK_TRANSACTION = "Cannot rollback transaction!";

}
