package finalproject.ShoppingWebsite.controller;

import finalproject.ShoppingWebsite.dto.OrderItemResponse;
import finalproject.ShoppingWebsite.model.OrderItem;
import finalproject.ShoppingWebsite.service.OrderItemService;
import finalproject.ShoppingWebsite.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderItem")
public class OrderItemController {

    @Autowired
    OrderItemService orderItemService;
    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/create")
    public ResponseEntity<?> createOrderItem(@RequestParam(value = "Authorization")String token, @RequestBody OrderItem orderItem){
        String jwt = token.substring(7);
        String username = jwtUtil.extractUsername(jwt);
        OrderItemResponse orderItemResponse = orderItemService.createOrderItem(orderItem);
        return ResponseEntity.ok(orderItemResponse);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateOrderItem(@RequestParam(value = "Authorization")String token, @RequestBody OrderItem orderItem){
        String jwt = token.substring(7);
        String username = jwtUtil.extractUsername(jwt);
        orderItemService.updateOrderItem(orderItem);
        return ResponseEntity.ok("Order Item Updated Successfully" + orderItem);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteOrderItemById(@RequestParam(value = "Authorization")String token, @PathVariable Long orderItemId){
        String jwt = token.substring(7);
        String username = jwtUtil.extractUsername(jwt);
        orderItemService.deleteOrderItemById(orderItemId);
        return ResponseEntity.ok("Order Item Deleted");
    }

    @GetMapping("/get-order/{orderItemId}")
    public OrderItem getOrderItemById(@RequestParam(value = "Authorization")String token, @PathVariable Long orderItemId){
        String jwt = token.substring(7);
        String username = jwtUtil.extractUsername(jwt);
        return orderItemService.getOrderItemById(orderItemId);
    }

    @GetMapping("/all-order-items/{orderId}")
    public ResponseEntity<List<OrderItem>> getAllOrderItemsByUserId(@RequestParam(value = "Authorization")String token, @PathVariable Long orderId){
        String jwt = token.substring(7);
        String username = jwtUtil.extractUsername(jwt);
        List<OrderItem> orderItems = orderItemService.getAllOrderItemsByOrderId(orderId);
        return ResponseEntity.ok(orderItems);
    }
}
