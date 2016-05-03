package hello;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.io.IOException;
@Import(TestIT.Config.class)
public class TestIT {

    @Value("${test.server.port}")
    private String port;

    @Test
    public void testSomething() throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String uri = String.format("%s:%s/%s","http://localhost", port,"home");
        HttpGet httpGet = new HttpGet(uri);
        CloseableHttpResponse response1 = httpclient.execute(httpGet);
        try {
            System.out.println(response1.getStatusLine());
            HttpEntity entity1 = response1.getEntity();
            byte[] bytes = EntityUtils.toByteArray(entity1);
            System.out.println(new String(bytes));
        } finally {
            response1.close();
        }
    }

    @Configuration
    @PropertySource(value={"classpath:config.properties"})
    class Config{

        @Bean
        public PropertySourcesPlaceholderConfigurer propertyConfigurer() {
            return new PropertySourcesPlaceholderConfigurer();
        }

    }
}
