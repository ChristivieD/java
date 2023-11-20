package jsonAssignment;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PictureFromJson {
    @JsonProperty("strDrinkThumb")
    private List<Cocktails> pictureList;

    public List<Cocktails> getPictureList() {
        return pictureList;
    }
}
