package sgu.sa.container.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sgu.sa.application.usecase.command.cancelpayment.CancelPaymentCommand;
import sgu.sa.application.usecase.command.cancelpayment.CancelPaymentHandler;
import sgu.sa.application.usecase.command.completepayment.CompletePaymentCommand;
import sgu.sa.application.usecase.command.completepayment.CompletePaymentHandler;
import sgu.sa.application.usecase.command.createpayment.CreatePaymentCommand;
import sgu.sa.application.usecase.command.createpayment.CreatePaymentHandler;
import sgu.sa.application.usecase.command.createpayment.CreatePaymentResult;
import sgu.sa.application.usecase.command.createpaymenturl.CreatePaymentUrlCommand;
import sgu.sa.application.usecase.command.createpaymenturl.CreatePaymentUrlHandler;
import sgu.sa.application.usecase.command.createpaymenturl.CreatePaymentUrlResult;
import sgu.sa.application.usecase.command.verifypayment.VerifyPaymentCommand;
import sgu.sa.application.usecase.command.verifypayment.VerifyPaymentHandler;
import sgu.sa.application.usecase.query.getorderpayment.GetOrderPaymentHandler;
import sgu.sa.application.usecase.query.getorderpayment.GetOrderPaymentQuery;
import sgu.sa.application.usecase.query.getorderpayment.GetOrderPaymentResult;
import sgu.sa.application.usecase.query.getpayment.GetPaymentHandler;
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

    private final CreatePaymentUrlHandler createPaymentUrlHandler;
    private final CreatePaymentHandler createPaymentHandler;
    private final GetOrderPaymentHandler getOrderPaymentHandler;
    private final GetPaymentHandler getPaymentHandler;
    private final CancelPaymentHandler cancelPaymentHandler;
    private final CompletePaymentHandler completePaymentHandler;
    private final VerifyPaymentHandler verifyPaymentHandler;

    @PostMapping("/vnpay/url")
    public ResponseEntity<DataResponse<CreatePaymentUrlResult>> createUrl(@RequestBody CreatePaymentUrlCommand command) {
        var vnpayCommand = new CreatePaymentUrlCommand(
            command.amount(),
            command.orderId(),
            PaymentMethod.VN_PAY
        );
        var result = createPaymentUrlHandler.handle(vnpayCommand);
        var response = DataResponse.success("Tạo link thanh toán thành công!", result);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/vnpay/callback")
    public ResponseEntity<String> handleVnPayCallback(@RequestParam Map<String, String> callbackParams) {
        VerifyPaymentCommand command = new VerifyPaymentCommand(callbackParams, PaymentMethod.VN_PAY);
        verifyPaymentHandler.handle(command);
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity<DataResponse<GetPaymentResult>> getPayment(@PathVariable UUID paymentId) {
        var query = new GetPaymentQuery(paymentId);
        var result = getPaymentHandler.handle(query);
        var response = DataResponse.success("Lấy thông tin thanh toán thành công.", result);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<DataResponse<GetOrderPaymentResult>> getOrderPayment(@PathVariable UUID orderId) {
        var query = new GetOrderPaymentQuery(orderId);
        var result = getOrderPaymentHandler.handle(query);
        var response = DataResponse.success("Lấy thông tin thanh toán theo đơn hàng thành công.", result);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<DataResponse<CreatePaymentResult>> createPayment(@RequestBody CreatePaymentCommand command) {
        var result = createPaymentHandler.handle(command);
        var response = DataResponse.success("Tạo thanh toán thành công.", result);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{paymentId}/cancel")
    public ResponseEntity<DataResponse<Void>> cancelPayment(@PathVariable UUID paymentId) {
        var command = new CancelPaymentCommand(paymentId);
        cancelPaymentHandler.handle(command);
        var response = DataResponse.successVoid("Hủy thanh toán thành công.");
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{paymentId}/complete")
    public ResponseEntity<DataResponse<Void>> completePayment(@PathVariable UUID paymentId) {
        var command = new CompletePaymentCommand(paymentId);
        completePaymentHandler.handle(command);
        var response = DataResponse.successVoid("Xác nhận thanh toán thành công.");
        return ResponseEntity.ok(response);
    }


}