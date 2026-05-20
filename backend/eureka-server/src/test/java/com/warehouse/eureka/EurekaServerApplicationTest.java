package com.warehouse.eureka;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context
    .SpringBootTest;
import org.springframework.test.context
    .TestPropertySource;
import static org.junit.jupiter.api
    .Assertions.*;

@SpringBootTest
@TestPropertySource(properties = {
    "eureka.client.register-with-eureka=false",
    "eureka.client.fetch-registry=false",
    "eureka.server.wait-time-in-ms-when-sync-empty=0"
})
class EurekaServerApplicationTest {

    @Test
    void contextLoads() {
        assertTrue(true);
    }

    @Test
    void testApplicationClassExists() {
        EurekaServerApplication app =
            new EurekaServerApplication();
        assertNotNull(app);
    }

    @Test
    void testMainMethod() {
        assertDoesNotThrow(() ->
            EurekaServerApplication.main(
                new String[]{}));
    }
}