package com.insung.lol.notice.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.insung.lol.common.dto.YNEnum;
import com.insung.lol.member.domain.Member;

import lombok.Data;

@Data
@Entity
@Table(name="TB_NOTICE")
public class Notice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column(name = "NOTICE_SEQ")
	private Long noticeSeq;
	
	@Column(name = "TITLE", length = 100, nullable = false)
	private String title;
	
	@Column(name = "CONTENT", length = 500, nullable = false)
	private String content;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	@Column(name = "REG_DATE", columnDefinition = "TIMESTAMP", nullable = false)
	private Date regDate;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	@Column(name = "UPDATE_DATE", columnDefinition = "TIMESTAMP", nullable = false)
	private Date updateDate;
	
	@Column(name = "VIEW_CNT", length = 10, nullable = false, columnDefinition = "integer default 0")
	private String viewCnt;
	
	@Column(name = "USE_YN", length = 1, nullable = false, columnDefinition = "enum('N', 'Y') default 'Y'")
	private YNEnum useYn;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	@JoinColumn(name = "MEMBER_SEQ")
	private Member member;
	
}
