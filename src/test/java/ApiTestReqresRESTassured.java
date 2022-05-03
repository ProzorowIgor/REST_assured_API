import io.restassured.http.ContentType;
import models.DataUser;
import org.testng.Assert;
import org.testng.annotations.Test;
import responses.ResponseGetAllUsers;

import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;


public class ApiTestReqresRESTassured {

    private static final String URL = "https://reqres.in";

   /* @BeforeMethod
    public void preCondition(){
        RestAssured.baseURI = "https://reqres.in/";
        RestAssured.basePath = "api/users?page=2";
    }*/

    @Test
    public void getDataFromUsers() {

        List<DataUser> myUsers = given()
                .when()
                .contentType(ContentType.JSON)
                .get(URL + "/api/users?page=2")
                .then().log().all()
                .extract().body().jsonPath().getList("data", DataUser.class);
        int a = 0;
        myUsers.forEach(x -> Assert.assertTrue(x.getAvatar().contains(x.getId().toString())));
        Assert.assertTrue(myUsers.stream().allMatch(x -> x.getEmail().endsWith("reqres.in")));
        myUsers.forEach(x -> System.out.println("This is id of one single user " + x.getId()));

       /* List<String> avatars = myUsers.stream().map(DataUser::getAvatar).collect(Collectors.toList());
        List<String> ids = myUsers.stream().map(x->x.getId().toString()).collect(Collectors.toList());

        for (int i = 0;i<avatars.size();i++){
            Assert.assertTrue(avatars.get(i).contains(ids.get(i)));
        }*/
    }

    @Test

    public void getAllUsers() {

        ResponseGetAllUsers allUsers = given()
                .when()
                .contentType(ContentType.JSON)
                .get(URL + "/api/users?page=2")
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(ResponseGetAllUsers.class);
        System.out.println(allUsers.getData().toString());
        System.out.println(allUsers.getSupport().toString());
        System.out.println(allUsers.getTotal());
        System.out.println(allUsers.getPage());

        for (DataUser users : allUsers.getData()) {
            System.out.println(users.toString());

        }

    }

}
