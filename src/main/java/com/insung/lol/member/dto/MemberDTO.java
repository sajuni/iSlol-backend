package com.insung.lol.member.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class MemberDTO {

	private Long memberSeq;
	private String memberName; 
	private String memberAddr;
	private String memberEamil;
	
	public MemberDTO(Long memberSeq, String memberName) {
		this.memberSeq = memberSeq;
		this.memberName = memberName;
	}

}
