package com.nuga.memo.service;

import com.nuga.memo.domain.Memo;
import com.nuga.memo.dto.MemoCdo;
import com.nuga.memo.dto.MemoUdo;
import com.nuga.memo.store.MemoStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class MemoService {
    private final MemoStore memoStore;

    public Mono<String> createMemo(MemoCdo memoCdo) {
        Memo memo = new Memo(memoCdo);
        return memoStore.saveMemo(memo);
    }

    public Flux<Memo> getAllMemos() {
        return memoStore.findAllMemos();
    }

    public Mono<Memo> getOneMemo(String memoId) {
        return memoStore.findOneMemo(memoId);
    }

    public Mono<Void> modifyMemo(String memoId, MemoUdo memoUdo) {
        return memoStore.findOneMemo(memoId)
                .flatMap(memo -> {
                    memo.update(memoUdo);
                    return memoStore.updateMemo(memo);
                });
    }

    public Mono<Void> removeMemo(String memoId) {
        return memoStore.deleteMemo(memoId);
    }
}
