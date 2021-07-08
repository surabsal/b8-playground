package put;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Test;
import pojo.PetPojo;
import utils.PayloadUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

public class PutIntro {

    @Test
    public void updatePetTest() throws URISyntaxException, IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        URIBuilder uriBuilder = new URIBuilder("https://petstore.swagger.io/v2/pet");

        HttpPut put = new HttpPut(uriBuilder.build());
        put.setHeader("Content-Type", "Application/json");
        put.setHeader("Accept", "Application/json");

        HttpEntity entity = new StringEntity(PayloadUtils.getPayload(133, "susu","available"));

        put.setEntity(entity);

        HttpResponse response = httpClient.execute(put);
        Assert.assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());

        ObjectMapper objectMapper = new ObjectMapper();
        PetPojo parsedPetResponse = objectMapper.readValue(response.getEntity().getContent(), PetPojo.class);

        Assert.assertEquals("available", parsedPetResponse.getStatus());

    }
}
