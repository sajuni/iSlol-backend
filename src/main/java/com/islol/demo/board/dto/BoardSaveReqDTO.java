package com.islol.demo.board.dto;


import com.islol.demo.board.enums.Type;
import lombok.Data;

@Data
public class BoardSaveReqDTO {
    private Type type;
    private String content;
}
