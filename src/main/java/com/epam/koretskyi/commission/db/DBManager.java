package com.epam.koretskyi.commission.db;

import com.epam.koretskyi.commission.constant.Field;
import com.epam.koretskyi.commission.db.entity.*;
import com.epam.koretskyi.commission.exception.DBException;
import com.epam.koretskyi.commission.exception.Messages;
import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author D.Koretskyi on 22.09.2020.
 */
public class DBManager {
    private static final Logger LOG = Logger.getLogger(DBManager.class);
    private DataSource dataSource;

    // faculty
    private static final String SQL_FIND_FACULTY_BY_ID = "SELECT * FROM faculties WHERE id=?";
    private static final String SQL_DELETE_FACULTY = "DELETE FROM faculties WHERE id=? LIMIT 1";
    private static final String SQL_FIND_ALL_FACULTIES = "SELECT * FROM faculties;";
    private static final String SQL_UPDATE_FACULTY = "UPDATE faculties SET name_en=?, name_uk=?, total_seats=?, budget_seats=? WHERE id=?";
    private static final String SQL_INSERT_FACULTY = "INSERT INTO faculties VALUES (?, ?, ?, ?, ?)";

    // criterion
    private static final String SQL_FIND_FACULTY_CRITERIA = "SELECT * FROM criteria WHERE id IN (SELECT criterion_id FROM faculty_criteria WHERE faculty_id = ?);";
    private static final String SQL_FIND_ALL_CRITERIA = "SELECT * FROM criteria";
    private static final String SQL_FIND_CRITERION_BY_ID = "SELECT * FROM criteria WHERE id=?";
    private static final String SQL_INSERT_FACULTY_CRITERIA = "INSERT INTO faculty_criteria VALUES (?, ?);";
    private static final String SQL_DELETE_FACULTY_CRITERIA = "DELETE FROM faculty_criteria WHERE faculty_id=?";

    // user
    private static final String SQL_FIND_USER_STATUS_BY_ID = "SELECT * FROM user_statuses WHERE id=?";
    private static final String SQL_FIND_USER_BY_EMAIL = "SELECT * FROM users WHERE email=?";
    private static final String SQL_INSERT_USER = "INSERT INTO users VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?, DEFAULT , DEFAULT)";
    private static final String SQL_UPDATE_USER = "UPDATE users SET email=?, password=? WHERE id=?";
    private static final String SQL_FIND_ALL_USERS = "SELECT * FROM users";
    private static final String SQL_UPDATE_USER_STATUS = "UPDATE users SET status_id=? WHERE id=?";

    // user marks
    private static final String SQL_INSERT_USER_MARKS = "REPLACE INTO user_marks VALUES (?, ?, ?)";
    private static final String SQL_FIND_USER_MARKS2 = "SELECT * FROM user_marks WHERE user_id=?;";

    // applications
    private static final String SQL_INSERT_APPLICATION = "REPLACE INTO applications VALUES (?, ?)";
    private static final String SQL_FIND_USER_APPLICATIONS = "SELECT * FROM applications WHERE user_id = ?;";

    ///////////////////////////////////
    // singleton
    ///////////////////////////////////

    private static DBManager dbManager;

