package finalproject.ShoppingWebsite.controller;

import finalproject.ShoppingWebsite.model.Item;
import finalproject.ShoppingWebsite.service.ItemService;
import finalproject.ShoppingWebsite.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("item")
public class ItemController {

    @Autowired
    ItemService itemService;

    @Autowired
    JwtUtil jwtUtil;

    @GetMapping("/getItem")
    @CrossOrigin
    public ResponseEntity<?> getItemById(@RequestParam(value = "Authorization")String token, @RequestParam Long itemId){
        String jwt = token.substring(7);
        String username = jwtUtil.extractUsername(jwt);
        Item item = itemService.getItemById(itemId);

        if (item != null) {
            return new ResponseEntity<>(item, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/update")
    @CrossOrigin
    void updateItem(@RequestParam(value = "Authorization")String token, @RequestBody Item item, @RequestParam int quantity){
        String jwt = token.substring(7);
        String username = jwtUtil.extractUsername(jwt);
        item.setStockQuantity(item.getStockQuantity() - quantity);
        itemService.updateItemStockQuantity(item, quantity);
    }

    @GetMapping("/getStockQuantity")
    @CrossOrigin
    public ResponseEntity<Integer> getStockQuantity(@RequestParam(value = "Authorization")String token, @RequestParam Long itemId) {
        String jwt = token.substring(7);
        String username = jwtUtil.extractUsername(jwt);
        Integer stockQuantity = itemService.getStockQuantity(itemId);

        if (stockQuantity != null) {
            return new ResponseEntity<>(stockQuantity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all-items")
    @CrossOrigin
    public ResponseEntity<List<Item>> getListOfAllItems() {
        List<Item> items = itemService.getListOfAllItems();
        return ResponseEntity.ok(items);
    }

    @GetMapping("/search-items")
    @CrossOrigin
    public ResponseEntity<List<Item>> searchItemsByName(@RequestParam String searchTerm) {
        List<Item> items = itemService.searchItemsByName(searchTerm);

        if (!items.isEmpty()) {
            return new ResponseEntity<>(items, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
