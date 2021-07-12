package com.nuga.memo.domain;

import com.nuga.memo.dto.MemoCdo;
import com.nuga.memo.dto.MemoUdo;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Memo implements Serializable {
    private String title;
    private boolean finished;
    private LocalDateTime createTime;
    private LocalDateTime finishTime;

    public Memo(MemoCdo memoCdo) {
        this.title = memoCdo.getTitle();
        this.finished = false;
        this.createTime = LocalDateTime.now();
    }

    public void update(MemoUdo memoUdo) {
        this.title = memoUdo.getTitle();
        this.finished = memoUdo.isFinished();
        if(memoUdo.isFinished()) {
            finishTime = LocalDateTime.now();
        }
    }
}
