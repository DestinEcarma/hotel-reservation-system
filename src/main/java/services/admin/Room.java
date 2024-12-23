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

/**
 *
 * @author maker
 */
public class Room {

    public static class Inputs {

        public String roomNumber;
        public String roomType;

        public Inputs(String roomNumber, String roomType) {
            this.roomNumber = roomNumber;
            this.roomType = roomType;
        }
    }

    private static class Queries {

        private static final String CREATE = "INSERT INTO rooms SET room_number = ?, room_type = ?";

        public static class Read {

            public static final String ALL = "SELECT * FROM rooms";
            public static final String FROM_ROOM_NUMBER = "SELECT * FROM rooms WHERE room_number = ?";
        }

        private static class Update {

            private static final String ROOM_NUMBER = "UPDATE rooms SET room_number = ? WHERE id = ?";
            private static final String ROOM_TYPE = "UPDATE rooms SET room_type = ? WHERE id = ?";
        }

        private static final String DELETE = "DELETE FROM rooms WHERE id = ?";
    }

    public final int id;
    public final String roomNumber;
    public final String roomType;
    public final boolean isAvailable;

    private Room(int id, String roomNumber, String roomType) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.isAvailable = true;
    }

    private Room(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getInt("id");
        this.roomNumber = resultSet.getString("room_number");
        this.roomType = resultSet.getString("room_type");
        this.isAvailable = resultSet.getBoolean("is_available");
    }

    public static Room createFrom(Inputs room) throws SQLException {
        try (PreparedStatement smt = Database.connection.prepareStatement(Queries.CREATE, Statement.RETURN_GENERATED_KEYS)) {
            smt.setString(1, room.roomNumber);
            smt.setString(2, room.roomType);

            if (smt.executeUpdate() == 1) {
                try (ResultSet generatedKeys = smt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int id = generatedKeys.getInt(1);

                        return new Room(id, room.roomNumber, room.roomType);
                    }
                }
            }
        }

        return null;
    }

    public static List<Room> getAll() throws SQLException {
        List<Room> list = new ArrayList();

        try (Statement smt = Database.connection.createStatement(); ResultSet res = smt.executeQuery(Queries.Read.ALL)) {

            while (res.next()) {
                list.add(new Room(res));
            }
        }

        return list;
    }

    public static Room fromRoomNumber(String roomNumber) throws SQLException {
        try (PreparedStatement smt = Database.connection.prepareStatement(Queries.Read.FROM_ROOM_NUMBER)) {
            smt.setString(1, roomNumber);

            try (ResultSet res = smt.executeQuery()) {
                if (res.next()) {
                    return new Room(res);
                }
            }
        }

        return null;
    }

    public static Room updateFromTo(int id, Inputs from, Inputs to) throws SQLException {
        if (!from.roomNumber.equals(to.roomNumber)) {
            try (PreparedStatement smt = Database.connection.prepareStatement(Queries.Update.ROOM_NUMBER)) {
                smt.setString(1, to.roomNumber);
                smt.setInt(2, id);

                if (smt.executeUpdate() == 0) {
                    to.roomNumber = from.roomNumber;
                }
            }
        }

        if (!from.roomType.equals(to.roomType)) {
            try (PreparedStatement smt = Database.connection.prepareStatement(Queries.Update.ROOM_TYPE)) {
                smt.setString(1, to.roomType);
                smt.setInt(2, id);

                if (smt.executeUpdate() == 0) {
                    to.roomType = from.roomType;
                }
            }
        }

        return new Room(
                id,
                to.roomNumber,
                to.roomType
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
