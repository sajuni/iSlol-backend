package com.islol.demo.board.service;

import com.islol.demo.board.dto.BoardListResDTO;
import com.islol.demo.board.dto.BoardSaveReqDTO;
import com.islol.demo.board.dto.BoardSaveResDTO;
import com.islol.demo.board.entity.Board;
import com.islol.demo.board.repository.BoardRepository;
import com.islol.demo.member.entity.Member;
import com.islol.demo.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final ModelMapper modelMapper;

    public BoardSaveResDTO save(BoardSaveReqDTO req) {
        CustomUserDetails principal = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Member member = principal.getMember();
        Board board = Board.builder().type(req.getType()).content(req.getContent()).member(member).build();
        Board result = boardRepository.save(board);
        return modelMapper.map(result, BoardSaveResDTO.class);
    }

    public List<BoardListResDTO> getList(Pageable pageable) {

        return null;
    }

}
