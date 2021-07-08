package deserialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pojo.PokemonPojo;

import java.io.IOException;
import java.net.URISyntaxException;

public class PokemonDeserialization {

    HttpClient client;
    URIBuilder uriBuilder;
    HttpGet get;
    ObjectMapper objectMapper;

    @Before
    public void setup() throws URISyntaxException {
        client = HttpClientBuilder.create().build();
        uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https").setPath("api/v2/ability").setHost("pokeapi.co");

        get = new HttpGet(uriBuilder.build());
        objectMapper = new ObjectMapper();

    }

    @Test
    public void getAbilityTest() throws IOException {
        HttpResponse response = client.execute(get);
        Assert.assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());

        PokemonPojo parsedResponse = objectMapper.readValue(response.getEntity().getContent(), PokemonPojo.class);

        int count = parsedResponse.getCount();
        Assert.assertEquals("Failed to get valid ability to count", 327, count);
    }
}
