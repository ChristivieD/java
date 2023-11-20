package jsonAssignment;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CocktailsFromJson {
    @JsonProperty("drinks")
    private List<Cocktails> cocktailList;

    public List<Cocktails> getCocktailList() {
        return cocktailList;
    }


}
