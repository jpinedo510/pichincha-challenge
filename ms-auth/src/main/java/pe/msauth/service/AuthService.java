package pe.msauth.service;

import pe.msauth.dto.UserDto;
import pe.msauth.dto.UserTokenDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AuthService {

    public Mono<UserTokenDto> validateUser(String email);

    public Flux<UserDto> getAllUsers();
}
