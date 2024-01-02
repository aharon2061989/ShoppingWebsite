package finalproject.ShoppingWebsite.controller;

import finalproject.ShoppingWebsite.dto.FavoriteItemResponse;
import finalproject.ShoppingWebsite.model.FavoriteItem;
import finalproject.ShoppingWebsite.service.FavoriteItemService;
import finalproject.ShoppingWebsite.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favorite-item")
public class FavoriteItemController {

    @Autowired
    FavoriteItemService favoriteItemService;
    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/create")
    @CrossOrigin
    public ResponseEntity<?> createFavoriteItem(@RequestParam(value = "Authorization")String token, @RequestBody FavoriteItem favoriteItem) {
        String jwt = token.substring(7);
        String username = jwtUtil.extractUsername(jwt);
        favoriteItemService.createFavoriteItem(favoriteItem);
        return ResponseEntity.ok(favoriteItem);
    }

    @DeleteMapping("delete/{favoriteItemId}")
    @CrossOrigin
    public ResponseEntity<?> deleteFavoriteItem(@RequestParam(value = "Authorization")String token, @PathVariable Long favoriteItemId) {
        String jwt = token.substring(7);
        String username = jwtUtil.extractUsername(jwt);
        favoriteItemService.deleteFavoriteItem(favoriteItemId);
        return ResponseEntity.ok("Favorite Item Deleted Successfully");
    }

    @GetMapping("/all-favorite-items/{userId}")
    @CrossOrigin
    public ResponseEntity<List<FavoriteItemResponse>> getAllFavoriteItemsByUserId(@RequestParam(value = "Authorization")String token, @PathVariable Long userId) {
        String jwt = token.substring(7);
        String username = jwtUtil.extractUsername(jwt);
        List<FavoriteItemResponse> favoriteItemResponses = favoriteItemService.getAllFavoriteItemsByUserId(userId);
        return ResponseEntity.ok(favoriteItemResponses);
    }
}
