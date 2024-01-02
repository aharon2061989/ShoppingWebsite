package finalproject.ShoppingWebsite.service;

import finalproject.ShoppingWebsite.model.CustomUser;
import finalproject.ShoppingWebsite.dto.UserRequest;

public interface CustomUserService {
    void createUser(UserRequest userRequest) throws Exception;
    void updateUser(CustomUser customUser);
    void deleteUserById(Long id);
    CustomUser findUserByUsername(String username);
}
