package instant.system.demo.utils;

import java.io.IOException;
import java.net.URL;
import org.apache.commons.lang3.StringUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUrlReader {

    public static <T> T getParkingDataFromJson(String jsonParking, Class<T> UsedObject) {
        if (StringUtils.isNotEmpty(jsonParking)) {
            try {
                return new ObjectMapper().reader().readValue(jsonParking, UsedObject);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    public static JsonNode get(String url) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(new URL(url));
        return json;
    }

}