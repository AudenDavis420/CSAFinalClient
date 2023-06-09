import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import com.google.gson.Gson;

import GameResources.*;

public class Client 
{
    //makes gson parser
    private static Gson gson = new Gson();

    //join HttpResponse
    public static Player joinGame(String ip) throws IOException, InterruptedException
    {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("http://"+ip+":8080/joinGame"))
            .build();

        

            HttpResponse<String> response = HttpClient.newBuilder()
            .build()
            .send(request, BodyHandlers.ofString());

            return gson.fromJson(response.body(), Player.class);//returns the Player for the joinGame request
    }


    //startGame HttpResponse
    public static Answer startGame(String ip, Player player) throws IOException, InterruptedException
    {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("http://"+ ip + ":8080/start/" + player.getPlayerID()))
            .build();

        

            HttpResponse<String> response = HttpClient.newBuilder()
            .build()
            .send(request, BodyHandlers.ofString());

            return gson.fromJson(response.body(), Answer.class);//returns the Answer for the startGame request
    }

    //getMap HttpResponse
    public static Game getMap(String ip, Player player) throws IOException, InterruptedException
    {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("http://"+ ip + ":8080/gameMap/" + player.getPlayerID()))
            .build();

        

            HttpResponse<String> response = HttpClient.newBuilder()
            .build()
            .send(request, BodyHandlers.ofString());

            return gson.fromJson(response.body(), Game.class);//returns the game for the getMap request
    }

    //makeMove HttpResponse
    public static Answer makeMove(String ip, Player player, MoveRequest moveRequest) throws IOException, InterruptedException
    {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("http://"+ ip + ":8080/makeMove/1"))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(moveRequest)))
            .build();
        
        System.out.println(moveRequest);
        System.out.println(gson.toJson(moveRequest));

            HttpResponse<String> response = HttpClient.newBuilder()
            .build()
            .send(request, BodyHandlers.ofString());

            return gson.fromJson(response.body(), Answer.class); //returns the answer for the makeMove request
    }


}
