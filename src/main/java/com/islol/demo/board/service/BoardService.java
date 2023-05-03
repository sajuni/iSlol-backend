package com.islol.demo.board.service;

import com.islol.demo.board.dto.BoardSaveReqDTO;
import com.islol.demo.board.entity.Board;
import com.islol.demo.board.repository.BoardRepository;
import com.islol.demo.member.entity.Member;
import com.islol.demo.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public void save(BoardSaveReqDTO req) {
        CustomUserDetails principal = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Member member = principal.getMember();

        Board board = Board.builder().type(req.getType()).content(req.getContent()).member(member).build();
        boardRepository.save(board);
    }

}
