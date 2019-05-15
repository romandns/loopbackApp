package testNgTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class dishesTestNG {
    private Response response;
    private ValidatableResponse json;
    private RequestSpecification request;
    int statusCode = 200;
    String successCode;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "http://localhost:3000";
        RestAssured.basePath = "/api";
        request = given();
        response = request.when().get("/dish");
    }

    @Test
    public void printBodyCode() {
        System.out.println("response: " + response.prettyPrint());
        json = response.then().statusCode(statusCode);
    }

    @Test
    public void dishesName() {
        List<Map<String, List<String>>> allDishes = response.jsonPath().getList("");
        System.out.println(allDishes.get(1).get("name"));
        for (Map<String, List<String>> dishName : allDishes) {
            System.out.println("Dish " + dishName.get("name"));
        }
    }

    @Test
    public void dishesId() {
        List<Map<String, List<String>>> allDishes = response.jsonPath().getList("");
        for (Map<String, List<String>> dish_name : allDishes) {
            System.out.println("Dish " + dish_name.get("id"));
        }
    }

    @Test
    public void dishesPrice() {
        List<Map<String, List<String>>> allDishes = response.jsonPath().getList("");
        int dishMassiveSize = allDishes.size();
        String testValue;
        System.out.println("Dishes list contains: " + dishMassiveSize);
        for (Map<String, List<String>> dishDb : allDishes) {
            System.out.println("Dish[" + dishMassiveSize + "]: " + dishDb.get("name") + "\t price: " + dishDb.get("price") +
                    "\t id: " + dishDb.get("id") + "\t currency: " + dishDb.get("currency"));
            dishMassiveSize--;
        }
        testValue = String.valueOf(allDishes.get(1).get("id"));
        System.out.println("testValue: " + testValue);

    }


    @Test
    public void deleteDishesByID() {
        List<Map<String, List<String>>> allDishes = response.jsonPath().getList("");
        int dishMassiveSize = allDishes.size();
        int lastItem = dishMassiveSize - 1;
        System.out.println("Dishes massive contains: " + dishMassiveSize + " items");
        System.out.println("Dish: " + "\t name: " + allDishes.get(lastItem).get("name")
                + "\t price: " + allDishes.get(lastItem).get("price")
                + "\t id: " + allDishes.get(lastItem).get("id")
                + "\t currency: " + allDishes.get(1).get("currency"));
        String deletedDishID;

        deletedDishID = String.valueOf(allDishes.get(lastItem).get("id"));
        given()
                .when()
                .delete("/dish/" + deletedDishID)
                .then()
                .statusCode(200);

    }

    @Test
    public void deleteAllDishesByID() {
        List<Map<String, List<String>>> allDishes = response.jsonPath().getList("");
        int dishMassiveSize = allDishes.size();
        System.out.println("Dishes massive contains: " + dishMassiveSize + " items");
        for (Map<String, List<String>> dishDb : allDishes) {
            String deleted_dish_ID = String.valueOf(dishDb.get("id"));
            System.out.println("deleted_dish_ID: " + deleted_dish_ID);

            System.out.println("Dish[" + dishMassiveSize + "]: " + dishDb.get("name") +
                    "\t id: " + dishDb.get("id"));
            given()
                    .when()
                    .delete("/dish/" + deleted_dish_ID);
            System.out.println("Dish[" + dishMassiveSize + "]: " + dishDb.get("name") +
                    "\t id: " + dishDb.get("id") + " is DELETED");
            dishMassiveSize--;
        }
    }

    @Test
    public void addCucumber() {
        for (int i = 0; i < 3; i++) {
            given().body("{\n" +
                    "  \"name\":\"Cucumber1\",\n" +
                    "  \"price\": 9,\n" +
                    "  \"currency\":\"US\"\n" +
                    "}")
                    .contentType(ContentType.JSON)
                    .when()
                    .post("/dish")
                    .then()
                    .statusCode(200);
        }
    }

    @Test
    public void printDishesByID() {
        given()
                .when()
                .get("/dish/5b1fb4cc4e749dec8817db5c")
                .then()
                .statusCode(200);
    }

    @Test
    public void dishesByID() {
        given()
                .when()
                .contentType(ContentType.JSON)
                .get("/dish/5b1e99d42548d06378fe20a6")
                .then()
                .statusCode(200);
    }

    @Test
    public void addDishes() {
        given().body("{ \n" +
                "\"name\":\"Dish_1\",\n" +
                "  \"price\": 7,\n" +
                "  \"currency\": \"US\"\n" +
                "}")
                .contentType(ContentType.JSON)
                .post("/dish")
                .then()
                .statusCode(200);
    }

    @Test
    public void requestDishes() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("name", "Strawberry_string994");
        requestBody.put("price", 9);
        requestBody.put("currency", "US");

        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.body(requestBody.toString());
        Response response = request.post("/dish");

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
        successCode = response.jsonPath().get("SuccessCode");
        System.out.println("Status Code is : " + statusCode);
        System.out.println("SuccessCode is : " + successCode);
        System.out.println(response.getBody().asString());
    }

    @Test
    public void requestStaffs() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("firstName", "Wonder");
        requestBody.put("lastName", "woman");
        requestBody.put("staffPosition", "hero");
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.body(requestBody.toString());
        Response response = request.post("/StaffsSteps");

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
        successCode = response.jsonPath().get("SuccessCode");
        System.out.println("Status Code is : " + statusCode);
        System.out.println(response.getBody().asString());
    }

    @Test
    public void postRequestExampleItemsItemsDB() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("name", "spider33");
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.body(requestBody.toString());
        Response response = request.post("http://localhost:3000/api/items");

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);

        successCode = response.jsonPath().get("SuccessCode");
        System.out.println("Status Code is : " + statusCode);
        System.out.println(response.getBody().asString());
    }
}
