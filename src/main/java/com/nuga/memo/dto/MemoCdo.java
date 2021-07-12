package com.nuga.memo.dto;

import lombok.*;

import java.io.Serializable;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@ToString
public class MemoCdo implements Serializable {
    private String title;
}
