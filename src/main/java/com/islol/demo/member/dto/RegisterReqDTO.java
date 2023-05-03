package com.islol.demo.member.dto;

import com.islol.demo.member.entity.Member;
import com.islol.demo.member.entity.MemberRole;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterReqDTO {

    @NotBlank(message = "이메일을 입력해주세요")
    private String account;

    @NotBlank(message = "비밀번호를 입력해주세요")
    private String pwd;

    private String name;

    private String checkedPwd;

    @NotBlank(message = "닉네임을 입력해주세요")
    private String nick;

    private String tier;

    private List<MemberRole> roles;

    @Builder
    public Member toEntity() {
        return Member.builder()
                .account(account)
                .name(name)
                .nick(nick)
                .pwd(pwd)
                .tier(tier)
                .build();
    }

}
