package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private final Connection connection = Util.connection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = connection.createStatement()) {
            String table = "CREATE TABLE IF NOT EXISTS users (Id INT NOT NULL AUTO_INCREMENT, name  varchar(45) NOT NULL, lastName varchar(45) NOT NULL, age INT NOT NULL, PRIMARY KEY (id))";
            statement.execute(table);
        } catch (SQLException ignored) {
        }

    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            String drop = "DROP TABLE IF EXISTS users";
            statement.execute(drop);
        } catch (SQLException ignored) {
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String insert = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(insert)) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setInt(3, age);
            statement.execute();
            System.out.println("User " + name + " added successfully");
        } catch (SQLException ignored) {
        }
    }

    public void removeUserById(long id) {
        String delete = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(delete)) {
            statement.setLong(1, id);
            statement.execute();
        } catch (SQLException ignored) {
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            String getTable = "SELECT * FROM users";
            ResultSet getUsers = statement.executeQuery(getTable);
            while (getUsers.next()) {
                int Id = getUsers.getInt("Id");
                String name = getUsers.getString("name");
                String lastName = getUsers.getString("lastName");
                int age = getUsers.getInt("age");
                User user = new User(name, lastName, (byte) age);
                user.setId((long) Id);
                list.add(user);
            }
        } catch (SQLException ignored) {
        }
        return list;
    }

    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()) {
            String deleteAll = "DELETE FROM users";
            statement.execute(deleteAll);
        } catch (SQLException ignored) {
        }
    }
}
