package jsonAssignment;

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

import static jsonAssignment.JsonReader.readJsonFromUrl;

@WebServlet(name = "CocktailsJsonServlet", value = "/CocktailsServlet")
public class CocktailsServlet extends HttpServlet {
    private  static List<Cocktails> cocktailsList;
    private  static List<String> categories;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("q");
        String sort = request.getParameter("sort");
        String  categIn = request.getParameter("category");
        String q = query !=null ? query :"";
        sort = sort != null ? sort: "";
        String category = categIn != null ? categIn: "";

        List<Cocktails> copy = new ArrayList<>(cocktailsList);
        if(!q.isBlank()){
            copy.removeIf(cocktails -> !cocktails.getDrink().toLowerCase().contains(q.toLowerCase()));
        }
        if(!category.isBlank()){
            copy.removeIf(cocktails -> !cocktails.getCategory().equals(category));
        }
        if(sort.equals("az")){
            Collections.sort(copy);
        }
        else {
            copy.sort(Comparator.reverseOrder());
        }
        request.setAttribute("cocktailsList", copy);
        request.setAttribute("categories", categories);
        request.setAttribute("q",q);
        request.setAttribute("s", sort);
        request.setAttribute("category", category);
        request.getRequestDispatcher("WEB-INF/JsonAssignment/CocktailsServlet.jsp").forward(request,response);

    }
    @Override
    public void init() throws ServletException{
        try{
            JSONObject json = readJsonFromUrl("https://www.thecocktaildb.com/api/json/v1/1/search.php?s=");
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
            CocktailsFromJson cocktailsFromJson = mapper.readValue(json.toString(),CocktailsFromJson.class);
            cocktailsFromJson.getCocktailList().forEach(System.out::println);
            this.cocktailsList = cocktailsFromJson.getCocktailList();

            categories = new ArrayList<>();
            for(Cocktails cocktail : cocktailsList){
                String currentCategory = cocktail.getCategory();
                if(!categories.contains(currentCategory)){
                    categories.add(currentCategory);
                }
            }
            Collections.sort(categories);
        }
        catch(IOException e){
        }
    }

}
