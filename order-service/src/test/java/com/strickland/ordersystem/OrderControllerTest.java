package com.strickland.ordersystem;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.strickland.ordersystem.entities.Order;
import com.strickland.ordersystem.entities.OrderItem;
import com.strickland.ordersystem.enums.OrderStatus;
import com.strickland.ordersystem.services.OrderService;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@ActiveProfiles("test")
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Autowired
    private ObjectMapper objectMapper;

    private Order order;

    @BeforeEach
    public void setUp() {
        OrderItem item1 = new OrderItem(1L, 2, new BigDecimal("19.99"));
        OrderItem item2 = new OrderItem(2L, 1, new BigDecimal("49.99"));

        order = new Order(12345L, Arrays.asList(item1, item2), new BigDecimal("89.97"), OrderStatus.NEW, LocalDateTime.now(), LocalDateTime.now());
    }

    @Test
    public void testPlaceOrder() throws Exception {
        String orderJson = objectMapper.writeValueAsString(order);

        Mockito.doNothing()
               .when(orderService)
               .processOrder(Mockito.any(Order.class));

        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(orderJson))
               .andDo(MockMvcResultHandlers.print())
               .andExpect(status().isOk());
    }
}