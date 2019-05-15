package tests;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import model.StaffData;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.jayway.restassured.http.ContentType.JSON;

public class StaffCreationTest {

    @Test
    public void testItemCreationFromJson() {
        System.out.println("staff: " + createStaffRecord());
        Response response = RestAssured.given()
                .contentType(JSON)
                .body(createStaffRecord())
                .post("http://localhost:3000/api/staffs");
        int stCode = response.getStatusCode();
        Assert.assertEquals(200, stCode);

        String json = response.asString();
        System.out.println("Response body: " + response.body().asString());

        String staffFirstName = createStaffRecord().getFirstName();

        JsonElement parsed = new JsonParser().parse(json);
        String staffId= String.valueOf(parsed.getAsJsonObject().get("id").getAsString());
        String staffFirstNameResponse= String.valueOf(parsed.getAsJsonObject().get("firstName").getAsString());
        System.out.println("id: "+ staffId);
        System.out.println("staffFirstNameResponse: "+ staffFirstNameResponse);
    }

    public StaffData createStaffRecord() {
        return new StaffData().withFirstName("Spider133")
                .withLastName("Man")
                .withStaffPosition("HERO")
                .withStarship("loLoLo");
    }
}

