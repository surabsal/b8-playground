package deserialization;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

public class Deserialization {

    @Test
    public void test1() throws URISyntaxException, IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        URIBuilder uriBuilder = new URIBuilder();
        // https://petstore.swagger.io/v2/pet/1
        uriBuilder.setScheme("https");
        uriBuilder.setHost("petstore.swagger.io");
        uriBuilder.setPath("v2/pet/1");
        // OR
        uriBuilder.setScheme("https").setHost("petstore.swagger.io").setPath("v2/pet/1");

        HttpGet get = new HttpGet(uriBuilder.build());

        HttpResponse response = httpClient.execute(get);

        Assert.assertEquals(200, response.getStatusLine().getStatusCode());

        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> deserializedResponse = objectMapper.readValue(response.getEntity().getContent(),
                new TypeReference<Map<String, Object>>() {
        });

        System.out.println(deserializedResponse.get("id"));
        System.out.println(deserializedResponse.get("name"));


    }
}
