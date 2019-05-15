package tests;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import model.ItemData;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static com.jayway.restassured.http.ContentType.JSON;

public class ItemCreationTests {
//    public String groupJsonPath = "/Users/roman.denysenko/Documents/GitHub/sandboxAddressbook/student/src/test/resources/items.json";
    public String groupJsonPath = "D:\\GitHub\\JavaProjects\\Barancev\\java_pft\\student\\src\\test\\resources\\items.json";

    /**
     * Reading, parsing JSON file using Gson library
     */
    @DataProvider
    public Iterator<Object[]> validItemsFromJson() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(
                new File(groupJsonPath)))) {
            String json = "";
            String line = reader.readLine();
            while (line != null) {
                json += line;
                line = reader.readLine();
            }
            Gson gson = new Gson();
            List<ItemData> items = gson.fromJson(json, new TypeToken<List<ItemData>>() {
            }.getType());
            return items.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }
    }

    @Test(dataProvider = "validItemsFromJson")
    public void testItemCreationFromJson(ItemData item) {
        System.out.println("item: " + item);
        String json = RestAssured.given()
                .contentType(JSON)
                .body(item)
                .post("http://localhost:3000/api/items")
                .asString();
        JsonElement parsed = new JsonParser().parse(json);
        String itemId = String.valueOf(parsed.getAsJsonObject().get("id").getAsString());
        System.out.println(itemId);

    }

}
