package sgu.sa.container.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sgu.sa.application.mediator.Mediator;
import sgu.sa.application.usecase.command.cancelpayment.CancelPaymentCommand;
import sgu.sa.application.usecase.command.completepayment.CompletePaymentCommand;
import sgu.sa.application.usecase.command.createpayment.CreatePaymentCommand;
import sgu.sa.application.usecase.command.createpayment.CreatePaymentResult;
import sgu.sa.application.usecase.command.createpaymenturl.CreatePaymentUrlCommand;
import sgu.sa.application.usecase.command.createpaymenturl.CreatePaymentUrlResult;
import sgu.sa.application.usecase.command.verifypayment.VerifyPaymentCommand;
import sgu.sa.application.usecase.query.getorderpayment.GetOrderPaymentQuery;
import sgu.sa.application.usecase.query.getorderpayment.GetOrderPaymentResult;
import sgu.sa.application.usecase.query.getpayment.GetPaymentQuery;
import sgu.sa.application.usecase.query.getpayment.GetPaymentResult;
import sgu.sa.container.response.DataResponse;
import sgu.sa.core.type.PaymentMethod;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final Mediator mediator;

    @PostMapping("/url")
    public ResponseEntity<DataResponse<CreatePaymentUrlResult>> createUrl(@RequestBody CreatePaymentUrlCommand command) {
        var result = mediator.send(command);
        var response = DataResponse.success("Tạo link thanh toán thành công!", result);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/vnpay/callback")
    public ResponseEntity<String> handleVnPayCallback(@RequestParam Map<String, String> callbackParams) {
        VerifyPaymentCommand command = new VerifyPaymentCommand(callbackParams, PaymentMethod.VN_PAY);
        mediator.send(command);
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity<DataResponse<GetPaymentResult>> getPayment(@PathVariable UUID paymentId) {
        var query = new GetPaymentQuery(paymentId);
        var result = mediator.send(query);
        var response = DataResponse.success("Lấy thông tin thanh toán thành công.", result);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<DataResponse<GetOrderPaymentResult>> getOrderPayment(@PathVariable UUID orderId) {
        var query = new GetOrderPaymentQuery(orderId);
        var result = mediator.send(query);
        var response = DataResponse.success("Lấy thông tin thanh toán theo đơn hàng thành công.", result);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<DataResponse<CreatePaymentResult>> createPayment(@RequestBody CreatePaymentCommand command) {
        var result = mediator.send(command);
        var response = DataResponse.success("Tạo thanh toán thành công.", result);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{paymentId}/cancel")
    public ResponseEntity<DataResponse<Void>> cancelPayment(@PathVariable UUID paymentId) {
        var command = new CancelPaymentCommand(paymentId);
        mediator.send(command);
        var response = DataResponse.successVoid("Hủy thanh toán thành công.");
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{paymentId}/complete")
    public ResponseEntity<DataResponse<Void>> completePayment(@PathVariable UUID paymentId) {
        var command = new CompletePaymentCommand(paymentId);
        mediator.send(command);
        var response = DataResponse.successVoid("Xác nhận thanh toán thành công.");
        return ResponseEntity.ok(response);
    }


}