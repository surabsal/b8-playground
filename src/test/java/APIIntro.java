import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Test;
import java.io.IOException;
import java.net.URISyntaxException;

public class APIIntro {

    @Test
    public void apiCallTest() throws URISyntaxException, IOException {
        //1. Have/launch a client(postman/http)
        HttpClient httpClient = HttpClientBuilder.create().build();
        //2.Have valid URL
        //https://corona.lmao.ninja/v2/states
        URIBuilder uriBuilder=new URIBuilder();
        uriBuilder.setScheme("https");
        uriBuilder.setHost("corona.lmao.ninja");
        uriBuilder.setPath("v2/states");
        //3.Specify desired/supported HTTP method
        HttpGet httpGet=new HttpGet(uriBuilder.build());
        //6.Execute/click on Send button
        HttpResponse response= httpClient.execute(httpGet);
        int statusCode= response.getStatusLine().getStatusCode();
        System.out.println("Status code is: " +statusCode);
        Assert.assertEquals(200,statusCode);
    }
}

