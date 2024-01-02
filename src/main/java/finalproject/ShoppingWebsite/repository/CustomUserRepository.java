package finalproject.ShoppingWebsite.repository;

import finalproject.ShoppingWebsite.model.CustomUser;

public interface CustomUserRepository {
    void createUser(CustomUser customUser);
    void updateUser(CustomUser customUser);
    void deleteUserById(Long id);
    CustomUser findUserByUsername(String username);

    CustomUser getUserById(Long userId);
}
