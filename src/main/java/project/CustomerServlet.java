package project;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "CustomerServlet", value = "/customer-db")
public class CustomerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("customers", CustomerDAO.getAll());
        request.getRequestDispatcher("WEB-INF/customer-db.jsp").forward(request,response);
    }
}
