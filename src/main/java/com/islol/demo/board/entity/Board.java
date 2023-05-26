package com.islol.demo.board.entity;

import com.islol.demo.board.enums.Type;
import com.islol.demo.member.entity.Member;
import com.islol.demo.util.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity(name = "TB_BOARD")
@Getter
@Builder
public class Board extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Type type;
    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

}
