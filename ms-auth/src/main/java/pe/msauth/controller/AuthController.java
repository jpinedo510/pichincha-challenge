package pe.msauth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.msauth.dto.UserDto;
import pe.msauth.dto.UserTokenDto;
import pe.msauth.dto.ValidateUserRequest;
import pe.msauth.service.AuthService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping(path = "/validate-user")
    public Mono<UserTokenDto> validateUser(@RequestBody ValidateUserRequest request) {
        return authService.validateUser(request.getEmail());
    }

    @GetMapping(path = "/users")
    public Flux<UserDto> getAllUsers() {
        return authService.getAllUsers();
    }
}
