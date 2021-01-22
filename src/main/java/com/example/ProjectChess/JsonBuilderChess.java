package com.example.ProjectChess;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import java.io.StringWriter;
import java.util.concurrent.Callable;

public class JsonBuilderChess implements Callable<String> {
    private String moveFrom;
    private String moveTo;

    public JsonBuilderChess(String moveFrom, String moveTo) {
        this.moveFrom = moveFrom;
        this.moveTo = moveTo;
    }

    public synchronized String buildJsonData(String moveFrom, String moveTo) throws InterruptedException {
        if(moveFrom.equals("") && moveTo.equals("")){
            wait();
        }
        notifyAll();
        JsonObject jsonObject = Json.createObjectBuilder()
                .add("moveFrom", moveFrom)
                .add("moveTo", moveTo).build();
        System.out.println(moveFrom + " " + moveTo);
        StringWriter stringWriter = new StringWriter();
        try (JsonWriter jsonWriter = Json.createWriter(stringWriter)) {
            jsonWriter.write(jsonObject);
        }
        return stringWriter.toString();
    }

    @Override
    public String call() throws Exception {
        return buildJsonData(this.moveFrom,this.moveTo);
    }
}
