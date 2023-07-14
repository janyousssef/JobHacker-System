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
        PrintWriter out = res.getWriter();

        res.setContentType("text/html");
        //include original page's content
        req.getRequestDispatcher("age.jsp").include(req, res);

        //parse dates
        LocalDate dateOfBirth;
        LocalDate ageAt;
        try {
            dateOfBirth = LocalDate.parse(req.getParameter("dob"));
            ageAt = LocalDate.parse(req.getParameter("age-at"));
        } catch (Exception e) {

            out.println("<hr>");
            out.println("<div id='result-container'>");
            out.println("<p id=\"error-result-header\">Error: Invalid or empty date(s)</p>");
            out.println("</div>");

            return;
        }

        //validation
        if (dateOfBirth.isAfter(ageAt)) {
            out.println("<hr>");
            out.println("<div id='result-container'>");
            out.println("<p id=\"error-result-header\">Error: Date of birth cannot be after age at</p>");
            out.println("</div>");
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