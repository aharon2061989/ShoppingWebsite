package finalproject.ShoppingWebsite.service;

import finalproject.ShoppingWebsite.security.model.AuthenticationRequest;
import finalproject.ShoppingWebsite.security.model.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse createAuthenticationToken(AuthenticationRequest authenticationRequest) throws Exception;
}
