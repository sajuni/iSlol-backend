package com.islol.demo.member.entity;

import com.islol.demo.board.entity.Board;
import com.islol.demo.member.enums.Position;
import com.islol.demo.member.enums.Tier;
import com.islol.demo.reply.entity.Reply;
import com.islol.demo.tournament.entity.Tournament;
import com.islol.demo.util.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "TB_MEMBER")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String account;
    @Column(length = 100)
    private String pwd;
    @Column(length = 50)
    private String name;
    @Column(unique = true, length = 100)
    private String nick;
    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private Tier curTier;
    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private Tier preTier;
    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private Tier topTier;
    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private Position mainPosition;
    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private Position subPosition;
    @Column
    private Integer point;
    private LocalDateTime birthDt;
    @Column(columnDefinition = "int default 0")
    private Integer warningCount;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Builder.Default
    private List<MemberRole> roles = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Board> boards = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Reply> replies = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Tournament> tournaments = new ArrayList<>();

    public void setRoles(List<MemberRole> role) {
        this.roles = role;
        role.forEach(o -> o.setMember(this));
    }
}
