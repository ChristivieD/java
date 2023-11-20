package jsonAssignment;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Cocktails implements Comparable<Cocktails> {
    @JsonProperty("idDrink")
    private int Id;
    @JsonProperty("strDrink")
    private String Drink;
    @JsonProperty("strTags")
    private String Tags;
    @JsonProperty("strCategory")
    private String Category;
    @JsonProperty("strAlcoholic")
    private String Alcoholic;
    @JsonProperty("strGlass")
    private String Glass;
    @JsonProperty("strInstructions")
    private String Instructions;
    @JsonProperty("strInstructionsES")
    private String InstuctionsES;
    @JsonProperty("strInstructionsIT")
    private String InstrucionsIT;

    public String getDrinkThumb() {
        return DrinkThumb;
    }

    @JsonProperty("strDrinkThumb")
    private String DrinkThumb;
    @JsonProperty("strIngredient1")
    private String Ingredient1;
    @JsonProperty("strIngredient2")
    private String Ingredient2;
    @JsonProperty("strIngredient3")
    private String Ingredient3;
    @JsonProperty("strIngredient4")
    private String Ingredient;

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

    public String getTags() {
        return Tags;
    }

    public String getInstructions() {
        return Instructions;
    }

    public String getInstuctionsES() {
        return InstuctionsES;
    }

    public String getInstrucionsIT() {
        return InstrucionsIT;
    }

    public String getIngredient1() {
        return Ingredient1;
    }

    public String getIngredient2() {
        return Ingredient2;
    }

    public String getIngredient3() {
        return Ingredient3;
    }

    public String getIngredient() {
        return Ingredient;
    }

    @Override
    public String toString() {
        return "Cocktails{" +
                "Id=" + Id +
                ", Drink='" + Drink + '\'' +
                ", Tags='" + Tags + '\'' +
                ", Category='" + Category + '\'' +
                ", Alcoholic='" + Alcoholic + '\'' +
                ", Glass='" + Glass + '\'' +
                ", Instructions='" + Instructions + '\'' +
                ", InstuctionsES='" + InstuctionsES + '\'' +
                ", InstrucionsIT='" + InstrucionsIT + '\'' +
                ", Ingredient1='" + Ingredient1 + '\'' +
                ", Ingredient2='" + Ingredient2 + '\'' +
                ", Ingredient3='" + Ingredient3 + '\'' +
                ", Ingredient='" + Ingredient + '\'' +
                '}';
    }

    @Override
    public int compareTo(Cocktails o)
    {
        return this.getDrink().compareTo((o.getDrink()));
    }
}

