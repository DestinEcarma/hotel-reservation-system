/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services.receptionist;

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
public class Inquiry {

    public enum Status {
        Pending,
        Accepted,
        Declined,
    }

    public static class Inputs {

        public String clientName;
        public String clientContact;
        public String roomType;
        public String startDate;
        public String endDate;

        public Inputs(String clientName, String clientContact, String roomType, String startDate, String endDate) {
            this.clientName = clientName;
            this.clientContact = clientContact;
            this.roomType = roomType;
            this.startDate = startDate;
            this.endDate = endDate;
        }
    }

    private static class Queries {

        private static class Read {

            public static final String ALL = "SELECT * FROM inquiries WHERE status = 'Pending'";
            public static final String FROM_USERNAME = "SELECT * FROM staffs WHERE username = ?";
        }

        private static String UDPATE = "UPDATE inquiries SET status = ? WHERE id = ?";
    }

    public final int id;
    public final String clientName;
    public final String clientContact;
    public final String roomType;
    public final String startDate;
    public final String endDate;

    private Inquiry(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getInt("id");
        this.clientName = resultSet.getString("client_name");
        this.clientContact = resultSet.getString("client_contact");
        this.roomType = resultSet.getString("room_type");
        this.startDate = resultSet.getString("start_date");
        this.endDate = resultSet.getString("end_date");
    }

    public static List<Inquiry> getAll() throws SQLException {
        List<Inquiry> list = new ArrayList();

        try (Statement smt = Database.connection.createStatement(); ResultSet res = smt.executeQuery(Queries.Read.ALL)) {

            while (res.next()) {
                list.add(new Inquiry(res));
            }
        }

        return list;
    }

    public static boolean updateStatus(int id, Status status) throws SQLException {
        try (PreparedStatement smt = Database.connection.prepareStatement(Queries.UDPATE)) {
            smt.setString(1, status.name());
            smt.setInt(2, id);

            if (smt.executeUpdate() == 1) {
                return true;
            }
        }

        return false;
    }
}
