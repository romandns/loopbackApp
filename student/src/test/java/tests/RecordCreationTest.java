package java.tests;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import model.RecordData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.jayway.restassured.http.ContentType.JSON;

public class RecordCreationTest {

    @Test
    public void testRecordCreationFromJson() {
        System.out.println("\n\nrequest body: " + createRecord());
        Response response = RestAssured.given()
                .contentType(JSON)
                .body(createRecord())
                .post("http://localhost:3000/api/records");
        int stCode = response.getStatusCode();
//        response.prettyPrint();

        Assert.assertEquals(200, stCode);

        String json = response.asString();
        String recordLabel = createRecord().getRecordLabel();
        JsonElement parsed = new JsonParser().parse(json);
        String recordId = String.valueOf(parsed.getAsJsonObject().get("recordId").getAsString());
        String recordRecordOwner = String.valueOf(parsed.getAsJsonObject().get("recordOwner").getAsString());
        System.out.println("recordId: " + recordId);
        System.out.println("recordRecordOwner: " + recordRecordOwner);

    }

    public RecordData createRecord() {
        return new RecordData()
                .withRecordId(1)
                .withRecordLabel("recLabel_2")
                .withRecordCreationData(getSystemData())
                .withRelationType("main")
                .withRecordRelation(new String[]{"ROMAN"})
                .withRecordOwner("BOSS")
                .withRecordStatus(true);
    }

    private String getSystemData() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String dataCreation = dateFormat.format(date);
        return dataCreation;
    }
}

