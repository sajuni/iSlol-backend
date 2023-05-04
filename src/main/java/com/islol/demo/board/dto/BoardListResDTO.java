package com.islol.demo.board.dto;

import com.islol.demo.board.enums.Type;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardListResDTO {
    private Long id;
    private Long memberId;
    private String content;
    private Type type;
    private LocalDateTime modDt;
    private String modifier;
}
