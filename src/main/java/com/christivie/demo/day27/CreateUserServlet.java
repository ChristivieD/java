package com.christivie.demo.day27;

import com.christivie.data_access.UserDAO;
import com.christivie.demo.day25.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "CreateUserServlet", value = "/signup")
public class CreateUserServlet extends HttpServlet {
    private static Map<String, String> results = new HashMap<>();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/day27/create-user.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("inputEmail1");
        String password1 = request.getParameter("inputPassword1");
        String password2 = request.getParameter("inputPassword2");
        String[] termsOfService = request.getParameterValues("termsOfService");

        results.clear();
        results.put("email", email);
        results.put("password1", password1);
        results.put("password2", password2);
        if(termsOfService != null && termsOfService[0].equals("agree")){
            // The user clicked the agree box
            results.put("termsOfService","agree");
        } else{
            // The user did not click the 'agree' box
            results.put("termsOfService","");
            results.put("termsOfServiceError", "You must agree to our terms od service");
        }
        // validate the form data
        User user = new User();
        try{
            user.setEmail(email);
        }catch (IllegalArgumentException e){
            results.put("emailError", e.getMessage());
        }
        try{
            user.setPassword(password1.toCharArray());
        }catch (IllegalArgumentException e){
            results.put("password1Error", e.getMessage());
        }

        if(!password1.equals(password2)){
            results.put("password2Error","passwords don't match");
        }
        // create a user if they are not error
        if (!results.containsKey("emailError") && !results.containsKey("password1Error")
                && !results.containsKey("password2Error") && !results.containsKey("termsOfServiceError")
        ) {
            try {
                UserDAO.add(user);
                results.put("userAddSuccess", "User added");
            } catch (RuntimeException e) {
                results.put("userAddFail", "User not added");
            }
        }

        request.setAttribute("results",results);
        request.getRequestDispatcher("WEB-INF/day27/create-user.jsp").forward(request,response);

    }
}
