/*
 * modified by rChi:P
 */
package servlets;

import java.io.*;
import java.util.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * This is a simple example of an HTTP Servlet.  It responds to the GET
 * method of the HTTP protocol.
 */
public class ResponseServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        // then write the data of the response
        String username = request.getParameter("username");

        if ((username != null) && (username.length() > 0)) {
            out.println("<h2>Hello, " + username + "!</h2>");
        }

        String hobby = request.getParameter("hobby");

        if ((hobby != null) && (hobby.length() > 0)) {
            out.println("<h2>Your hobby is " + hobby + "!</h2>");
        }
        
        String birthPlace = request.getParameter("birthPlace");
        if((birthPlace != null) && (birthPlace.length() > 0)){
            out.println("<h2>You was born in " + birthPlace);
        }
                
    }

    public String getServletInfo() {
        return "The Response servlet says hello.";
    }
}
