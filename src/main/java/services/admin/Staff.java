/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services.admin;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import shared.Database;
import shared.PasswordUtil;

/**
 *
 * @author maker
 */
public class Staff {
    public static class Inputs {
        public String name;
        public String contact;
        public String username;
        public String password;
        
        public Inputs(String name, String contact, String username, String password) {
            this.name = name;
            this.contact = contact;
            this.username = username;
            this.password = password;
        }
    }
    
    private static class Queries {
        private static final String CREATE = "INSERT INTO staffs SET name = ?, contact = ?, username = ?, password_hash = ?, password_length = ?";
        
        private static class Read {
            public static final String ALL = "SELECT * FROM staffs";
            public static final String FROM_USERNAME = "SELECT * FROM staffs WHERE username = ?";
        }
        
        private static class Update {
            private static final String NAME = "UPDATE staffs SET name = ? WHERE id = ?";
            private static final String CONTACT = "UPDATE staffs SET contact = ? WHERE id = ?";
            private static final String USERNAME = "UPDATE staffs SET username = ? WHERE id = ?";
            private static final String PASSWORD = "UPDATE staffs SET password_hash = ?, password_length = ? WHERE id = ?";
        }
        
        private static final String DELETE = "DELETE FROM staffs WHERE id = ?";
    }
    
    public final int id;
    public final String name;
    public final String contact;
    public final String username;
    public final int passwordLength;
    public String passwordHash;
    
    private Staff(int id, String name, String contact, String username, int passwordLength) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.username = username;
        this.passwordLength = passwordLength;
    }
    
    private Staff(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getInt("id");
        this.name = resultSet.getString("name");
        this.contact = resultSet.getString("contact");
        this.username = resultSet.getString("username");
        this.passwordHash = resultSet.getString("password_hash");
        this.passwordLength = resultSet.getInt("password_length");
    }
    
    public static Staff createFrom(Inputs staff) throws SQLException {
        try (PreparedStatement smt = Database.connection.prepareStatement(Queries.CREATE, Statement.RETURN_GENERATED_KEYS)) {
            smt.setString(1, staff.name);
            smt.setString(2, staff.contact);
            smt.setString(3, staff.username);
            smt.setString(4, PasswordUtil.passwordHash(staff.password));
            smt.setInt(5, staff.password.length());

            if (smt.executeUpdate() == 1) {
                try (ResultSet generatedKeys = smt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int id = generatedKeys.getInt(1);

                        return new Staff(
                            id,
                            staff.name,
                            staff.contact,
                            staff.username,
                            staff.password.length()
                        );
                    }
                }
            }
        }
        
        return null;
    }

    public static List<Staff> getAll() throws SQLException {
        List<Staff> list = new ArrayList();
        
        try (Statement smt = Database.connection.createStatement();
            ResultSet res = smt.executeQuery(Queries.Read.ALL)) {
        
            while (res.next()) {
                list.add(new Staff(res));
            }
        }

        return list;
    }

    public static Staff fromUsername(String username) throws SQLException {
        try (PreparedStatement smt = Database.connection.prepareStatement(Queries.Read.FROM_USERNAME)) {
            smt.setString(1, username);
            
            try (ResultSet res = smt.executeQuery()) {
                if (res.next()) {
                    return new Staff(res);
                }
            }
        }

        return null;
    }
    
    public static Staff updateFromTo(int id, Inputs from, Inputs to) throws SQLException {
        if (!from.name.equals(to.name)) {
            try (PreparedStatement smt = Database.connection.prepareStatement(Queries.Update.NAME)) {
                smt.setString(1, to.name);
                smt.setInt(2, id);

                if (smt.executeUpdate() == 0) {
                    to.name = from.name;
                }
            }
        }
        
        if (!from.contact.equals(to.contact)) {
            try (PreparedStatement smt = Database.connection.prepareStatement(Queries.Update.CONTACT)) {
                smt.setString(1, to.contact);
                smt.setInt(2, id);

                if (smt.executeUpdate() == 0) {
                    to.contact = from.contact;
                }
            }
        }
        
        if (!from.username.equals(to.username)) {
            try (PreparedStatement smt = Database.connection.prepareStatement(Queries.Update.USERNAME)) {
                smt.setString(1, to.username);
                smt.setInt(2, id);

                if (smt.executeUpdate() == 0) {
                    to.username = from.username;
                }
            }
        }
        
        if (!from.password.equals(to.password)) {
            try (PreparedStatement smt = Database.connection.prepareStatement(Queries.Update.PASSWORD)) {
                smt.setString(1, PasswordUtil.passwordHash(to.password));
                smt.setInt(2, to.password.length());
                smt.setInt(3, id);

                if (smt.executeUpdate() == 0) {
                    to.password = from.password;
                }
            }
        }
        
        return new Staff(
            id,
            to.name,
            to.contact,
            to.username,
            to.password.length()
        );
    }
    
    public static boolean deleteFromId(int id) throws SQLException {
        try (PreparedStatement smt = Database.connection.prepareStatement(Queries.DELETE)) {
            smt.setInt(1, id);
            
            if (smt.executeUpdate() == 1) {
                return true;
            } 
        }
            
        return false;
    }
}