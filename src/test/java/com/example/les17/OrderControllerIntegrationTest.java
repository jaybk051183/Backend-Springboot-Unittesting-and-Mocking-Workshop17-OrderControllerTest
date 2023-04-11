package com.example.les17;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.is;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
class OrderControllerIntegrationTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldCreateCorrectOrder() throws Exception {

        String requestJson = """
                {
                    "productname" : "Gibson gitaar",
                    "unitprice" : 2399.00,
                    "quantity" :  5
                }
                """;

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/orders")
                .contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void shouldReturnCorrectOrder() throws Exception {
        // Eerst een nieuwe test bestelling plaatsen
        String requestJson = """
            {
                "productname" : "Gibson gitaar",
                "unitprice" : 2399.00,
                "quantity" :  5
            }
            """;
//Stuur een POST verzoek naar de endpoint "/orders" en sla het resultaat van dit verzoek op in de variabele result
        MvcResult result = this.mockMvc
                .perform(MockMvcRequestBuilders.post("/orders")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(requestJson))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        // De gegenereerde ID uit de respons halen en opslaan in de variabele orderId.
        String orderId = result.getResponse().getContentAsString();

        // De bestelling ophalen met de gegenereerde ID
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/orders/{id}", orderId)
                        .contentType(APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.orderid", is(Integer.parseInt(orderId))))
                .andExpect(MockMvcResultMatchers.jsonPath("$.productname", is("Gibson gitaar")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.unitprice", is(2399.00)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.quantity", is(5)));
    }
}
