package tests;

import model.Item;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

import static com.jayway.restassured.http.ContentType.JSON;
import static org.testng.Assert.assertEquals;

public class    RestAssuredItemTests {


    @Test
    public void testCreateItem() throws IOException {
        Set<Item> oldItems = getItems();
        Item newItem = new Item().withName("NAME-111");
        String itemId = createItem(newItem);
        Set<Item> newItems = getItems();
        oldItems.add(newItem.withId(itemId));
        assertEquals(newItems, oldItems);
    }

    public Set<Item> getItems() throws IOException {
        String json = RestAssured.get("http://localhost:3000/api/items").asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement selectedItems = parsed.getAsJsonArray().get(0);
        JsonElement items = selectedItems.getAsJsonObject().get("id");
        return new Gson().fromJson(items, new TypeToken<Set<Item>>() {
        }.getType());
    }


    private String createItem(Item newItem) throws IOException {
        String bodyString = String.format("{\n" + "  \"name\": \"%s\"\n" + "}", newItem.getName());
        String json = RestAssured.given()
                .contentType(JSON)
                .body(bodyString)
                .post("http://localhost:3000/api/items")
                .asString();
        JsonElement parsed = new JsonParser().parse(json);
        return String.valueOf(parsed.getAsJsonObject().get("id").getAsString());

    }
}
