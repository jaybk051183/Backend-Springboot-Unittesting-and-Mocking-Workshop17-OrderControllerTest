package com.example.les17.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void shouldReturnProductName() {
        //Arrange
        Order o = new Order("Fiets", 699, 5);


        //Act
        String result = o.getProductname();

        //Assert
        assertEquals("Fiets",result);
    }

    @Test
    void shouldCalculateCorrectAmount(){
        //Arrange
        Order o = new Order("Fiets", 699, 5);

        //Act
        double result = o.calculateAmount();

        //Assert
        assertEquals(3495,result);
    }

    }
