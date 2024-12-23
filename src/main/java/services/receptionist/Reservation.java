/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services.receptionist;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import shared.Api;
import shared.Database;

/**
 *
 * @author maker
 */
public class Reservation {

    public static class Inputs {

        public String name;
        public String contact;
        public String roomNumber;
        public String startDate;
        public String endDate;

        public Inputs(String clientName, String clientContact, String roomNumber, String startDate, String endDate) {
            this.name = clientName;
            this.contact = clientContact;
            this.roomNumber = roomNumber;
            this.startDate = startDate;
            this.endDate = endDate;
        }
    }

    public int id;
    public String customer;
    public String roomNumber;
    public String startDate;
    public String endDate;
    public boolean paymentMade;

    public static boolean createFrom(Inputs input) throws SQLException {
        try {
            Api.postRequest("receptionist/reservation", input).body();

            return true;
        } catch (IOException | URISyntaxException | InterruptedException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public static List<Reservation> getAll() throws SQLException {
        try {
            return new ObjectMapper().readValue(Api.getRequest("receptionist/reservation").body(), new TypeReference<List<Reservation>>() {
            });
        } catch (IOException | URISyntaxException | InterruptedException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
}
