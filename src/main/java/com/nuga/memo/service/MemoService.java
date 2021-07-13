package com.nuga.memo.service;

import com.nuga.memo.domain.Memo;
import com.nuga.memo.dto.MemoCdo;
import com.nuga.memo.dto.MemoUdo;
import com.nuga.memo.store.MemoStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemoService {
    private final MemoStore memoStore;

    public Mono<String> createMemo(MemoCdo memoCdo) {
        log.info("MemoService :: createMemo() called");
        Memo memo = new Memo(memoCdo);
        return memoStore.saveMemo(memo);
    }

    public Flux<Memo> getAllMemos() {
        log.info("MemoService :: getAllMemos() called");
        return memoStore.findAllMemos();
    }

    public Mono<Memo> getOneMemo(String memoId) {
        log.info("MemoService :: getOneMemo() called");
        return memoStore.findOneMemo(memoId);
    }

    public Mono<Void> modifyMemo(String memoId, MemoUdo memoUdo) {
        log.info("MemoService :: modifyMemo() called");
        return memoStore.findOneMemo(memoId)
                .flatMap(memo -> {
                    memo.update(memoUdo);
                    return memoStore.updateMemo(memo);
                });
    }

    public Mono<Void> removeMemo(String memoId) {
        log.info("MemoService :: removeMemo() called");
        return memoStore.deleteMemo(memoId);
    }
}
