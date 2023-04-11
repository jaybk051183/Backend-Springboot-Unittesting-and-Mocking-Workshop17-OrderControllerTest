package com.example.les17.service;

import com.example.les17.dto.OrderDto;
import com.example.les17.model.Order;
import com.example.les17.repository.OrderRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepos;

    public OrderService(OrderRepository repos) {
        this.orderRepos = repos;
    }
    public int putOrder(OrderDto newOrderDto) {
        Order o = new Order(newOrderDto.productname, newOrderDto.unitprice, newOrderDto.quantity);

        Order savedOrder = orderRepos.save(o);

        return savedOrder.getOrderid();
    }
    public OrderDto getOrder(int orderid) {
        Optional<Order> oo = orderRepos.findById(orderid);
        if (oo.isPresent()) {
            Order o = oo.get();
            OrderDto odto = new OrderDto();
            odto.orderid = o.getOrderid();
            odto.productname = o.getProductname();
            odto.unitprice = o.getUnitprice();
            odto.quantity = o.getQuantity();

            return odto;
        }
        return null;
    }

    public double getAmount(int orderid) {
        Optional<Order> oo = orderRepos.findById(orderid);
        if (oo.isPresent()) {
            Order o = oo.get();
            return o.calculateAmount();
        }
        return 0;
    }
}
