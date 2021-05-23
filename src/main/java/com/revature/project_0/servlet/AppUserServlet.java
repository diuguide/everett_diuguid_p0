package com.revature.project_0.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.project_0.dao.UserDAO;
import com.revature.project_0.models.AppUser;
import com.revature.project_0.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * Servlet Hierarchy
 *
 *
 *
 */

public class AppUserServlet extends HttpServlet {

    private UserService userService = new UserService(new UserDAO());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Need to register a user
        // 1. Gather information out of the request send by the form
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String firstName = req.getParameter("firstname");
        String lastName = req.getParameter("lastname");

        // 2. Construct an AppUser with that information

        AppUser newUser = new AppUser(firstName, lastName, username, password);

        // 3. Save the user in the database with the register method

        userService.register(newUser);

        // 4. optional: create method for sending actual information back to the client
        // Status code levels
        // 100 - Information
        // 200 - Ok
        // 300 - Redirect
        // 400 - Client side error
        // 500 - Server side error
        resp.setStatus(202);

        // This will get the PrintWriter associated with the Response.  This PrintWriter will ... write
        //          to the body of the response
        resp.getWriter().print("Hello out there. Your user has been created");
    }

    // Here we are going to authenticate a user taken from a json in the request body
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // json stands for javascript object notation

        // Read body of the request
        InputStream json = req.getInputStream();

        // parse json
        Map<String, Object> jsonMap = new ObjectMapper().readValue(json, HashMap.class);
        System.out.println(jsonMap);

        // call authenticate with username and password from json
        AppUser user = userService.authenticate(jsonMap.get("username").toString(), jsonMap.get("password").toString());

        // return appropriate response
        if(user == null) {
            resp.getWriter().println("Please check your credentials");
        } else {
            resp.getWriter().println("Succesfully Logged In");
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Redirect, will send client to other servers
        resp.sendRedirect("https://www.reddit.com");

    }
}
