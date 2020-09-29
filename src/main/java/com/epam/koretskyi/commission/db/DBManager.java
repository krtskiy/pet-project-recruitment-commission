package com.epam.koretskyi.commission.db;

import com.epam.koretskyi.commission.constant.Fields;
import com.epam.koretskyi.commission.db.entity.User;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;

/**
 * @author D.Koretskyi on 22.09.2020.
 */
public class DBManager {

    /////////////////////////
    /////// singleton ///////
    /////////////////////////

    private static DBManager dbManager;

    private DBManager() {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            dataSource = (DataSource) envContext.lookup("jdbc/commission");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public static synchronized DBManager getInstance() {
        if (dbManager == null) {
            dbManager = new DBManager();
        }
        return dbManager;
    }

    private DataSource dataSource;

    private static final String SQL_INSERT_USER = "INSERT INTO users VALUES (DEFAULT, ?, ?, ?, ?, ?, DEFAULT , DEFAULT)";
    private static final String SQL_FIND_USER_BY_EMAIL = "SELECT * FROM users WHERE email=?";

    private Connection getConnection() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void insertUser(User user) {
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
            preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            rollback(connection);
            e.printStackTrace();
        } finally {
            close(preparedStatement);
            close(connection);
        }
    }

    public User findUserByEmail(String email) {
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
            e.printStackTrace();
        } finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }
        return user;
    }

    ////////////////////////
    ///// Util methods /////
    ////////////////////////

    private User extractUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt(Fields.ID));
        user.setName(resultSet.getString(Fields.USER_NAME));
        user.setSurname(resultSet.getString(Fields.USER_SURNAME));
        user.setPatronymic(resultSet.getString(Fields.USER_PATRONYMIC));
        user.setEmail(resultSet.getString(Fields.USER_EMAIL));
        user.setPassword(resultSet.getString(Fields.USER_PASSWORD));
        user.setRoleId(resultSet.getInt(Fields.USER_ROLE_ID));
        user.setStatusId(resultSet.getInt(Fields.USER_STATUS_ID));
        return user;
    }

    private void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void rollback(Connection connection) {
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
