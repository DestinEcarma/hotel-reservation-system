/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services.admin;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import shared.Api;
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

    public int id;
    public String roomNumber;
    public String roomType;

    public static Room createFrom(Inputs room) {
        try {
            long id = Long.parseUnsignedLong(Api.postRequest("admin/room", room).body());

            return new ObjectMapper().readValue(Api.getRequest("admin/room/" + id).body(), Room.class);
        } catch (IOException | URISyntaxException | InterruptedException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static List<Room> getAll() {
        try {
            return new ObjectMapper().readValue(Api.getRequest("admin/room").body(), new TypeReference<List<Room>>() {
            });
        } catch (IOException | URISyntaxException | InterruptedException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static Room fromRoomNumber(String roomNumber) {
        try {
            HttpResponse<String> res = Api.getRequest("admin/room/number/" + roomNumber);
            
            if (res.statusCode() != 404) {
                return new ObjectMapper().readValue(res.body(), Room.class);
            }
        } catch (IOException | URISyntaxException | InterruptedException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static Room updateFromTo(int id, Inputs input) {
        try {
            HttpResponse<String> res = Api.putRequest("admin/room/" + id, input);
            
            if (res.statusCode() != 404) {
                return new ObjectMapper().readValue(res.body(), Room.class);
            }
        } catch (IOException | URISyntaxException | InterruptedException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static boolean deleteFromId(int id) {
        try {
            Api.deleteRequest("admin/room/" + id).body();

            return true;
        } catch (IOException | URISyntaxException | InterruptedException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }
}
