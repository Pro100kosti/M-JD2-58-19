package by.pvt.controller;

import by.pvt.dto.OrderDto;
import by.pvt.service.OrderPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
public class OrderController {

    Logger log = Logger.getLogger("OrderController");

    @Autowired
    OrderPaymentService service;

    @GetMapping("/order")
    public List<OrderDto> getOrders(@RequestParam int maxCount) {
        if (maxCount < 1) throw new IllegalArgumentException();
        return service.getAllOrders(maxCount);
    }

    @GetMapping("/order/{orderId}")
    public OrderDto getOrder(@PathVariable("orderId") int orderId) {
        return service.getOrderById(orderId);
    }

    @PostMapping("/order")
    public void createNewOrder(@RequestBody OrderCmd newOrderCmd) {
        service.createNewOrder(newOrderCmd);
        log.info("New order: " + newOrderCmd);
    }

    @PutMapping("/order/{id}")
    public void updateOrder(@PathVariable int id,
                            @RequestBody OrderCmd orderCmd) {
        log.info("Update order ID=" + id + " " + orderCmd);
    }

    @DeleteMapping("/order/{id}")
    public void deleteOrder(@PathVariable int id) {
        log.info("Delete order ID=" + id);
    }

}
