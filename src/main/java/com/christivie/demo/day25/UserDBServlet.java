package com.christivie.demo.day25;

import com.christivie.data_access.UserDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "UserDBServlet", value = "/users-db")
public class UserDBServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("users", UserDAO.getAll());
        request.getRequestDispatcher("WEB-INF/day25/users-db.jsp").forward(request, response);

    }
}
