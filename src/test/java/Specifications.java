import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Specifications {
    public static RequestSpecification requestSpecification(String url){
        return new RequestSpecBuilder()
                .setBaseUri(url)
                .setContentType(ContentType.JSON)
                .build();
    }
    public static ResponseSpecification responseSpecification(int statusCode){
        return new ResponseSpecBuilder()
                .expectStatusCode(statusCode)
                .build();
    }

  /*  public static ResponseSpecification responseSpecification201ok(){
        return new ResponseSpecBuilder()
                .expectStatusCode(201)
                .build();
    }*/




    public static void  installSpecification(RequestSpecification request, ResponseSpecification response){
        RestAssured.requestSpecification = request;
        RestAssured.responseSpecification = response;

    }
//Examples of building different specifications of request and response from Rahul Shetty
    /*RequestSpecification req =new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
                .setContentType(ContentType.JSON).build();


        ResponseSpecification resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        RequestSpecification res=given().spec(req)
                .body(p);

        Response response =res.when().post("/maps/api/place/add/json").
                then().spec(resspec).extract().response();

        String responseString=response.asString();
        System.out.println(responseString);*/

}
