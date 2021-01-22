package com.example.ProjectChess;

import com.github.bhlangonijr.chesslib.Board;
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

@ServerEndpoint(value = "/chess")
public class ChessEndpoint {
    static Set<Session> chessRoomUsers = Collections.synchronizedSet(new HashSet<Session>());
    ExecutorService threadExecutor = Executors.newCachedThreadPool();
    private JsonBuilderChess jsonBuilderChess;
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
    public void handleMove(String move,Session userSession) throws IOException, ExecutionException, InterruptedException {
        String[] tokens = move.split(" ");
        System.out.println(tokens[0] + " " + tokens[1]);
        Iterator<Session> iterator = chessRoomUsers.iterator();
        while(iterator.hasNext()) {
            System.out.println(move);
            if (!(move.equals(""))) {
                this.jsonBuilderChess = new JsonBuilderChess(tokens[0],tokens[1]);
                Future<String> futureCall = threadExecutor.submit(this.jsonBuilderChess);
                String newMessage = futureCall.get();
                iterator.next().getBasicRemote().sendText(newMessage);
            }
            else break;
        }
    }
}
