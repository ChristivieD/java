package com.christivie.demo.day20;

import com.christivie.project.utility.Helpers;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "tempconvert", urlPatterns = {"/convert-temp", "/convert-temps"})
public class Tempconvert extends HttpServlet {
    Map<String, String> results = new HashMap<>();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/day20/convert-temp.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String conversion = request.getParameter("conversion");
        String temperature = request.getParameter("temperature");
        results.clear();
        results.put("conversion", conversion);
        results.put("temperature", temperature);
        convertTemp(conversion,temperature);
        request.setAttribute("results", results);
        request.getRequestDispatcher("WEB-INF/day20/convert-temp.jsp").forward(request,response);
    }

    private void convertTemp(String conversion, String temperature) {
        if(conversion == null || !conversion.equals("FtoC") && !conversion.equals("CtoF")) {
            results.put("conversionError", "Select a conversion type");
        }
        if(!Helpers.isANumber(temperature)) {
            results.put("temperatureError", "Please input a valid temperature");
        }
        if(!results.containsKey("conversionError") && !results.containsKey("temperatureError")) {
            double tempDouble = Double.parseDouble(temperature);
            if(conversion.equals("FtoC")) {
                double tempConverted = (tempDouble - 32) * 5.0 / 9;
                results.put("tempConverted", String.format("%s°F = %s°C", temperature, Helpers.round(tempConverted)));
            }
            if(conversion.equals("CtoF")) {
                double tempConverted = tempDouble * (9.0 / 5) + 32;
                results.put("tempConverted", String.format("%s°C = %s°F", temperature, Helpers.round(tempConverted)));
            }
        }
    }
}
