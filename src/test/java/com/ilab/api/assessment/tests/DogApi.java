package com.ilab.api.assessment.tests;

import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static com.ilab.api.assessment.constants.Constants.*;
import static com.ilab.api.assessment.constants.StatusCodeConstants.CODE_200;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class DogApi extends BaseTest{
    Response res;
    @Test(description = "Verify that a successful message is returned when a user searches for random breeds")
    public void verifyRandomBreedMessage(){
        res = given().filter(new RequestLoggingFilter(captor))
                .when().get(DOGBASEURL+GETRANDOMBREEDURL);
        res.then().statusCode(CODE_200)
                .assertThat().body(STATUS,equalTo(SUCCESS)).log().all();
        writeRequestAndResponseInReport(writer.toString(), res.prettyPrint());
    }
    @Test(description = "Verify that bulldog is on the list of all breeds")
    public void verifyBullDogInList(){
       res = given().filter(new RequestLoggingFilter(captor))
        .when().get(DOGBASEURL+DOGLISTURL);
       res.then().statusCode(CODE_200).log().all().assertThat().body(MESSAGE, hasKey(BULLDOG));
        writeRequestAndResponseInReport(writer.toString(), res.prettyPrint());
    }
    @Test(description = "Retrieve all sub-breeds for bulldogs")
    public void retrieveBullDogSubBreed(){
        res = given().filter(new RequestLoggingFilter(captor))
        .when().get(DOGBASEURL+BULLDOGLISTURL);
        res.then().statusCode(CODE_200).log().all();
        writeRequestAndResponseInReport(writer.toString(), res.prettyPrint());
    }
    @Test(description = "Retrieve all sub-breeds images for bulldogs")
    public void retrieveBullDogSubBreedImages() {
        List subBreeds = res.jsonPath().getList("message");
        for (Object subBreed :subBreeds) {
            subBreed=subBreed.toString();
            res = given().filter(new RequestLoggingFilter(captor))
                    .when().get(DOGBASEURL+BULLDOGURL + subBreed + IMAGESURL);
            res.then().statusCode(200).log().all();
            writeRequestAndResponseInReport(writer.toString(), res.prettyPrint());
        }
    }
}
