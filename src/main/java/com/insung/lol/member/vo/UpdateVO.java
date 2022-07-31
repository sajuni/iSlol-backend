package com.insung.lol.member.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateVO {

    private String email;
    private String pw;
    private String changePw;
    private String nick;
    private String mobileNum;
    private String role;

}
