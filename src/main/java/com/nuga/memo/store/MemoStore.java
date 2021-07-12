package com.nuga.memo.store;

import com.nuga.memo.document.MemoDoc;
import com.nuga.memo.domain.Memo;
import com.nuga.memo.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class MemoStore {
    private final MemoRepository memoRepository;

    public Mono<String> saveMemo(Memo memo) {
        MemoDoc memoDoc = new MemoDoc(memo);
        return memoRepository.save(memoDoc)
                .flatMap(savedMemoDoc -> Mono.just(savedMemoDoc.getId()));
    }

    public Flux<Memo> findAllMemos() {
        return memoRepository.findAll().flatMap(memoDoc -> Mono.just(memoDoc.toDomain()));
    }

    public Mono<Memo> findOneMemo(String memoId) {
        return memoRepository.findById(memoId).flatMap(memoDoc -> Mono.just(memoDoc.toDomain()));
    }

    public Mono<Void> updateMemo(Memo memo) {
        MemoDoc memoDoc = new MemoDoc(memo);
        return memoRepository.save(memoDoc).flatMap(savedMemoDoc -> Mono.empty());
    }

    public Mono<Void> deleteMemo(String memoId) {
        return memoRepository.deleteById(memoId);
    }
}
