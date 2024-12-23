/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shared;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import static shared.Defs.dotenv;

/**
 *
 * @author User
 */
public class Api {
    public static HttpResponse<String> getRequest(String path) throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(dotenv.get("API_HOST") + path))
                .GET()
                .build();

        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public static HttpResponse<String> postRequest(String path, Object object) throws URISyntaxException, IOException, InterruptedException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String payload = ow.writeValueAsString(object);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(dotenv.get("API_HOST") + path))
                .POST(HttpRequest.BodyPublishers.ofString(payload))
                .build();

        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public static HttpResponse<String> putRequest(String path, Object object) throws URISyntaxException, IOException, InterruptedException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String payload = ow.writeValueAsString(object);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(dotenv.get("API_HOST") + path))
                .PUT(HttpRequest.BodyPublishers.ofString(payload))
                .build();

        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public static HttpResponse<String> deleteApiRequest(String path) throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(dotenv.get("API_HOST") + path))
                .DELETE()
                .build();

        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
