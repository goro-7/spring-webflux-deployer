package live.goro.common.webflux;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.InetSocketAddress;

import static java.lang.String.format;
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
            logRequest(request);

            int status = CommandRunner.refreshCode();
            String message = format("The result of hot refresh : %d", status);
            return buildResponse(message);
        };
    }

    static Mono<ServerResponse> buildResponse(String message) {
        return ServerResponse
                .ok()
                .contentType(TEXT_EVENT_STREAM)
                .bodyValue(message);
    }

    static void logRequest(ServerRequest request) {
        InetSocketAddress remoteAddress = CommandRunner.isTest ? null : request.remoteAddress().orElseThrow();
        log.info("Got server refresh request from ip : {}", remoteAddress);
    }

}
