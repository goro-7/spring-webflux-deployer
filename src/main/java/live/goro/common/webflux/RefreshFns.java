package live.goro.common.webflux;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.TEXT_EVENT_STREAM;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

public interface RefreshFns {
    Logger log = LoggerFactory.getLogger(RefreshFns.class);
    String PATH = "/refresh";

    static RouterFunction<ServerResponse> router() {
        return route()
                .POST(PATH, accept(APPLICATION_JSON), refreshHandler())
                .build()
                ;
    }

    static HandlerFunction<ServerResponse> refreshHandler() {
        return request -> {
            Mono<Integer> status = Mono.just(CommandRunner.refreshCode());
            return ServerResponse
                    .ok()
                    .contentType(TEXT_EVENT_STREAM)
                    .body(status, String.class);
        };
    }

}
