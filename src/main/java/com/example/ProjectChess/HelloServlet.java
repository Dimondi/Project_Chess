package com.example.ProjectChess;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.websocket.WebSocketContainer;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter("name");
        System.out.println("");
        HttpSession session=request.getSession();
        session.setAttribute("username",name);
        request.setAttribute("name",name);
        request.getRequestDispatcher("index.jsp").forward(request,response);
    }

    public void destroy() {
    }
}