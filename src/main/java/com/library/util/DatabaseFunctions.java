package com.library.util;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseFunctions {
    private static final Logger log = Logger.getLogger(DatabaseFunctions.class);

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/library", "root", "root");
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
        }
        return connection;
    }

    public static String create(String table, String fields, Statement state) {
        String result;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("CREATE TABLE ").append(table).append(" (").append(fields).append(")");
            state.executeUpdate(String.valueOf(sql));
            result = "Success";
            state.close();
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            result = e.getLocalizedMessage();
        }
        return result;
    }

    public static String insert(String table, String fields, String values, Statement state) {
        String result;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO ").append(table).append(" (").append(fields).append(") ");
            sql.append("VALUES (").append(values).append(")");
            state.executeUpdate(String.valueOf(sql));
            result = "Success";
            state.close();
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            result = e.getLocalizedMessage();
        }
        return result;
    }

    public static String update(String table, String set, String where, Statement state) {
        String result;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE ").append(table).append(" SET ").append(set).append(" ");
            if (where != null) {
                sql.append("WHERE ").append(where).append(" ");
            }
            state.executeUpdate(String.valueOf(sql));
            result = "Success";
            state.close();
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            result = e.getLocalizedMessage();
        }
        return result;
    }

    public static String delete(String table, String condition, Statement state) {
        String result;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("DELETE FROM ").append(table);
            if (condition != null) {
                sql.append(" WHERE ").append(condition);
            }
            state.executeUpdate(String.valueOf(sql));
            result = "Success";
            state.close();
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            result = e.getLocalizedMessage();
        }
        return result;
    }

    public static ResultSet select(String table, String fields, String condition, String orderBy, Statement state) {
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT ").append(fields);
            sql.append(" FROM ").append(table);
            if (condition != null) {
                sql.append(" WHERE ").append(condition);
            }
            if (orderBy != null) {
                sql.append(" ORDER BY ").append(orderBy);
            }
            return state.executeQuery(String.valueOf(sql));
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return null;
        }
    }
    public static ResultSet selectCountOrders(String table, String fields, String condition, Statement state) {
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT ").append(fields);
            sql.append(" FROM ").append(table);
            if (condition != null) {
                sql.append(" WHERE ").append(condition);
            }
            return state.executeQuery(String.valueOf(sql));
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return null;
        }
    }
}