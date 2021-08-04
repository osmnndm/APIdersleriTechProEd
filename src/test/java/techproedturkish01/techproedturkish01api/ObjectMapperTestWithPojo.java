package techproedturkish01.techproedturkish01api;

import Utilities.JsonUtil;
import org.junit.Test;

import java.util.Map;

public class ObjectMapperTestWithPojo {

    @Test
    public void javaToJson(){
        BookingDates bookingDates = new BookingDates("2020-11-03","2020-11-09");
        System.out.println(bookingDates);

        //bookingDates Java Object'ini json formatına çevirdik.==> Serialization
        String jsonFromPojo = JsonUtil.convertJavaToJson(bookingDates, Map.class);
        System.out.println(jsonFromPojo);
    }
}
