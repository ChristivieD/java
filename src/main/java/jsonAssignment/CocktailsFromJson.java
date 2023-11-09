package jsonAssignment;

import com.christivie.demo.day21.Picture;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CocktailsFromJson {
    @JsonProperty("drinks")
    private List<Cocktail> cocktailList;

    public List<Cocktail> getCocktailList() {
        return cocktailList;
    }
    class Cocktail{
        @JsonProperty("idDrink")
        private int Id;
        @JsonProperty("StrDrink")
        private String Drink;
        @JsonProperty("strCategory")
        private String Category;
        @JsonProperty("strAlcoholic")
        private String Alcoholic;
        @JsonProperty("strGlass")
        private String Glass;
        @JsonProperty("strDrinkThumb")
        private Picture drinkThumb;

        public int getId() {
            return Id;
        }

        public String getDrink() {
            return Drink;
        }

        public String getCategory() {
            return Category;
        }

        public String getAlcoholic() {
            return Alcoholic;
        }

        public String getGlass() {
            return Glass;
        }

        public Picture getDrinkThumb() {
            return drinkThumb;
        }

        @Override
        public String toString() {
            return "Cocktail{" +
                    "\n\tId=" + Id +
                    "\n\tDrink='" + Drink + '\'' +
                    "\n\tCategory='" + Category + '\'' +
                    "\n\tAlcoholic='" + Alcoholic + '\'' +
                    "\n\tGlass='" + Glass + '\'' +
                    "\n\tdrinkThumb=" + drinkThumb +
                    "}\n";
        }
    }
}
