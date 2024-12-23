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

    public int id;
    public String name;
    public String contact;
    public String username;
    public int passwordLength;

    public static Staff createFrom(Inputs staff) {
        try {
            long id = Long.parseUnsignedLong(Api.postRequest("admin/staff", staff).body());

            return new ObjectMapper().readValue(Api.getRequest("admin/staff/" + id).body(), Staff.class);
        } catch (IOException | URISyntaxException | InterruptedException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static List<Staff> getAll() {
        try {
            return new ObjectMapper().readValue(Api.getRequest("admin/staff").body(), new TypeReference<List<Staff>>() {
            });
        } catch (IOException | URISyntaxException | InterruptedException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static Staff fromUsername(String username) {
        try {
            HttpResponse<String> res = Api.getRequest("admin/staff/username/" + username);

            if (res.statusCode() != 404) {
                return new ObjectMapper().readValue(res.body(), Staff.class);
            }
        } catch (IOException | URISyntaxException | InterruptedException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static Staff updateFromTo(int id, Inputs input) {
        try {
            HttpResponse<String> res = Api.putRequest("admin/staff/" + id, input);

            System.out.println(res.body());

            if (res.statusCode() != 404) {
                return new ObjectMapper().readValue(res.body(), Staff.class);
            }
        } catch (IOException | URISyntaxException | InterruptedException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static boolean deleteFromId(int id) {
        try {
            Api.deleteRequest("admin/staff/" + id).body();

            return true;
        } catch (IOException | URISyntaxException | InterruptedException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }
}
