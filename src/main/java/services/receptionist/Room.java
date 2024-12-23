package services.receptionist;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import shared.Database;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author maker
 */
public class Room {

    private static class Queries {

        private static class Read {

            private static final String NOT_IN_PERIOD_RANGE = "SELECT ro.* FROM rooms ro LEFT JOIN reservations re ON ro.id = re.room_id WHERE ro.room_type = ? AND (re.start_date IS NULL OR( re.start_date NOT BETWEEN ? AND ? AND re.end_date NOT BETWEEN ? AND ?))";
        }
    }

    public final int id;
    public final String roomNumber;
    public final String roomType;
    public final boolean isAvailable;

    private Room(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getInt("id");
        this.roomNumber = resultSet.getString("room_number");
        this.roomType = resultSet.getString("room_type");
        this.isAvailable = resultSet.getBoolean("is_available");
    }

    public static List<Room> getNotInPeriodRangeFromRoomType(String roomType, String startDate, String endDate) throws SQLException {
        List<Room> list = new ArrayList();

        try (PreparedStatement smt = Database.connection.prepareStatement(Queries.Read.NOT_IN_PERIOD_RANGE)) {
            smt.setString(1, roomType);
            smt.setString(2, startDate);
            smt.setString(3, endDate);
            smt.setString(4, startDate);
            smt.setString(5, endDate);

            try (ResultSet res = smt.executeQuery()) {
                while (res.next()) {
                    list.add(new Room(res));
                }
            }
        }

        return list;
    }

}
