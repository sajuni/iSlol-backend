package com.insung.lol.member.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class MemberDTO {

	private Long memberSeq;
	private String memberName;
	private String memberPwd;
	private String memberAddr;
	private String memberEamil;
	private String memberNick;
	
	public MemberDTO(Long memberSeq, String memberName, String  memberNick) {
		this.memberSeq = memberSeq;
		this.memberName = memberName;
		this.memberNick = memberNick;
	}

}
