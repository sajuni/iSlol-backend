package com.insung.lol.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberUpdateDTO {
    private String memberEmail;
    private String memberoldPwd;
    private String memberPwd;
    private String memberAddr;
    private String memberNick;
}
