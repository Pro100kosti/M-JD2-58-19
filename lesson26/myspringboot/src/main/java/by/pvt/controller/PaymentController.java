package by.pvt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class PaymentController {

    @GetMapping("/payment")
    public String getPayment(@RequestParam UUID id) {
        return "Payment ID=" + id;
    }

}