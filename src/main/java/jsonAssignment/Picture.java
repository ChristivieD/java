package jsonAssignment;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

public class Picture {
    @JsonProperty("strDrinkThumb")
    private String thumb;

    public static Picture fromJsonString(String jsonString) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return mapper.readValue(jsonString, Picture.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getThumb() {
        return thumb;
    }

    @Override
    public String toString() {
        return "Picture{" +
                "thumb='" + thumb + '\'' +
                '}';
    }


}
