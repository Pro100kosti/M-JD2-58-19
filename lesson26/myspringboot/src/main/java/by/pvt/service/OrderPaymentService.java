package by.pvt.service;

import by.pvt.controller.OrderCmd;
import by.pvt.dto.OrderDto;
import by.pvt.pojo.Order;
import by.pvt.repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderPaymentService {

    @Autowired
    OrderRepository orderRepository;

    public List<OrderDto> getAllOrders(int maxCount) {
        List<OrderDto> orderDtos = new ArrayList<>();

        orderRepository.findAll().forEach(
                order -> {
                    OrderDto dto = new OrderDto(order.getId(),
                            order.getProductPrice(),
                            order.getItemCount(),
                            order.getComment(),
                            order.getCreatedDate());

                    if (orderDtos.size() < maxCount) {
                        orderDtos.add(dto);
                    }
                }
        );
        return orderDtos;
    }

    public OrderDto getOrderById(int orderId) {
        Order order = orderRepository.findById((long)orderId).orElseThrow();
        return new OrderDto(order.getId(),
                order.getProductPrice(),
                order.getItemCount(),
                order.getComment(),
                order.getCreatedDate());
    }

    public void createNewOrder(OrderCmd newOrderCmd) {
        Order order = new Order();
        order.setComment(newOrderCmd.getComment());
        order.setCreatedDate(newOrderCmd.getCreatedDate());
        order.setItemCount(newOrderCmd.getItemCount());
        order.setProductId(newOrderCmd.getProductId());
        order.setProductPrice(newOrderCmd.getProductPrice());

        orderRepository.save(order);
    }
}
