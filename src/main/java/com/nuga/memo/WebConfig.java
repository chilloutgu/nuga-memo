package com.nuga.memo;

import com.nuga.memo.handler.MemoHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Slf4j
@EnableWebFlux
@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebFluxConfigurer {
    private final MemoHandler memoHandler;

    @Bean
    public RouterFunction<ServerResponse> memoRouter() {
        log.info("memoRouter called");
        return route()
                .GET("/memos/{memoId}", memoHandler::getOneMemo)
                .GET("/memos", memoHandler::getAllMemos)
                .POST("/memos", memoHandler::createMemo)
                .PUT("/memos/{memoId}", memoHandler::modifyMemo)
                .DELETE("/memos/{memoId}", memoHandler::removeMemo)
                .build();
    }
}
