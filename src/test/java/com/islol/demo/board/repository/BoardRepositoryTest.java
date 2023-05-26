package com.islol.demo.board.repository;

import com.islol.demo.board.entity.Board;
import com.islol.demo.board.enums.Type;
import com.islol.demo.member.entity.Member;
import com.islol.demo.member.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("게시판작성")
    void saveBoard() {
        //given
        Optional<Member> byId = memberRepository.findById(1L);
        Board board = Board.builder().type(Type.FREE).content("테스트중이요").member(byId.get()).build();

        //when
        Board result = boardRepository.save(board);

        //then
        assertNotNull(result.getId());
        assertEquals("테스트중이요", result.getContent());

    }
}