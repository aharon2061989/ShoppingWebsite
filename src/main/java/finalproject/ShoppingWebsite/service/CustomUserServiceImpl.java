package finalproject.ShoppingWebsite.service;

import finalproject.ShoppingWebsite.model.CustomUser;
import finalproject.ShoppingWebsite.dto.UserRequest;
import finalproject.ShoppingWebsite.repository.CustomUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomUserServiceImpl implements CustomUserService {

    @Autowired
    private CustomUserRepository customUserRepository;

    @Override
    public void createUser(UserRequest userRequest) throws Exception {
        CustomUser existingCustomUser = customUserRepository.findUserByUsername(userRequest.getUsername());
        if (existingCustomUser != null) {
            throw new Exception("Username " + userRequest.getUsername() + " is already taken");
        }
        customUserRepository.createUser(userRequest.toUser());
    }

    @Override
    public void updateUser(CustomUser customUser) {
        customUserRepository.updateUser(customUser);
    }

    @Override
    public void deleteUserById(Long id) {
        customUserRepository.deleteUserById(id);
    }

    @Override
    public CustomUser findUserByUsername(String username) {
        return customUserRepository.findUserByUsername(username);
    }
}
