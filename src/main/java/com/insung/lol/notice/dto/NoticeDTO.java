package com.insung.lol.notice.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.insung.lol.common.dto.YNEnum;
import com.insung.lol.member.dto.MemberDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class NoticeDTO {

	private Long noticeSeq;
	@NotBlank
	private String title;
	@NotBlank
	private String content;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime createdDate;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime modifiedDate;
	private String viewCnt;
	private YNEnum useYn;
	private MemberDTO member;

}
