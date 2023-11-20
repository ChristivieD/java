package jsonAssignment;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

public class JsonReader {

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    public static Picture pictureFromJsonString(String jsonString) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return mapper.readValue(jsonString, Picture.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static void main(String[] args) throws IOException, JSONException {
        JSONObject json = readJsonFromUrl("https://www.thecocktaildb.com/api/json/v1/1/search.php?s=");

        String imageUrl = json.getJSONArray("drinks").getJSONObject(0).optString("strDrinkThumb");
        Picture picture = Picture.fromJsonString("{\"strDrinkThumb\": \"" + imageUrl + "\"}");

        if (picture != null) {
            System.out.println(picture);
        } else {
            System.out.println("Failed to deserialize JSON into Picture object.");
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        PictureFromJson pictureFromJson = mapper.readValue(json.toString(), PictureFromJson.class);
        CocktailsFromJson cocktailsFromJson = mapper.readValue(json.toString(), CocktailsFromJson.class);

//        pictureFromJson.getPictureList().forEach(System.out::println);
        cocktailsFromJson.getCocktailList().forEach(System.out::println);
    }
}