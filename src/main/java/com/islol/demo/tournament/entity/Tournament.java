package com.islol.demo.tournament.entity;

import com.islol.demo.member.entity.Member;
import com.islol.demo.util.BaseEntity;
import jakarta.persistence.*;

@Entity(name = "TB_TOURNAMENT")
public class Tournament extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer win;
    private Integer lose;

    @JoinColumn(name = "member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
}
