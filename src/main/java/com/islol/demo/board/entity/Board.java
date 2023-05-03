package com.islol.demo.board.entity;

import com.islol.demo.board.enums.Type;
import com.islol.demo.member.entity.Member;
import com.islol.demo.util.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;

@Entity(name = "TB_BOARD")
@Builder
public class Board extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Type type;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

}
