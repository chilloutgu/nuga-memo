package com.nuga.memo.repository;

import com.nuga.memo.document.MemoDoc;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface MemoRepository extends ReactiveCrudRepository<MemoDoc, String> {
}
