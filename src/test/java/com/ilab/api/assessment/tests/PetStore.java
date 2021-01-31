package com.ilab.api.assessment.tests;

import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static com.ilab.api.assessment.constants.Constants.*;
import static com.ilab.api.assessment.constants.StatusCodeConstants.CODE_200;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.equalTo;

public class PetStore extends BaseTest {
    Response res, resp;
    Object ID;

    @Test(description = "Retrieve all available pets and confirm that the name “doggie” with category id : 12 is on the list")
    public void verifyNameAndCategory() {
        res = given().filter(new RequestLoggingFilter(captor)).when().get(PETSTOREBASEURL + GETPETBYSTATUSURL);
        List jsonList = res.body().jsonPath().get("$");
        boolean containsKeys = false;
        for (Object json : jsonList) {
            HashMap<String,Object> map = (HashMap<String,Object>) json;
            if(map != null && map.get("name") != null) {
                if (map.get("name").equals("doggie")) {
                    HashMap<String, Object> category = (HashMap<String, Object>) map.get("category");
                    if (category != null) {
                        int id = (int) category.get("id");
                        if (id == 12) {
                            System.out.println(map.get("name") + "    " + category.get("id"));
                            writeRequestAndResponseInReport(writer.toString(), "There is a pet contains the name “doggie” with category id : 12 is on the list");
                            containsKeys = true;
                        }
                    }
                }
            }
        }
        if(!containsKeys){
            writeRequestAndResponseInReport(writer.toString(),"There is no pets contains the name “doggie” with category id : 12 is on the list");
        }
    }

    @Test(description = "Add a new pet with an auto generated name and status available - Confirm the new pet has been added")
    public void addNewPet() throws IOException {
        res = given().filter(new RequestLoggingFilter(captor)).contentType(JSON).body(generateStringFromResource(JSONFILE)).when().post(PETSTOREBASEURL);
        res.then().assertThat().statusCode(CODE_200).log().all();
        ID = res.jsonPath().get("id");
        writeRequestAndResponseInReport(writer.toString(), res.prettyPrint());
    }

    @Test(description = "retrieve the created pet using the ID")
    public void verifyNewlyAddedPetUsingID() {
        resp = given().filter(new RequestLoggingFilter(captor)).when().get(PETSTOREBASEURL + "/" + ID);
        resp.then().statusCode(CODE_200).assertThat().body("id", equalTo(ID)).log().all();
        writeRequestAndResponseInReport(writer.toString(), res.prettyPrint());
    }
}
