package sgu.sa.container.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sgu.sa.application.usecase.command.createorder.CreateOrderCommand;
import sgu.sa.application.usecase.command.createorder.CreateOrderHandler;
import sgu.sa.application.usecase.command.createorder.CreateOrderResult;
import sgu.sa.application.usecase.command.payorder.PayOrderCommand;
import sgu.sa.application.usecase.command.payorder.PayOrderHandler;
import sgu.sa.application.usecase.query.getorder.GetOrderHandler;
import sgu.sa.application.usecase.query.getorder.GetOrderQuery;
import sgu.sa.application.usecase.query.getorder.GetOrderResult;
import sgu.sa.application.usecase.query.getuserorders.GetUserOrdersHandler;
import sgu.sa.application.usecase.query.getuserorders.GetUserOrdersQuery;
import sgu.sa.application.usecase.query.getuserorders.GetUserOrdersResult;
import sgu.sa.container.response.DataResponse;

import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final CreateOrderHandler createOrderHandler;
    private final GetOrderHandler getOrderHandler;
    private final GetUserOrdersHandler getUserOrdersHandler;
    private final PayOrderHandler payOrderHandler;

    @GetMapping("/{orderId}")
    public ResponseEntity<DataResponse<GetOrderResult>> getOrder(@PathVariable UUID orderId) {
        var query = new GetOrderQuery(orderId);
        var result = getOrderHandler.handle(query);
        var response = DataResponse.success("Lấy thông tin đơn hàng thành công.", result);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/customer/{userId}")
    public ResponseEntity<DataResponse<GetUserOrdersResult>> getUserOrder(@PathVariable UUID userId) {
        var query = new GetUserOrdersQuery(userId);
        var result = getUserOrdersHandler.handle(query);
        var response = DataResponse.success("Lấy thông tin đơn hàng thành công.", result);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<DataResponse<CreateOrderResult>> createOrder(@RequestBody CreateOrderCommand createOrderCommand) {
        var result = createOrderHandler.handle(createOrderCommand);
        var response = DataResponse.success("Tạo đơn hàng thành công", result);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{orderId}/pay")
    public ResponseEntity<DataResponse<Void>> cancelPayment(@PathVariable UUID orderId) {
        var command = new PayOrderCommand(orderId);
        payOrderHandler.handle(command);
        var response = DataResponse.successVoid("Đơn hàng đã thanh toán thành công.");
        return ResponseEntity.ok(response);
    }



}
