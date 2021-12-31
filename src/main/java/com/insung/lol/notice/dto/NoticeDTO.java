package com.insung.lol.notice.dto;

import java.util.Date;

import com.insung.lol.common.dto.YNEnum;
import com.insung.lol.member.dto.MemberDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NoticeDTO {

	private Long noticeSeq;
	private String title;
	private String content;
	private Date regDate;
	private Date updateDate;
	private String viewCnt;
	private YNEnum useYn;
	private MemberDTO member;
	
}
