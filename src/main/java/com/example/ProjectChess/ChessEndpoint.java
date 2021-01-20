package com.example.ProjectChess;

import com.github.bhlangonijr.chesslib.Board;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.servlet.http.HttpSession;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@ServerEndpoint(value = "/chess")
public class ChessEndpoint {
    static Set<Session> chessRoomUsers = Collections.synchronizedSet(new HashSet<Session>());
    private static Board board = new Board();

    @OnOpen
    public void handleOpen(Session userSession){
        chessRoomUsers.add(userSession);
    }

    @OnClose
    public void handleClose(Session userSession){
        chessRoomUsers.remove(userSession);
    }

    @OnMessage
    public void handleMove(String move,Session userSession) throws IOException {
        String[] tokens = move.split(" ");
        System.out.println(tokens[0] + " " + tokens[1]);
        Iterator<Session> iterator = chessRoomUsers.iterator();
        while(iterator.hasNext()) {
            System.out.println(move);
            if (!(move.equals(""))) {
                iterator.next().getBasicRemote().sendText(buildJsonData(tokens[0],tokens[1]));
            }
            else break;
        }
    }

    public String buildJsonData(String moveFrom,String moveTo){
        JsonObject jsonObject = Json.createObjectBuilder()
                .add("moveFrom",moveFrom)
                .add("moveTo",moveTo).build();
        System.out.println(moveFrom + " " + moveTo);
        StringWriter stringWriter = new StringWriter();
        try(JsonWriter jsonWriter = Json.createWriter(stringWriter)) {
            jsonWriter.write(jsonObject);
        }
        return  stringWriter.toString();
    }
}
