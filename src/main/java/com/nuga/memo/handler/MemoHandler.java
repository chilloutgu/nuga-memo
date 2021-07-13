package com.nuga.memo.handler;

import com.nuga.memo.domain.Memo;
import com.nuga.memo.dto.MemoCdo;
import com.nuga.memo.dto.MemoUdo;
import com.nuga.memo.service.MemoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Slf4j
@Component
@RequiredArgsConstructor
public class MemoHandler {
    private final MemoService memoService;

    public Mono<ServerResponse> createMemo(ServerRequest serverRequest) {
        log.info("memoHandler :: createMemo() called");
        final Mono<MemoCdo> memoCdoMono = serverRequest.bodyToMono(MemoCdo.class);
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(memoCdoMono.flatMap(memoService::createMemo), Memo.class);
    }

    public Mono<ServerResponse> getAllMemos(ServerRequest serverRequest) {
        log.info("memoHandler :: getAllMemos() called");
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(memoService.getAllMemos(), Memo.class);
    }

    public Mono<ServerResponse> getOneMemo(ServerRequest serverRequest) {
        log.info("memoHandler :: getOneMemo() called");
        final String memoId = serverRequest.pathVariable("memoId");
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(memoService.getOneMemo(memoId), Memo.class);
    }

    public Mono<ServerResponse> modifyMemo(ServerRequest serverRequest) {
        log.info("memoHandler :: modifyMemo() called");
        final String memoId = serverRequest.pathVariable("memoId");
        Mono<MemoUdo> memoUdoMono = serverRequest.bodyToMono(MemoUdo.class);
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(memoUdoMono.flatMap(memoUdo ->
                        memoService.modifyMemo(memoId, memoUdo)), Void.class);
    }

    public Mono<ServerResponse> removeMemo(ServerRequest serverRequest) {
        log.info("memoHandler :: removeMemo() called");
        final String memoId = serverRequest.pathVariable("memoId");
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(memoService.removeMemo(memoId), Void.class);
    }

}
