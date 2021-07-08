import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;
import org.junit.Test;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

public class PetStore {

    @Test
    public void getPetTest() throws URISyntaxException, IOException {
        HttpClient client= HttpClientBuilder.create().build();
        URIBuilder uriBuilder=new URIBuilder();
        //https://petstore.swagger.io/v2/pet/1
        uriBuilder.setScheme("https");
        uriBuilder.setHost("petstore.swagger.io");
        uriBuilder.setPath("v2/pet/1");
        HttpGet httpGet=new HttpGet(uriBuilder.build());
        HttpResponse response = client.execute(httpGet);
        int statusCode=response.getStatusLine().getStatusCode();
        Assert.assertEquals(200,statusCode);
        String responseBody = EntityUtils.toString(response.getEntity());
        System.out.println(responseBody);
    }
}
