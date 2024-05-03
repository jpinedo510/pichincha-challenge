package pe.msauth.service.impl;

import io.jsonwebtoken.Jwts;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.msauth.client.GoRestClient;
import pe.msauth.dto.UserDto;
import pe.msauth.dto.UserTokenDto;
import pe.msauth.exception.NotFoundException;
import pe.msauth.service.AuthService;
import pe.msauth.util.TokenUtil;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private GoRestClient goRestClient;

    @Override
    public Mono<UserTokenDto> validateUser(String email) {
        val users = goRestClient.getAllUsers();
        val userFound = users.filter(user -> user.getEmail().equals(email))
                .singleOrEmpty();

        return userFound
                .switchIfEmpty(Mono.error(new NotFoundException("User Not Found")))
                .map(user -> {
                    val claims = Jwts.claims();
                    claims.put("userId", user.getId());
                    val token = TokenUtil.encodeToken(claims);

                    return UserTokenDto.builder().token(token).userId(user.getId()).build();
                });
    }

    @Override
    public Flux<UserDto> getAllUsers() {
        return goRestClient.getAllUsers();
    }
}
