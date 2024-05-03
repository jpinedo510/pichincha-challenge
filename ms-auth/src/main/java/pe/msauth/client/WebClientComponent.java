package pe.msauth.client;

import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

@Component
public class WebClientComponent {

    private static final String HEADER_CONTENT_TYPE = "Content-Type";

    public WebClient getWebClientByBaseUrl(String baseUrl) {
        int memorySize = 16 * 1024 * 1024;
        ExchangeStrategies strategies = ExchangeStrategies.builder()
                .codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(memorySize))
                .build();
        ConnectionProvider connectionProvider = ConnectionProvider
                .builder("largePoolConnection")
                .maxConnections(1000)
                .pendingAcquireMaxCount(3000)
                .build();

        return WebClient.builder()
                .baseUrl(baseUrl)
                .clientConnector(new ReactorClientHttpConnector(HttpClient.create(connectionProvider)))
                .exchangeStrategies(strategies)
                .defaultHeader(HEADER_CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
