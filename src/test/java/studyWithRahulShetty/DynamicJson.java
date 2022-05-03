package studyWithRahulShetty;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utilsRahulShettyStudy.Payload;
import utilsRahulShettyStudy.ReusableMethods;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class DynamicJson {

    @Test(dataProvider="BooksData")
    public void addBook(String isbn,String aisle) {

        RestAssured.baseURI="http://216.10.245.166";
        String resp = given().
                header("Content-Type","application/json").
                body(Payload.Addbook(isbn,aisle)).
               // body(GenerateStringFromResource("C:\\Users\\rahul\\Documents\\Addbookdetails.json")). <It's optoinal, you can read JSON from file>
                when().
                post("/Library/Addbook.php").
                then().assertThat().statusCode(200).
                extract().response().asString();

        JsonPath js= ReusableMethods.rawToJson(resp);
        String id = js.get("ID");
        System.out.println(id);

        //deleteBOok

    }

    @DataProvider(name="BooksData")
    public Object[][]  getData()
    {

//array=collection of elements

//multidimensional array= collection of arrays
        return new Object[][] {{"ojfwty","1234"},{"cwetee","0971"}, {"okmfet","6785"} };
    }

    public static String GenerateStringFromResource(String path) throws IOException {

        return new String(Files.readAllBytes(Paths.get(path)));
    }


}
