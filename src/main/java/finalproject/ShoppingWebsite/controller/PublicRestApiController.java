package finalproject.ShoppingWebsite.controller;

import finalproject.ShoppingWebsite.dto.TestResponse;
import finalproject.ShoppingWebsite.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/public")
public class PublicRestApiController {

    @Autowired
    JwtUtil jwtUtil;

    @GetMapping("/test1")
    @CrossOrigin
    public ResponseEntity<?> test1(@RequestParam(value = "Authorization")String token){
        String jwt = token.substring(7);
        String username = jwtUtil.extractUsername(jwt);
        System.out.println(username);
        return ResponseEntity.ok(new TestResponse("API test 1"));
    }

    @CrossOrigin
    @GetMapping("/test2")
    public ResponseEntity<?> test2(){
        return ResponseEntity.ok(new TestResponse("API Test 2 "));
    }
}