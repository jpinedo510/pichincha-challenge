package pe.msauth.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import pe.msauth.dto.UserDto;
import reactor.core.publisher.Flux;

@Component
public class GoRestClient {

    private static final String BASE_URL = "https://gorest.co.in";

    @Autowired
    private WebClientComponent webClientComponent;

    public Flux<UserDto> getAllUsers() {
        WebClient webClient = webClientComponent.getWebClientByBaseUrl(BASE_URL);

        return webClient.get().uri(uriBuilder -> uriBuilder.path("/public/v2/users").build()).retrieve().bodyToFlux(UserDto.class);
    }
}
