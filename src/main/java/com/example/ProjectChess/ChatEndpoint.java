package com.example.ProjectChess;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@ServerEndpoint(value = "/chat")
public class ChatEndpoint {
    static Set<Session> chatroomUsers = Collections.synchronizedSet(new HashSet<Session>());
    ExecutorService threadExecutor = Executors.newCachedThreadPool();
    private JsonBuilderChat jsonBuilderChat;

    @OnOpen
    public void handleOpen(Session userSession){
        chatroomUsers.add(userSession);
    }

    @OnClose
    public void handleClose(Session userSession){
        chatroomUsers.remove(userSession);
    }

    @OnMessage
    public void handleMessage(String message,Session userSession) throws IOException, ExecutionException, InterruptedException {
        String username = (String) userSession.getUserProperties().get("username");
        System.out.println(username);
        if(username==null){
            userSession.getUserProperties().put("username",message);
            this.jsonBuilderChat = new JsonBuilderChat("System","you are connected as " + message);
            Future<String> futureCall = threadExecutor.submit(this.jsonBuilderChat);
            String newMessage = futureCall.get();
            userSession.getBasicRemote().sendText(newMessage);
        }
        else{
            Iterator<Session> iterator = chatroomUsers.iterator();
            while(iterator.hasNext()) {
                System.out.println(message);
                if (!(message.equals(""))) {
                    this.jsonBuilderChat = new JsonBuilderChat(username,message);
                    Future<String> futureCall = threadExecutor.submit(this.jsonBuilderChat);
                    String newMessage = futureCall.get();
                    iterator.next().getBasicRemote().sendText(newMessage);
                }
                else break;
            }
        }
    }
}
