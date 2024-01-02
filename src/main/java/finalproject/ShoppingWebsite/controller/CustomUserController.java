package finalproject.ShoppingWebsite.controller;

import finalproject.ShoppingWebsite.dto.UserRequest;
import finalproject.ShoppingWebsite.model.CustomUser;
import finalproject.ShoppingWebsite.service.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/public/CustomUser")
public class CustomUserController {

    @Autowired
    CustomUserService customUserService;

    @PostMapping("/create")
    @CrossOrigin
    public ResponseEntity<?> createUser(@RequestBody UserRequest customUser){
        try{
            customUserService.createUser(customUser);
            return ResponseEntity.ok("User Registered Successfully");
        } catch (Exception exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }

    @GetMapping("/findUser/{username}")
    @CrossOrigin
    public CustomUser findUserByUsername(@PathVariable String username) {
        return customUserService.findUserByUsername(username);
    }

}

