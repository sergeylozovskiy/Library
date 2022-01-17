package com.library.service;

import com.library.entity.Book;
import com.library.entity.User;
import com.library.enums.Role;
import com.library.util.DatabaseFunctions;
import com.library.util.PasswordEncryption;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    private Connection connection;
    private static final Logger log = Logger.getLogger(OrdersService.class);

    public UserService(Connection connection) {
        this.connection = connection;
    }

    public List<User> findAllUsers(String condition, String orderBy) {
        List<User> userList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = DatabaseFunctions.select("users", " * ", condition, orderBy, statement);
            if (rs != null) {
                userList = getUsersFromResultSet(rs);
                rs.close();
            }
            statement.close();
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
        }
        return userList;
    }

    public String addUser(User user) {
        String result;
        try {
            Statement statement = connection.createStatement();
            result = DatabaseFunctions.insert("users",
                    "login,password,role,name_en,name_ru,surname_en,surname_ru,patronymic_en,patronymic_ru,active",
                    "'" + user.getLogin() + "'," +
                            "'" + PasswordEncryption.hashPassword(user.getPassword()) + "'," +
                            "'" + user.getRoleString() + "'," +
                            "'" + user.getNameEn() + "'," +
                            "'" + user.getNameRu() + "'," +
                            "'" + user.getSurnameEn() + "'," +
                            "'" + user.getSurnameRu() + "'," +
                            "'" + user.getPatronymicEn() + "'," +
                            "'" + user.getPatronymicRu() + "',true",
                    statement);
            statement.close();
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            result = e.getLocalizedMessage();
        }
        return result;
    }

    public List<User> searchUsers(String condition, String orderBy) {
        List<User> userList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = DatabaseFunctions.select("users", " * ", condition, orderBy, statement);
            if (rs != null) {
                userList = getUsersFromResultSet(rs);
                rs.close();
            }
            statement.close();
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
        }
        return userList;
    }

    public String blockUser(int id, boolean active) {
        String result;
        try {
            Statement statement = connection.createStatement();
            result = DatabaseFunctions.update("users", " active = " + active, "id = " + id, statement);
            statement.close();
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            result = e.getLocalizedMessage();
        }
        return result;
    }

    public String editUser(User user) {
        String result;
        try {
            Statement statement = connection.createStatement();
            result = DatabaseFunctions.update("users",
                    "login='" + user.getLogin() + "'," +
                            "role ='" + user.getRoleString() + "'," +
                            "name_en='" + user.getNameEn() + "'," +
                            "name_ru='" + user.getNameRu() + "'," +
                            "surname_en='" + user.getSurnameEn() + "'," +
                            "surname_ru='" + user.getSurnameRu() + "'," +
                            "patronymic_en='" + user.getPatronymicEn() + "'," +
                            "patronymic_ru='" + user.getPatronymicRu() + "'," +
                            "active=" + user.isActive(),
                    "id = " + user.getId(), statement);
            statement.close();
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            result = e.getLocalizedMessage();
        }
        return result;
    }

    public String deleteUser(int id) {
        String result="none";
        try {
            if (id != 1) {
                Statement statement = connection.createStatement();
                result = DatabaseFunctions.delete("users", "id = " + id, statement);
                statement.close();
            }
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            result = e.getLocalizedMessage();
        }
        return result;
    }

    public User getUserByLogin(String login) {
        User user = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = DatabaseFunctions.select("users", " * ", "login = '" + login + "'", null, statement);
            if (rs != null) {
                List<User> userList = getUsersFromResultSet(rs);
                if (userList != null && userList.size() > 0) {
                    user = userList.get(0);
                }
                rs.close();
            }
            statement.close();
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
        }
        return user;
    }

    public User getUserById(int id) {
        User user = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = DatabaseFunctions.select("users", " * ", "id = " + id, null, statement);
            if (rs != null) {
                List<User> userList = getUsersFromResultSet(rs);
                if (userList != null && userList.size() > 0) {
                    user = userList.get(0);
                }
                rs.close();
            }
            statement.close();
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
        }
        return user;
    }

    private List<User> getUsersFromResultSet(ResultSet rs) throws SQLException {
        List<User> userList = new ArrayList<>();
        while (rs.next()) {
            User u = new User();
            u.setId(rs.getInt("id"));
            u.setActive(rs.getBoolean("active"));
            u.setSurnameRu(rs.getString("surname_ru"));
            u.setSurnameEn(rs.getString("surname_en"));
            u.setLogin(rs.getString("login"));
            u.setPassword(rs.getString("password"));
            u.setNameRu(rs.getString("name_ru"));
            u.setNameEn(rs.getString("name_en"));
            u.setPatronymicRu(rs.getString("patronymic_ru"));
            u.setPatronymicEn(rs.getString("patronymic_en"));
            u.setRole(getRole(rs.getString("role")));
            userList.add(u);
        }
        return userList;
    }

    private Role getRole(String roleString) {
        switch (roleString) {
            case "LIBRARIAN":
                return Role.LIBRARIAN;
            case "ADMINISTRATOR":
                return Role.ADMINISTRATOR;
            default:
                return Role.VIEWER;
        }
    }

}
