package com.islol.demo.reply.entity;

import com.islol.demo.board.entity.Board;
import com.islol.demo.member.entity.Member;
import com.islol.demo.util.BaseEntity;
import jakarta.persistence.*;

@Entity(name = "TB_REPLY")
public class Reply extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long preId;
    @Column
    private String content;

    @JoinColumn(name = "member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @JoinColumn(name = "board_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

}