    private DBManager() throws DBException {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            dataSource = (DataSource) envContext.lookup("jdbc/commission");
        } catch (NamingException e) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_DATA_SOURCE, e);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_DATA_SOURCE, e);
        }
    }

    public static synchronized DBManager getInstance() throws DBException {
        if (dbManager == null) {
            dbManager = new DBManager();
        }
        return dbManager;
    }


    private Connection getConnection() throws DBException {
        Connection connection;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_CONNECTION, e);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_CONNECTION, e);
        }
        return connection;
    }

    ///////////////////////////////////
    // faculty
    ///////////////////////////////////

    public List<Faculty> findAllFaculties() throws DBException {
        List<Faculty> faculties = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL_FIND_ALL_FACULTIES);
            while (resultSet.next()) {
                faculties.add(extractFaculty(resultSet));
            }
            connection.commit();
        } catch (SQLException e) {
            rollback(connection);
            LOG.error(Messages.ERR_CANNOT_FIND_FACULTIES, e);
            throw new DBException(Messages.ERR_CANNOT_FIND_FACULTIES, e);
        } finally {
            close(resultSet);
            close(statement);
            close(connection);
        }
        return faculties;
    }

    public void deleteFaculty(int facultyId) throws DBException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(SQL_DELETE_FACULTY);
            preparedStatement.setInt(1, facultyId);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            rollback(connection);
            LOG.error(Messages.ERR_CANNOT_INSERT_USER, e);
            throw new DBException(Messages.ERR_CANNOT_DELETE_FACULTY, e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
    }

    public Faculty findFacultyById(int facultyId) throws DBException {
        Faculty faculty = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_FACULTY_BY_ID);
            preparedStatement.setInt(1, facultyId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                faculty = extractFaculty(resultSet);
                faculty.setCriteria(findFacultyCriteriaByFacultyId(facultyId));
            }
        } catch (SQLException e) {
            rollback(connection);
            LOG.error(Messages.ERR_CANNOT_OBTAIN_FACULTY_BY_ID, e);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_FACULTY_BY_ID, e);
        } finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }
        return faculty;
    }

    public void updateFaculty(Faculty faculty) throws DBException {
        Connection connection = null;
        try {
            connection = getConnection();
            updateFaculty(connection, faculty);
            deleteFacultyCriteria(connection, faculty);
            insertFacultyCriteria(connection, faculty);
            connection.commit();
        } catch (SQLException e) {
            rollback(connection);
            throw new DBException(Messages.ERR_CANNOT_UPDATE_FACULTY, e);
        }
    }

    private void updateFaculty(Connection connection, Faculty faculty) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_UPDATE_FACULTY);
            int k = 0;
            preparedStatement.setString(++k, faculty.getNameEn());
            preparedStatement.setString(++k, faculty.getNameUk());
            preparedStatement.setInt(++k, faculty.getTotalSeats());
            preparedStatement.setInt(++k, faculty.getBudgetSeats());
            preparedStatement.setInt(++k, faculty.getId());
            preparedStatement.executeUpdate();
        } finally {
            close(preparedStatement);
        }
    }

    public void insertFaculty(Faculty faculty) throws DBException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(SQL_INSERT_FACULTY);

            insertFaculty(connection, faculty);
            insertFacultyCriteria(connection, faculty);

            connection.commit();
        } catch (SQLException e) {
            rollback(connection);
            LOG.error(Messages.ERR_CANNOT_INSERT_USER, e);
            throw new DBException(Messages.ERR_CANNOT_INSERT_FACULTY, e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
    }

    private void insertFaculty(Connection connection, Faculty faculty) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_FACULTY);
        int k = 0;
        preparedStatement.setInt(++k, faculty.getId());
        preparedStatement.setString(++k, faculty.getNameEn());
        preparedStatement.setString(++k, faculty.getNameUk());
        preparedStatement.setInt(++k, faculty.getTotalSeats());
        preparedStatement.setInt(++k, faculty.getBudgetSeats());
        preparedStatement.execute();
    }

    private void insertFacultyCriteria(Connection connection, Faculty faculty) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_FACULTY_CRITERIA);
        List<Criterion> facultyCriteria = faculty.getCriteria();
        for (Criterion facultyCriterion : facultyCriteria) {
            int k = 0;
            preparedStatement.setInt(++k, faculty.getId());
            preparedStatement.setInt(++k, facultyCriterion.getId());
            preparedStatement.addBatch();
        }
        preparedStatement.executeBatch();
    }

    private void deleteFacultyCriteria(Connection connection, Faculty faculty) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_FACULTY_CRITERIA);
        preparedStatement.setInt(1, faculty.getId());
        preparedStatement.executeUpdate();
    }


    ///////////////////////////////////
    // criterion
    ///////////////////////////////////

    public List<Criterion> findFacultyCriteriaByFacultyId(int facultyId) throws DBException {
        List<Criterion> criteria = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_FACULTY_CRITERIA);
            preparedStatement.setInt(1, facultyId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                criteria.add(extractCriterion(resultSet));
            }
            connection.commit();
        } catch (SQLException e) {
            rollback(connection);
            LOG.error(Messages.ERR_CANNOT_FIND_FACULTY_CRITERIA);
            throw new DBException(Messages.ERR_CANNOT_FIND_FACULTY_CRITERIA, e);
        } finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }
        return criteria;
    }


    public List<Criterion> findAllCriteria() throws DBException {
        List<Criterion> criteria = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL_FIND_ALL_CRITERIA);
            while (resultSet.next()) {
                criteria.add(extractCriterion(resultSet));
            }
            connection.commit();
        } catch (SQLException e) {
            rollback(connection);
            LOG.error(Messages.ERR_CANNOT_FIND_ALL_CRITERIA, e);
            throw new DBException(Messages.ERR_CANNOT_FIND_ALL_CRITERIA, e);
        } finally {
            close(resultSet);
            close(statement);
            close(connection);
        }
        return criteria;
    }

    public Criterion findCriterionById(int criteriaId) throws DBException {
        Criterion criterion = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_CRITERION_BY_ID);
            preparedStatement.setInt(1, criteriaId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                criterion = extractCriterion(resultSet);
            }
        } catch (SQLException e) {
            rollback(connection);
            LOG.error(Messages.ERR_CANNOT_OBTAIN_CRITERION_BY_ID, e);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_CRITERION_BY_ID, e);
        } finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }
        return criterion;
    }

    ///////////////////////////////////
    // user
    ///////////////////////////////////

    public void insertUser(User user) throws DBException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(SQL_INSERT_USER);
            int k = 0;
            preparedStatement.setString(++k, user.getName());
            preparedStatement.setString(++k, user.getSurname());
            preparedStatement.setString(++k, user.getPatronymic());
            preparedStatement.setString(++k, user.getEmail());
            preparedStatement.setString(++k, user.getPassword());
            preparedStatement.setString(++k, user.getRegion());
            preparedStatement.setString(++k, user.getCity());
            preparedStatement.setString(++k, user.getInstitutionName());
            preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            rollback(connection);
            LOG.error(Messages.ERR_CANNOT_INSERT_USER, e);
            throw new DBException(Messages.ERR_CANNOT_INSERT_USER, e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
    }

    public User findUserByEmail(String email) throws DBException {
        User user = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_USER_BY_EMAIL);
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = extractUser(resultSet);
            }
            connection.commit();
        } catch (SQLException e) {
            rollback(connection);
            LOG.error(Messages.ERR_CANNOT_OBTAIN_USER_BY_EMAIL, e);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER_BY_EMAIL, e);
        } finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }
        return user;
    }

    public List<User> findAllUsers() throws DBException {
        List<User> users = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL_FIND_ALL_USERS);
            while (resultSet.next()) {
                users.add(extractUser(resultSet));
            }
            connection.commit();
        } catch (SQLException e) {
            rollback(connection);
            LOG.error(Messages.ERR_CANNOT_FIND_USERS);
            throw new DBException(Messages.ERR_CANNOT_FIND_USERS, e);
        } finally {
            close(resultSet);
            close(statement);
            close(connection);
        }
        return users;
    }

    public void updateUser(User user) throws DBException {
        Connection connection = null;
        try {
            connection = getConnection();
            updateUser(connection, user);
            connection.commit();
        } catch (SQLException e) {
            rollback(connection);
            throw new DBException(Messages.ERR_CANNOT_UPDATE_USER, e);
        } finally {
            close(connection);
        }
    }

    private void updateUser(Connection connection, User user) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_UPDATE_USER);
            int k = 0;
            preparedStatement.setString(++k, user.getEmail());
            preparedStatement.setString(++k, user.getPassword());
            preparedStatement.setInt(++k, user.getId());
            preparedStatement.executeUpdate();
        } finally {
            close(preparedStatement);
        }
    }

    public String findUserStatusById(int statusId) throws DBException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String status = "";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_USER_STATUS_BY_ID);
            preparedStatement.setInt(1, statusId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                status = resultSet.getString("status");
            }
            connection.commit();
        } catch (SQLException e) {
            rollback(connection);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER_STATUS_BY_ID, e);
        } finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }
        return status;
    }

    public void updateUserStatus(int statusId, int userId) throws DBException {
        Connection connection = null;
        try {
            connection = getConnection();
            updateUserStatus(connection, statusId, userId);
            connection.commit();
        } catch (SQLException e) {
            rollback(connection);
            LOG.error(Messages.ERR_CANNOT_UPDATE_STATUS, e);
            throw new DBException(Messages.ERR_CANNOT_UPDATE_STATUS, e);
        } finally {
            close(connection);
        }
    }

    private void updateUserStatus(Connection connection, int statusId, int userId) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_UPDATE_USER_STATUS);
            int k = 0;
            preparedStatement.setInt(++k, statusId);
            preparedStatement.setInt(++k, userId);
            preparedStatement.executeUpdate();
        } finally {
            close(preparedStatement);
        }
    }

    ///////////////////////////////////
    // user marks
    ///////////////////////////////////

    public void insertUserMarks(List<UserMark> userMarks) throws DBException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(SQL_INSERT_USER_MARKS);
            for (UserMark userMark : userMarks) {
                int k = 0;
                preparedStatement.setInt(++k, userMark.getUserId());
                preparedStatement.setInt(++k, userMark.getCriterionId());
                preparedStatement.setInt(++k, userMark.getMark());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            rollback(connection);
            LOG.error(Messages.ERR_CANNOT_INSERT_USER_MARKS, e);
            throw new DBException(Messages.ERR_CANNOT_INSERT_USER_MARKS, e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
    }


    public List<UserMark> findUserMarksByUserId(int userId) throws DBException {
        List<UserMark> userMarks = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_USER_MARKS2);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                userMarks.add(extractUserMark(resultSet));
            }
            connection.commit();
        } catch (SQLException e) {
            rollback(connection);
            LOG.error(Messages.ERR_CANNOT_FIND_FACULTY_CRITERIA);
            throw new DBException(Messages.ERR_CANNOT_FIND_FACULTY_CRITERIA, e);
        } finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }
        return userMarks;
    }

    ///////////////////////////////////
    // application
    ///////////////////////////////////

    public void insertApplication(int userId, int facultyId) throws DBException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(SQL_INSERT_APPLICATION);
            int k = 0;
            preparedStatement.setInt(++k, userId);
            preparedStatement.setInt(++k, facultyId);
            preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            rollback(connection);
            LOG.error(Messages.ERR_CANNOT_INSERT_APPLICATION, e);
            throw new DBException(Messages.ERR_CANNOT_INSERT_APPLICATION, e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
    }

    public List<Application> findUserApplications(int userId) throws DBException {
        List<Application> applications = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_USER_APPLICATIONS);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                applications.add(extractApplication(resultSet));
            }
            connection.commit();
        } catch (SQLException e) {
            rollback(connection);
            LOG.error(Messages.ERR_CANNOT_FIND_USER_APPLICATIONS);
            throw new DBException(Messages.ERR_CANNOT_FIND_USER_APPLICATIONS, e);
        } finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }
        return applications;
    }

    ///////////////////////////////////
    // util methods
    ///////////////////////////////////


    private User extractUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt(Field.ID));
        user.setName(resultSet.getString(Field.USER_NAME));
        user.setSurname(resultSet.getString(Field.USER_SURNAME));
        user.setPatronymic(resultSet.getString(Field.USER_PATRONYMIC));
        user.setEmail(resultSet.getString(Field.USER_EMAIL));
        user.setPassword(resultSet.getString(Field.USER_PASSWORD));
        user.setRegion(resultSet.getString(Field.USER_REGION));
        user.setCity(resultSet.getString(Field.USER_CITY));
        user.setInstitutionName(resultSet.getString(Field.USER_INSTITUTION_NAME));
        user.setRoleId(resultSet.getInt(Field.USER_ROLE_ID));
        user.setStatusId(resultSet.getInt(Field.USER_STATUS_ID));
        return user;
    }

    private Faculty extractFaculty(ResultSet resultSet) throws SQLException, DBException {
        Faculty faculty = new Faculty();
        faculty.setId(resultSet.getInt(Field.ID));
        faculty.setNameEn(resultSet.getString(Field.FACULTY_NAME_EN));
        faculty.setNameUk(resultSet.getString(Field.FACULTY_NAME_UK));
        faculty.setTotalSeats(resultSet.getInt(Field.FACULTY_TOTAL_SEATS));
        faculty.setBudgetSeats(resultSet.getInt(Field.FACULTY_BUDGET_SEATS));

        return faculty;
    }

    private Criterion extractCriterion(ResultSet resultSet) throws SQLException {
        Criterion criterion = new Criterion();
        criterion.setId(resultSet.getInt(Field.CRITERIA_ID));
        criterion.setNameEn(resultSet.getString(Field.CRITERIA_NAME_EN));
        criterion.setNameUk(resultSet.getString(Field.CRITERIA_NAME_UK));
        return criterion;
    }

    private UserMark extractUserMark(ResultSet resultSet) throws SQLException {
        UserMark userMark = new UserMark();
        userMark.setUserId(resultSet.getInt(Field.USER_ID));
        userMark.setCriterionId(resultSet.getInt(Field.CRITERION_ID));
        userMark.setMark(resultSet.getInt(Field.MARK));
        return userMark;
    }

    private Application extractApplication(ResultSet resultSet) throws SQLException {
        Application application = new Application();
        application.setFacultyId(resultSet.getInt(Field.FACULTY_ID));
        application.setUserId(resultSet.getInt(Field.USER_ID));
        return application;
    }

    private void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOG.error(Messages.ERR_CANNOT_CLOSE_RESULTSET, e);
            }
        }
    }

    private void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                LOG.error(Messages.ERR_CANNOT_CLOSE_RESULTSET, e);
            }
        }
    }

    private void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                LOG.error(Messages.ERR_CANNOT_CLOSE_RESULTSET, e);
            }
        }
    }

    private void rollback(Connection connection) {
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                LOG.error(Messages.ERR_CANNOT_ROLLBACK_TRANSACTION, e);
            }
        }
    }


}
