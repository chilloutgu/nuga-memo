package com.nuga.memo.document;

import com.nuga.memo.domain.Memo;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;

@Document("memo")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemoDoc implements Serializable {
    @Id private String id;
    private String title;
    private boolean finished;
    private LocalDateTime createTime;
    private LocalDateTime finishTime;

    public MemoDoc(Memo memo) {
        this.title = memo.getTitle();
        this.finished = memo.isFinished();
        this.createTime = memo.getCreateTime();
        this.finishTime = memo.getFinishTime();
    }

    public Memo toDomain() {
        return new Memo(this.title, this.finished, this.createTime, this.finishTime);
    }
}
