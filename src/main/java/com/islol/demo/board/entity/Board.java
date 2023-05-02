package com.islol.demo.board.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.islol.demo.board.entity.enums.Type;
import com.islol.demo.member.entity.Member;
import com.islol.demo.util.BaseTimeEntity;
import jakarta.persistence.*;

@Entity(name = "TB_BOARD")
public class Board extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Type type;
    private String content;
    private String register;
    private String modifier;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

}
