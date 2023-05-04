package com.islol.demo.board.dto;

import com.islol.demo.board.enums.Type;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardSaveResDTO {
    private Long id;
    private String type;
    private String content;
    private LocalDateTime regDt;
    private LocalDateTime modDt;

}
