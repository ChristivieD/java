package com.christivie.demo.day20;

import com.christivie.project.utility.Helpers;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "calculatorServlet", value = "/calculator")
public class CalculatorServlet extends HttpServlet {
    private static Map<String, String> results = new HashMap<>();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/day20/calculator.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request , HttpServletResponse response ) throws ServletException, IOException {
        // made through a form
//        System.out.println("You made a Post request ");
        String num1 = request.getParameter("num1");
        String num2 = request.getParameter("num2");
        add(num1,num2);
        results.put("num1", num1);
        results.put("num2", num2);
        request.setAttribute("results", results);
        request.getRequestDispatcher("WEB-INF/day20/calculator.jsp").forward(request, response);

    }
    public void add(String num1, String num2) {
        if(Helpers.isANumber(num1) && Helpers.isANumber(num2)) {
            double n1 = Double.parseDouble(num1);
            double n2 = Double.parseDouble(num2);
            results.put("sum", n1+ " +" + num2+ "=" + Helpers.round(n1 + n2));
        } else{
            results.put("inavalidNumber", "Please input a valid number");
        }
    }

}
