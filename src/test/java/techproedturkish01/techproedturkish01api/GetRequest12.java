package techproedturkish01.techproedturkish01api;

import static io.restassured.RestAssured.*;
import java.util.ArrayList; 
import java.util.List;
import java.util.Map;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import com.google.gson.Gson;

import io.restassured.response.Response;

public class GetRequest12 extends TestBase {

	/*
	 GSON: GSON, 1) Json formatindaki data'lari Java Objectlerine donusturur. (De-Serialization) 
	             2) Java Object'lerini Json formatindaki data'lara donusturur.(Serialization)
	             
	 Note: GSON ile ayni isi yapan ObjectMapper Class da var
	*/

	@Test
	public void get01() {
		
		Response response = given().
	                          spec(spec03).
	                        when(). 
	                          get();
		
        response.prettyPrint();
        
        //How to convert Json into Java Object by using GSON
        
        List<Map<String, Object>> listOfMapsByGson = response.as(ArrayList.class);
        
        System.out.println("Convertod to Java Object by GSON: " + listOfMapsByGson);
        System.out.println(listOfMapsByGson.get(0));
        
        SoftAssert softAssert = new SoftAssert();
        
        //200 tane id oldugunu 'verify' ediniz.
        softAssert.assertTrue(listOfMapsByGson.size()==200,"Id sayisi istenen gibi degil");
             
        //121. elemanin completed degerinin true oldugunu verify ediniz
        softAssert.assertEquals(listOfMapsByGson.get(120).get("completed"), true, "Istenen gibi degil");
        
        //Sondan bir onceki elemanin title'inin 'numquam repellendus a magnam' oldugunu verify ediniz
        softAssert.assertEquals(listOfMapsByGson.get(listOfMapsByGson.size()-2).get("title"), "numquam repellendus a magnam");
        softAssert.assertAll();

        //Java Object'ini Json formatına cevirme
        Gson gson = new Gson();
        String jsonFromList = gson.toJson(listOfMapsByGson);
        System.out.println(jsonFromList);
	}
}
