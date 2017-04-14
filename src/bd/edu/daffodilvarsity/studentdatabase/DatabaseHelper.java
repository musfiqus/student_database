/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.edu.daffodilvarsity.studentdatabase;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author musfiqus
 */
public class DatabaseHelper {

    public static final String TABLE_ROOT = "root";
    public static final String TABLE_ADMIN = "admin";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";
    public static String DB_NAME = "users.db";

    public void createDB() {
        Connection connection = null;
        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:" + DB_NAME);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec
            statement.executeUpdate("drop table if exists " + TABLE_ADMIN);
            statement.executeUpdate("create table " + TABLE_ADMIN + " (id INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_USERNAME + " TEXT, " + COLUMN_PASSWORD + " TEXT)");
            statement.executeUpdate("drop table if exists " + TABLE_ROOT);
            statement.executeUpdate("create table " + TABLE_ROOT + " (id INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_USERNAME + " TEXT, " + COLUMN_PASSWORD + " TEXT)");
            statement.executeUpdate("INSERT INTO " + TABLE_ROOT + "("+COLUMN_USERNAME+", "+COLUMN_PASSWORD+") VALUES ('root', '" + MD5("masterpw") + "')");
        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println("createDB");
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e);
            }
        }
    }

    public int insertData(String username, String password, String table) {
        Connection connection = null;
        int validity = 0;
        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:" + DB_NAME);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec
            if(isUsernameValid(table,username)) {
                statement.executeUpdate("INSERT INTO " + table + "("+COLUMN_USERNAME+", "+COLUMN_PASSWORD+") VALUES ('" + username + "', '" + MD5(password) + "')");
                validity = 1;
            } else {
                validity = -1;
            }
            
        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println("Here goes");
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e);
            }
        }
        return validity;
    }
    
    public int addAdmin(String username, String password) {
        return insertData(username, password, TABLE_ADMIN);
    }
    
    public boolean isUsernameValid(String table, String username) {
        Connection connection = null;
        boolean matched = false;
        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:" + DB_NAME);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            ResultSet rs = statement.executeQuery("select * from " + table);
            while (rs.next()) {
                // read the result set
                if (rs.getString(COLUMN_USERNAME).equals(username)) {
                    System.out.println("KOPPA");
                    matched = true;
                }
            }
        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e);
            }

        }
        return !matched;
    }
    
    public boolean authenticate(String password) {
        return authenticate(TABLE_ROOT, "root", password);
    }

    public boolean authenticate(String username, String password) {
        if (username.equals("root")) {
            return authenticate(password);
        }
        return authenticate(TABLE_ADMIN, username, password);
    }
    
    public boolean authenticate(String table, String username, String password) {
        Connection connection = null;
        boolean matched = false;
        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:" + DB_NAME);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            ResultSet rs = statement.executeQuery("select * from " + table);
            while (rs.next()) {
                // read the result set
                if (rs.getString(COLUMN_USERNAME).equals(username) && rs.getString(COLUMN_PASSWORD).equals(MD5(password))) {
                    System.out.println("KOPPA");
                    matched = true;
                }
            }
        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e);
            }

        }
        return matched;
    }

    public boolean checkDBFile() {
        Path path = Paths.get(DB_NAME);

        if (Files.exists(path)) {
            System.out.println("Exists");
            return true;
        } else if (Files.notExists(path)) {
            return false;
        } else {
            return false;
        }
    }

    private String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes(Charset.forName("UTF-8")));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            System.err.println(e);
        }
        return null;
    }
}
