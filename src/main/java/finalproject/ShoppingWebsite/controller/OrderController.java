package finalproject.ShoppingWebsite.controller;

import finalproject.ShoppingWebsite.dto.OrderItemRequest;
import finalproject.ShoppingWebsite.dto.OrderResponse;
import finalproject.ShoppingWebsite.model.*;
import finalproject.ShoppingWebsite.service.OrderService;
import finalproject.ShoppingWebsite.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;
    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("create")
    @CrossOrigin
    public ResponseEntity<?> createOrder(@RequestParam(value = "Authorization")String token, @RequestBody Order order){
        String jwt = token.substring(7);
        String username = jwtUtil.extractUsername(jwt);
        orderService.createOrder(order);
        return ResponseEntity.ok("Order Created Successfully.");
    }

    @PostMapping("/add-to-order")
    @CrossOrigin
    public ResponseEntity<?> addItemToOrder(@RequestParam(value = "Authorization")String token,@RequestBody OrderItemRequest orderItemRequest){
        String jwt = token.substring(7);
        String username = jwtUtil.extractUsername(jwt);
        OrderResponse orderResponse = orderService.addItemToOrder(orderItemRequest);
        return ResponseEntity.ok(orderResponse);
    }

    @PostMapping("/remove-from-order")
    @CrossOrigin
    public ResponseEntity<?> removeItemFromOrder(@RequestParam(value = "Authorization")String token,@RequestBody OrderItemRequest orderItemRequest){
        String jwt = token.substring(7);
        String username = jwtUtil.extractUsername(jwt);
        OrderResponse orderResponse = orderService.removeItemFromOrder(orderItemRequest);
        return ResponseEntity.ok(orderResponse);
    }

    @PutMapping("/update")
    @CrossOrigin
    public void updateOrder(@RequestParam(value = "Authorization")String token, @RequestBody Order order) {
        String jwt = token.substring(7);
        String username = jwtUtil.extractUsername(jwt);
        orderService.updateOrder(order);
    }

    @DeleteMapping("/delete/{orderId}")
    @CrossOrigin
    public void deleteOrder(@RequestParam(value = "Authorization")String token, @PathVariable Long orderId) {
        String jwt = token.substring(7);
        String username = jwtUtil.extractUsername(jwt);
        orderService.deleteOrder(orderId);
    }

    @GetMapping("/get-order/{orderId}")
    @CrossOrigin
    public ResponseEntity<Order> getOrderByOrderId(@RequestParam(value = "Authorization")String token, @PathVariable Long orderId){
        String jwt = token.substring(7);
        String username = jwtUtil.extractUsername(jwt);
        System.out.println("Received orderId: " + orderId);
        Order order = orderService.getOrderById(orderId);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/all-orders/{userId}")
    @CrossOrigin
    public ResponseEntity<List<Order>> getAllOrdersByUserId(@RequestParam(value = "Authorization") String token, @PathVariable Long userId) {
        String jwt = token.substring(7);

        String username = jwtUtil.extractUsername(jwt);
        try {
            List<Order> orders = orderService.getAllOrdersByUserId(userId);
            return ResponseEntity.ok(orders);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/place-order")
    @CrossOrigin
    public ResponseEntity<?> placeOrder(@RequestParam(value = "Authorization") String token, @RequestBody Order order){
        String jwt = token.substring(7);
        String username = jwtUtil.extractUsername(jwt);
        OrderResponse orderResponse = orderService.placeOrder(order);
        return ResponseEntity.ok(orderResponse);
    }
    @GetMapping("/full-order-list/{userId}")
    @CrossOrigin
    public List<OrderResponse> getListOfAllFullOrders(@RequestParam(value = "Authorization") String token, @PathVariable Long userId){
        String jwt = token.substring(7);
        String username = jwtUtil.extractUsername(jwt);
        List<OrderResponse> orderResponses = orderService.getListOfAllFullOrders(userId);
        return orderResponses;
    }


}
