package com.jan.jspservlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@WebServlet(name = "Age Calculator Servlet", value = "/calculate-age")
public class AgeCalculatorServlet extends HttpServlet {


    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

        res.setContentType("text/html");
        //include original page's content
        req.getRequestDispatcher("age.jsp").include(req, res);

        //parse dates
        LocalDate dateOfBirth = LocalDate.parse(req.getParameter("dob"));
        LocalDate ageAt = LocalDate.parse(req.getParameter("age-at"));

        PrintWriter out = res.getWriter();
        //validation
        if (dateOfBirth.isAfter(ageAt)) {
            out.println("<p id=\"result-header\" style='font-weight: bolder'>Error</p>");
            out.println("<p id=\"result\">Date of birth cannot be after age at</p>");
            return;
        }

        long days = ChronoUnit.DAYS.between(dateOfBirth, ageAt);
        long hours = days * 24;
        long minutes = hours * 60;
        long seconds = minutes * 60;
        out.println("<hr>");
        out.println("<div id='result-container'>");
        out.println("<p id='result-header'>Age</p>");
        out.println("<p class='result'>" +"Days: "+ days + "</p>");
        out.println("<p class='result'>" +"Hours: "+ hours + "</p>");
        out.println("<p class='result'>" +"Minutes: "+ minutes + "</p>");
        out.println("<p class='result'>" +"Seconds: "+ seconds + "</p>");
        out.println("</div>");
    }

}