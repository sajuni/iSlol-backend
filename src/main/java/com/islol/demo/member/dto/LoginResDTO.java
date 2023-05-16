package com.islol.demo.member.dto;

import com.islol.demo.member.entity.MemberRole;
import com.islol.demo.member.enums.Tier;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResDTO {
    private Long id;
    private String name;
    private String account;
    private String nick;
    private Tier curTier;
    private List<MemberRole> roles = new ArrayList<>();
    private String token;
}
