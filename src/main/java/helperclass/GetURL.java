package helperclass;

import io.github.cdimascio.dotenv.Dotenv;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GetURL {

    private final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();

    public static String main(String args) throws Exception {
        GetURL obj = new GetURL();

        System.out.println("Testing 1 - Send Http GET request");
        System.out.println(obj.sendGet(args));
        return obj.sendGet(args);
    }

    private String sendGet(String link) throws Exception {
        Dotenv dotenv = Dotenv.configure()
                .directory("src/assets")
                .load();


        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(link))
                .setHeader("Accept", "application/json")
                .setHeader("apikey",  dotenv.get("AIRLY"))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.statusCode());
        return response.body();
    }
}