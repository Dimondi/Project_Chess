package com.example.ProjectChess;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
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

@ServerEndpoint(value = "/chat")
public class ChatEndpoint {
    static Set<Session> chatroomUsers = Collections.synchronizedSet(new HashSet<Session>());

    @OnOpen
    public void handleOpen(Session userSession){
        chatroomUsers.add(userSession);
    }

    @OnClose
    public void handleClose(Session userSession){
        chatroomUsers.remove(userSession);
    }

    @OnMessage
    public void handleMessage(String message,Session userSession) throws IOException {
        String username = (String) userSession.getUserProperties().get("username");
        System.out.println(username);
        if(username==null){
            userSession.getUserProperties().put("username",message);
            userSession.getBasicRemote().sendText(buildJsonData("System","you are connected as " + message));
        }
        else{
            Iterator<Session> iterator = chatroomUsers.iterator();
            while(iterator.hasNext()) {
                System.out.println(message);
                if (!(message.equals(""))) {
                    iterator.next().getBasicRemote().sendText(buildJsonData(username, message));
                }
                else break;
            }
        }
    }

    public String buildJsonData(String username,String message){
        JsonObject jsonObject = Json.createObjectBuilder().add("message",username + ": " + message).build();
        System.out.println(username + " " + message);
        StringWriter stringWriter = new StringWriter();
        try(JsonWriter jsonWriter = Json.createWriter(stringWriter)) {
            jsonWriter.write(jsonObject);
        }
        return  stringWriter.toString();
    }
}
