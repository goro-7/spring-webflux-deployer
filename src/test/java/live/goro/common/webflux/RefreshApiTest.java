package live.goro.common.webflux;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static live.goro.common.webflux.RefreshFns.PATH;

@SpringBootTest
@AutoConfigureWebTestClient
public class RefreshApiTest {
    private static final String REFRESH_URI = PATH;

    @Autowired
    private WebTestClient client;

    @Test
    void shouldRefreshApplication() {

        client.post()
                .uri(REFRESH_URI)
                .exchange()
                .expectStatus()
                .isOk();
    }
}
