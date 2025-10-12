package sgu.sa.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import usecase.command.createpaymenturl.*;


@RestController
@RequestMapping("/api/payments/vnpay")
@RequiredArgsConstructor
@Getter
public class PaymentController {

    private final CreatePaymentUrlHandler createPaymentUrlHandler;

    @PostMapping("/url")
    public ResponseEntity<CreatePaymentUrlResult> createPaymentUrl(@RequestBody CreatePaymentUrlCommand command) {
        var result = getCreatePaymentUrlHandler().handle(command);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<?> test() {
        return ResponseEntity.ok("ok test!!");
    }
}
