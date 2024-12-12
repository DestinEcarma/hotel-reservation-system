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
import shared.Session;

/**
 *
 * @author maker
 */
public class Reservation {
    public static class Inputs {
        public String clientName;
        public String clientContact;
        public String roomNumber;
        public String startDate;
        public String endDate;
        
        public Inputs(String clientName, String clientContact, String roomNumber, String startDate, String endDate) {
            this.clientName = clientName;
            this.clientContact = clientContact;
            this.roomNumber = roomNumber;
            this.startDate = startDate;
            this.endDate = endDate;
        }
    }
    
    private static class Queries {
        private static class Read {
            private static final String ALL = "SELECT re.*, c.name AS customer, ro.room_number AS room_number FROM reservations re LEFT JOIN customers c ON re.customer_id = c.id LEFT JOIN rooms ro ON re.room_id = ro.id";
        }
        
        private static class Create {
            private static final String PART_1 = "INSERT INTO customers SET name = ?, contact = ?";
            private static final String PART_2 = "INSERT INTO reservations SET customer_id = ?, room_id = (SELECT id FROM rooms WHERE room_number = ?), handled_by = ?, start_date = ?, end_date = ?";
        }
    }
    
    public final int id;
    public final String customer;
    public final String roomNumber;
    public final String startDate;
    public final String endDate;
    public final boolean paymentMade;
    
    private Reservation(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getInt("id");
        this.customer = resultSet.getString("customer");
        this.roomNumber = resultSet.getString("room_number");
        this.startDate = resultSet.getString("start_date");
        this.endDate = resultSet.getString("end_date");
        this.paymentMade = resultSet.getBoolean("payment_made");
    }
    
    public static boolean createFrom(Inputs input) throws SQLException {
        try (PreparedStatement customerSMT = Database.connection.prepareStatement(Queries.Create.PART_1, Statement.RETURN_GENERATED_KEYS)) {
            customerSMT.setString(1, input.clientName);
            customerSMT.setString(2, input.clientContact);
            
            if (customerSMT.executeUpdate() == 0) {
                return false;
            }
            
            try (ResultSet generatedKeys = customerSMT.getGeneratedKeys()) {
                if (!generatedKeys.next()) {
                    return false;
                }
                
                int id = generatedKeys.getInt(1);
                
                try (PreparedStatement reservationSMT = Database.connection.prepareStatement(Queries.Create.PART_2)) {
                    reservationSMT.setInt(1, id);
                    reservationSMT.setString(2, input.roomNumber);
                    reservationSMT.setInt(3, Session.userId);
                    reservationSMT.setString(4, input.startDate);
                    reservationSMT.setString(5, input.endDate);
                    
                    if (reservationSMT.executeUpdate() == 0) {
                        return false;
                    }
                }
            }
        }
        
        return true;
    }
    
    public static List<Reservation> getAll() throws SQLException {
        List<Reservation> list = new ArrayList();
        
        try (Statement smt = Database.connection.createStatement();
            ResultSet res = smt.executeQuery(Queries.Read.ALL)) {
        
            while (res.next()) {
                list.add(new Reservation(res));
            }
        }
        
        return list;
    }
}
