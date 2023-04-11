package com.example.les17.controller;

import com.example.les17.dto.InvoiceDto;
import com.example.les17.dto.OrderDto;
import com.example.les17.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping("")
    public ResponseEntity<Integer> createOrder(@RequestBody OrderDto newOrderDto) {
        int orderid = service.putOrder(newOrderDto);
        return new ResponseEntity<>(orderid, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> retrieveOrder(@PathVariable int id) {
        OrderDto odto = service.getOrder(id);
        return new ResponseEntity<>(odto, HttpStatus.OK);
    }

    @GetMapping("/{id}/invoice")
    public ResponseEntity<InvoiceDto> getAmount(@PathVariable int id) {
        InvoiceDto invoiceDto = new InvoiceDto();
        invoiceDto.orderid = id;
        invoiceDto.amount = service.getAmount(id);
        return new ResponseEntity<>(invoiceDto, HttpStatus.OK);
    }
}
