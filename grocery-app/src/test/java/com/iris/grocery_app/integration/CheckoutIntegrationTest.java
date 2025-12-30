/*
package com.iris.grocery_app.integration;

import com.iris.grocery_app.service.InventoryService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class CheckoutIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldCheckoutBasketWithDiscounts() throws Exception {

        String request = """
        [
          { "productCode": "BANANA", "quantity": 3 },
          { "productCode": "ORANGE", "quantity": 4 },
          { "productCode": "APPLE", "quantity": 1 }
        ]
        """;

        mockMvc.perform(post("/checkout")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.items.length()").value(3))
                .andExpect(jsonPath("$.subtotal").value(3.3))
                .andExpect(jsonPath("$.discount").value(0.3))
                .andExpect(jsonPath("$.total").value(3.0));
    }
}
*/
