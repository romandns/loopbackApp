package api.steps;

import api.helpers.Resources;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

public class StaffsSteps {
    private Response response;
    private ValidatableResponse json;
    private RequestSpecification request;
    int staff_massive_size;
    int responseStatusCode;
    String url = "http://localhost:3000/api/staffs" ;
//
//    {
//        try {
//          String url = Resources.getEnvValue();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


    @When("^I add one staff item$")
    public void addOneStaffRecord() {
        request = given();
        given()
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "  \"firstName\": \"Wonder\",\n" +
                        "  \"lastName\": \"Woman\",\n" +
                        "  \"staffPosition\": \"hero\",\n" +
                        "  \"starShip\": \"ellO\"\n" +
                        "}")
                .when()
                .post(url)
                .then()
                .statusCode(200);
    }

    @Given("^I check existing of the Staff list$")
    public void checkingStaffList() {
        given()
                .when()
                .get(url)
                .then().assertThat()
                .body("any { it.containsKey('firstName') }", is(true));
    }

    @And("^I check response and it has StatusCode (\\d+) and contentType: ([^\\\"]*)$")
    public void checkingResponseData(int status_code, String content_type) throws IOException {
        given()
                .when()
                .get(url)
                .then().assertThat()
                .body("any { it.containsKey('firstName') }", is(true))
                .and()
                .statusCode(status_code)
                .contentType(content_type);
    }

    @Then("^I delete all records from the Staff list$")
    public void deleteAllStaffRecords() {
        request = given();
        response = request.when().get(url);
        List<Map<String, List<String>>> allStaffs = response.jsonPath().getList("");
        staff_massive_size = 1;
        for (Map<String, List<String>> staff_list : allStaffs) {
            String deleted_staff_ID = String.valueOf(staff_list.get("id"));
            given()
                    .when()
                    .delete(url + "/" + deleted_staff_ID).then()
                    .statusCode(200)
                    .and()
                    .body("count", equalTo(1));
            staff_massive_size++;
        }
    }

    @And("^I check that staff list and it contains (\\d+) item$")
    public void i_check_staff_list_and_it_contains_0_items(int emptyListValue) {
        response = request.when().get(url);
        List<Map<String, List<String>>> allStaffs = response.jsonPath().getList("");
        assertEquals(emptyListValue, allStaffs.size());
    }

    @And("^I check the Staff list and statusCode is (\\d+)$")
    public void i_Check_staff_list_Response_Of_StatusCode_Is(int verification_status_code) {
        response = request.when().get(url);
        responseStatusCode = response.getStatusCode();
        assertEquals(responseStatusCode, verification_status_code);
    }

    @And("^I ADD one staff record as ([^\\\"]*), ([^\\\"]*), ([^\\\"]*)$")
    public void addNewStaffAs(String first_name, String last_name, String staff_position) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("firstName", first_name);
        requestBody.put("lastName", last_name);
        requestBody.put("staffPosition", staff_position);
        requestBody.put("starShip", "starShip-1111");
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.body(requestBody.toString());
        response = request.post(url);
        responseStatusCode = response.getStatusCode();
        assertEquals(responseStatusCode, 200);
    }

    @When("^I ADD 3 staffs items$")
    public void addFewStaffs(DataTable table) {
        JSONObject requestBody = new JSONObject();
        /**
         * create an ArrayList
         * */
        List<Staff> staffs;
        /**
         * store all items
         * */
        staffs = table.asList(Staff.class);
        /**
         * create FOR cycle for each elements of List<Staff>
         * */
        for (Staff staff : staffs) {
            requestBody.put("firstName", staff.firstName);
            requestBody.put("lastName", staff.lastName);
            requestBody.put("staffPosition", staff.staffPosition);
            requestBody.put("starShip", staff.starShip);
            RequestSpecification request = RestAssured.given();
            request.header("Content-Type", "application/json");
            request.body(requestBody.toString());
            request.post(url);
            int statusCode = response.getStatusCode();
            assertEquals(statusCode, 200);
        }
    }

    @And("^I check that list contains (\\d+) items$")
    public void checkingStaffListSize(int staff_size_list) {
        response = request.when().get(url);
        List<Map<String, List<String>>> allStaffs = response.jsonPath().getList("");
        assertEquals(staff_size_list, allStaffs.size());
    }

    @And("^I check that last request has statusCode: (\\d+)$")
    public void checkingStatusCode(int status_code) {
        json = response.then().statusCode(status_code);
    }

    @And("^I check that (\\d+) record has name and it has next position$")
    public void checkingPosition(int heroPosition, DataTable table) {
        response = request.when().get(url);
        List<Map<String, List<String>>> allStaffs = response.jsonPath().getList("");
        List<Staff> staffs;
        /**
         * store all items
         * */
        staffs = table.asList(Staff.class);
        for (Staff staff : staffs) {
            assertEquals((allStaffs.get(heroPosition - 1).get("firstName")), staff.firstName);
            assertEquals((allStaffs.get(heroPosition - 1).get("lastName")), staff.lastName);
            assertEquals((allStaffs.get(heroPosition - 1).get("staffPosition")), staff.staffPosition);
        }
    }

    @And("^I print the Staff list$")
    public void printingStaffList() {
        request = given();
        response = request.when().get(url);
        List<Map<String, List<String>>> allStaffs = response.jsonPath().getList("");
        staff_massive_size = 0;
        for (Map<String, List<String>> staff_list : allStaffs) {
            given()
                    .when()
                    .get(url)
                    .then()
                    .statusCode(200);
            staff_massive_size++;
        }
    }


    @When("^I delete (\\d+) and (\\d+) item from DB$")
    public void deletingItem(int firstItem, int lastItem) {
        int itemPositionFirst = firstItem - 1;

        response = request.when().get(url);
        List<Map<String, List<String>>> allStaffs = response.jsonPath().getList("");
        String firstDeleteIdItem = String.valueOf(allStaffs.get(itemPositionFirst).get("id"));
        given()
                .when()
                .delete(url + "/" + firstDeleteIdItem).then()
                .statusCode(200)
                .and()
                .body("count", equalTo(1));
        response = request.when().get(url);
        int lastItemPosition = lastItem - 2;
        allStaffs = response.jsonPath().getList("");
        String lastDeleteIdItem = String.valueOf(allStaffs.get(lastItemPosition).get("id"));
        given()
                .when()
                .delete(url + "/" + lastDeleteIdItem).then()
                .statusCode(200)
                .and()
                .body("count", equalTo(1));
    }

    @Then("^I check that staff list contains (\\d+) item$")
    public void checkingStaffItemList(int staffSizeList) {
        response = request.when().get(url);
        List<Map<String, List<String>>> allStaffs = response.jsonPath().getList("");
        assertEquals(staffSizeList, allStaffs.size());
    }

    @And("^I check that last item has name and position$")
    public void checkingItemInfo(DataTable table) {
        response = request.when().get(url);
        List<Map<String, List<String>>> allStaffs = response.jsonPath().getList("");
        int staffListSize = allStaffs.size() - 1;
        List<Staff> staffs;
        /**
         * storing all items
         * */
        staffs = table.asList(Staff.class);
        for (Staff staff : staffs) {
            assertEquals((allStaffs.get(staffListSize).get("firstName")), staff.firstName);
            assertEquals((allStaffs.get(staffListSize).get("lastName")), staff.lastName);
            assertEquals((allStaffs.get(staffListSize).get("staffPosition")), staff.staffPosition);
        }
    }

    public class Staff {
        public String firstName;
        public String lastName;
        public String staffPosition;
        public String starShip;


        Staff(String firstName, String lastName, String staffPosition, String starShip) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.staffPosition = staffPosition;
            this.staffPosition = starShip;
        }
    }

}
