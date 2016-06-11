package hello.test;

import hello.config.Config;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Config.class})
public class TestIT {

    @Value("${test.server.port}")
    private String port;

    @Test
    public void testSomething() throws IOException {
      Assert.assertEquals(1,1);
    }
}

