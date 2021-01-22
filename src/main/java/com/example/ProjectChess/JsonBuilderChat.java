package com.example.ProjectChess;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import java.io.StringWriter;
import java.util.concurrent.Callable;

public class JsonBuilderChat implements Callable<String> {
    private String username;
    private String message;

    public JsonBuilderChat(String username, String message) {
        this.username = username;
        this.message = message;
    }

    public synchronized String buildJsonData(String username, String message) throws InterruptedException {
        if(username.equals("") && message.equals("")){
            wait();
        }
        notifyAll();
        JsonObject jsonObject = Json.createObjectBuilder().add("message",username + ": " + message).build();
        System.out.println(username + " " + message);
        StringWriter stringWriter = new StringWriter();
        try(JsonWriter jsonWriter = Json.createWriter(stringWriter)) {
            jsonWriter.write(jsonObject);
        }
        return  stringWriter.toString();
    }

    @Override
    public String call() throws Exception {
        return buildJsonData(this.username,this.message);
    }
}
