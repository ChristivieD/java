package com.christivie.demo.day21;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.christivie.demo.day21.JsonReader.readJsonFromUrl;

@WebServlet(name = "UserJsonServlet", value = "/user-json")
public class UserJsonServlet extends HttpServlet {
    private static List<User> users;
    private static List<String> states;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get query string parameters
        String query = request.getParameter("q");
        String sort = request.getParameter("sort");
        String stateIn = request.getParameter("state");
        String q = query !=null ? query :"";
        sort = sort != null ? sort: "";
        String state = stateIn != null ? stateIn:"";
//        users.forEach(System.out::println);

        List<User> copy = new ArrayList<>(users); // shallow copy, if your change will affect the original
        // predicate object
        if(!q.isBlank()){
            copy.removeIf(user -> !user.getName().getFullName().toLowerCase().contains(q.toLowerCase()));
        }
        if (!state.isBlank()){
            copy.removeIf(user -> !user.getLocation().getState().equals(state));
        }
         if(sort.equals("az")){
             Collections.sort(copy);
         }else{
             copy.sort(Comparator.reverseOrder());
//             copy.sort((a,b) -> b.compareTo(a));
//             Collections.reverseOrder();
         }

        // set all static variables and parameters as attributes
        request.setAttribute("users", copy);
        request.setAttribute("states", states);
        request.setAttribute("q", q);
        request.setAttribute("s",sort);
        request.setAttribute("state", state);
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


            states = new ArrayList<>();
            for(User user:users){
                String state = user.getLocation().getState();
                if(!states.contains(state)){
                    states.add(state);
                }
            }
            Collections.sort(states);
        } catch(IOException e){

        }
    }
}
