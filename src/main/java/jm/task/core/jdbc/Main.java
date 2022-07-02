package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

public class Main {
    public static void main(String[] args) {
        UserDaoJDBCImpl user = new UserDaoJDBCImpl();
        user.createUsersTable();
        user.saveUser("Name1", "lastName1", (byte) 20);
        user.saveUser("Name2", "lastName2", (byte) 45);
        user.saveUser("Name3", "lastName3", (byte) 55);
        user.saveUser("Name4", "lastName4", (byte) 25);
        System.out.println(user.getAllUsers());
        user.cleanUsersTable();
        user.dropUsersTable();
    }
}
