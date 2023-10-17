package com.example.d5_web_apps;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/about-me")
public class HelloServlet extends HttpServlet {
    private String message;
    private String name;
    private String interests;

    public void init() {
        System.out.println("HelloServlet initialized");
        message = "About Me";
        name = "Ethan";
        interests = "cooking, sports, anime";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println(String.format("<p>%s</p>", name));
        out.println(String.format("<p>%s</p>", interests));
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("received request");
//        System.out.println(req.getParameter("lyrics"));
        resp.setContentType("text/plain");
        PrintWriter out = resp.getWriter();

        out.println(req.getParameter("lyrics"));
    }

    public void destroy() {
        System.out.println("HelloServlet destroyed");
    }
}