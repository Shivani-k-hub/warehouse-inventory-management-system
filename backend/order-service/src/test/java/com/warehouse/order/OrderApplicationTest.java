package com.warehouse.order;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api
    .Assertions.*;

class OrderApplicationTest {

    @Test
    void testApplicationClassExists() {
        OrderServiceApplication app =
            new OrderServiceApplication();
        assertNotNull(app);
    }
}