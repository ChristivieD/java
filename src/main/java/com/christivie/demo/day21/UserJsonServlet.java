package com.christivie.demo.day21;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.christivie.demo.day21.JsonReader.readJsonFromUrl;

@WebServlet(name = "UserJsonServlet", value = "/user-json")
public class UserJsonServlet extends HttpServlet {
    private static List<User> users;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("q");
        String q = query !=null ? query :"";
//        users.forEach(System.out::println);
        List<User> copy = new ArrayList<>(users); // shallow copy, if your change will affect the original
        copy.removeIf(user -> !user.getName().getFullName().toLowerCase().contains(q.toLowerCase()));
        request.setAttribute("users", copy);
        request.setAttribute("q", q);
        request.getRequestDispatcher("WEB-INF/day21/user-json.jsp").forward(request,response);
    }

    @Override
    public void init() throws ServletException {
        try{
            JSONObject json = readJsonFromUrl("https://randomuser.me/api/?format=json&seed=abc&results=12&nat=us&noinfo");
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            UserFromJson userFromJson = mapper.readValue(json.toString(), UserFromJson.class);
            userFromJson.getUsers().forEach(System.out::println);
            this.users = userFromJson.getUsers();
        } catch(IOException e){

        }
    }
}
