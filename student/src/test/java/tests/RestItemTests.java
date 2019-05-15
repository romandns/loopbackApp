package tests;

import model.Item;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

public class RestItemTests {


    @Test
    public void testCreateItem() throws IOException {
//        Set<Item> oldItems = getItems();
        Item newItem = new Item();
        int itemId = createItem(newItem);
//        Set<Item> newItems = getItems();
//        oldItems.add(newItem.withId(itemId));
//        assertEquals(newItems, oldItems);
    }

    public Set<Item> getItems() throws IOException {
        String json = getExecutor().execute(Request.Get("http://localhost:3000/api/items/5cc84d5196521b84c02edb9a"))
                .returnContent().asString();

        JsonElement parsed = new JsonParser().parse(json);
        JsonElement items = parsed.getAsJsonObject().get("name");
        return new Gson().fromJson(items, new TypeToken<Set<Item>>() {
        }.getType());
    }

    private Executor getExecutor() {
        return Executor.newInstance();
    }

    private int createItem(Item newItem) throws IOException {
        String json = getExecutor()
                .execute(
                        Request.Post("http://localhost:3000/api/items")
                                                                .bodyForm(new BasicNameValuePair("name", "SDFSDFSDFSF"))
                                .setHeader("Content-Type", "application/json"))
                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        return parsed.getAsJsonObject().get("issue_id").getAsInt();

    }

    @Test
    public void whenPostJsonUsingHttpClient_thenCorrect()
            throws ClientProtocolException, IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://localhost:3000/api/items");

        String json = String.format("{\n" + "  \"name\": \"%s\"\n" + "}", "ERTEGDG");
        StringEntity entity = new StringEntity(json);
        httpPost.setEntity(entity);
        httpPost.setHeader("Content-type", "application/json");

        CloseableHttpResponse response = client.execute(httpPost);
        assertThat(response.getStatusLine().getStatusCode(), equalTo(200));
        client.close();
    }
}
