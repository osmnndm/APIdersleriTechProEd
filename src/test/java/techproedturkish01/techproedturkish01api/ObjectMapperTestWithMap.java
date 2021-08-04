package techproedturkish01.techproedturkish01api;

import java.util.HashMap;
import java.util.Map;​
import org.junit.Test;
import Utilities.JsonUtil;
import io.restassured.response.Response;​
import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;
​
public class ObjectMapperTestWithMap extends TestBase {
​
    @Test
    public void javaToJson() {
        HashMap<Integer, String> map = new HashMap<>();
        map.put(101, "Ali");
        map.put(102, "Can");
        map.put(103, "Remziye");
        System.out.println(map);//{101=Ali, 102=Can, 103=Remziye}

        //map Java object'ini json formata cevirdik ==> Serialization
        String jsonFromMap = JsonUtil.convertJavaToJson(map);
        System.out.println(jsonFromMap);//{"101":"Ali","102":"Can","103":"Remziye"}
    }

    @Test
    public void jsonToJava() {

        Response response = given().
                spec(spec01).
                when().
                get("/booking/3");
        response.prettyPrint();

        //API'dan gelen Json formatindaki data'yi Map'e cevirdim ==> De-Serialization
        Map<String,Object> jsonToMapApi = JsonUtil.convertJsonToJava(response.asString(), Map.class);
        System.out.println(jsonToMapApi);

        // 1) API'dan gelen Json Formatındaki data'yı Map'e çevirdik
        // 2) TestCas'de bize verilen data'yı Map'e cevireceğiz.
        // 3) 1.adımda oluşturduğumuz Map ile 2.adımda oluşturduğumuz Map'teki Dataları karşılaştırarak
        // verification yapacağız.

        Map<String, Object> jsonToMapTestCase = new HashMap<>();
        jsonToMapTestCase.put("firstname", "Sally");
        jsonToMapTestCase.put("lastname", "Brown");
        jsonToMapTestCase.put("totalprice", 152);
        jsonToMapTestCase.put("depositpaid", false);

        response.then().assertThat().statusCode(200);

        assertEquals(jsonToMapTestCase.get("firstname"),jsonToMapApi.get("firstname"));
    }
​
}