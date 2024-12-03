package samryong.blogserver.webclient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class NotionClient {
    public Map<String, Object> getNotionDocument(String pageId) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://notion-api.splitbee.io/v1/page/" + pageId;

        String jsonString = restTemplate.getForEntity(url, String.class).getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonString, new TypeReference<Map<String, Object>>() {
        });
    }
}
