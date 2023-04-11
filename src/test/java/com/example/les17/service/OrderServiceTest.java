package com.example.les17.service;

import com.example.les17.dto.OrderDto;
import com.example.les17.model.Order;
import com.example.les17.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    OrderRepository repos;

    @InjectMocks
    OrderService service;

    @Test
    void shouldReturnCorrectOrder() {
        // arrange
        Order o = new Order("Playstation 5", 456.99, 3);
        o.setOrderid(123);

        Mockito.when(repos.findById(anyInt())).thenReturn(Optional.of(o));

        // act
        OrderDto odto = service.getOrder(123);

        // assert
        assertEquals("Playstation 5", odto.productname);
        assertEquals(456.99, odto.unitprice);
        assertEquals(3, odto.quantity);
        assertEquals(123, odto.orderid);
    }
}