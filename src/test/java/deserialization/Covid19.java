package deserialization;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

public class Covid19 {

    @Test
    public void getCountriesTest() throws URISyntaxException, IOException {
        // construct a client
        HttpClient client = HttpClientBuilder.create().build();

        // construct URL: https://corona.lmao.ninja/v2/countries/
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setPath("v2/countries/")
                .setScheme("https")
                .setHost("corona.lmao.ninja");

        // construct http method --> get
        HttpGet httpGet = new HttpGet(uriBuilder.build());

        // headers --> Accept pattern
        httpGet.setHeader("accept", "application/json");

        // execute request
        HttpResponse response = client.execute(httpGet);
        Assert.assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());

        // deserialization
        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String, Object>> parsedResponse = objectMapper.readValue(response.getEntity().getContent(),
                new TypeReference<List<Map<String, Object>>>() {
                });
        System.out.println("Total number of countries: "+parsedResponse.size());

        long maxCases = 0;
        String maxCasesCountry = "";

        for (int i = 0; i < parsedResponse.size(); i++) {
            Map<String, Object> countryMap = parsedResponse.get(i);
            Object countryName = (String) countryMap.get("country");

            long covidCases = (int) countryMap.get("cases");
            if (covidCases > maxCases){
                maxCases = covidCases;
                maxCasesCountry = (String) countryMap.get("country");

            }
            System.out.println("Country name is: "+countryName);

        }
        System.out.println("Max Cases: "+maxCases+" ,country name: "+maxCasesCountry);
    }

    @Test
    public void covidTest2() throws URISyntaxException, IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();

        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https").setHost("corona.lmao.ninja").setPath("v2/countries/");

        HttpGet httpGet = new HttpGet(uriBuilder.build());
        httpGet.setHeader("Accept", "application/json");

        HttpResponse response = httpClient.execute(httpGet);
        Assert.assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());

        // Validate if response body is in JSON format
        Assert.assertTrue(response.getEntity().getContentType().getValue().contains("json"));
        
        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String, Object>> countryList = objectMapper.readValue(response.getEntity().getContent(), new TypeReference<List<Map<String, Object>>>() {
        });

        for (int i = 0; i < countryList.size() ; i++) {
            Map<String, Object> countryMap = countryList.get(i);
            Map<String, Object> countryInfoMap = (Map<String, Object>) countryMap.get("countryInfo");
            String countryCode = countryInfoMap.get("iso3").toString();

            System.out.println("Country code: "+countryCode);



        }


    }
}
