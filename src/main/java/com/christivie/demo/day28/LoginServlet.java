package com.christivie.demo.day28;

import com.christivie.data_access.UserDAO;
import com.christivie.demo.day25.User;
import com.christivie.project.utility.PasswordUtility;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    private static Map<String,String> results = new HashMap<>();
    private static int loginAttempt = 5;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/day28/login.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("inputEmail1");
        String password = request.getParameter("inputPassword1");
        String [] rememberMe = request.getParameterValues("rememberCheck1");

        results.clear();
        results.put("email", email);
        results.put("password",password);
        if(rememberMe!= null && rememberMe[0].equals("true")) {
            results.put("rememberMe", "true");
        }  else {
            results.put("rememberMe", "");
        }

        try {
            User userFromDatabase = UserDAO.get(email);
            if(userFromDatabase == null || userFromDatabase.getStatus().equals("locked")){
                // email not found or account is locked
                results.put("loginFail", "no user found with that email and passwords combination");
            }
            else{
                // email is found
                if(!PasswordUtility.checkpw(password,String.valueOf(userFromDatabase.getPassword()))){
                    // passwords don't match
                    if(loginAttempt == 0){
                        userFromDatabase.setStatus("locked");
                        UserDAO.update(userFromDatabase);
                        results.put("loginFail", "your account is locked. Please contact the admin for login");
                    }
                    else{
                        loginAttempt = loginAttempt -1 ;
                        results.put("loginFail"," login failed, now only" +"\t" + loginAttempt + " login available");
                    }
                }
                else{
                    // password match
                    results.put("loginSuccess", "Welcome Back");
                }
            }
        } catch (Exception e){
            results.put("loginFail", "Cannot log you in. Try again later");
        }
        request.setAttribute("results",results);
        request.getRequestDispatcher("WEB-INF/day28/login.jsp").forward(request,response);

    }
}
