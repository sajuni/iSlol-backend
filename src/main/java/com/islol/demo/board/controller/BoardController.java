package com.islol.demo.board.controller;

import com.islol.demo.board.dto.BoardSaveReqDTO;
import com.islol.demo.board.service.BoardService;
import com.islol.demo.util.BaseController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/board")
@RestController
public class BoardController extends BaseController {

    private final BoardService boardService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody BoardSaveReqDTO boardSaveReqDTO) {
        return getCreateResponseEntity(boardService.save(boardSaveReqDTO));
    }

}
