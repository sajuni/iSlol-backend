package com.insung.lol.notice.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.insung.lol.common.domain.BaseTimeEntity;
import com.insung.lol.common.dto.YNEnum;
import com.insung.lol.member.domain.Member;

import lombok.Data;

@Data
@Entity
@Table(name = "TB_NOTICE")
public class Notice extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "NOTICE_SEQ")
	private Long noticeSeq;

	@Column(name = "TITLE", length = 100, nullable = false)
	private String title;

	@Column(name = "CONTENT", nullable = false, columnDefinition = "LONGTEXT")
	private String content;

	@Column(name = "VIEW_CNT", length = 10, nullable = true, columnDefinition = "integer default 0")
	private String viewCnt;

	@Enumerated(EnumType.STRING)
	@Column(name = "USE_YN", length = 1, nullable = true, columnDefinition = "enum('N', 'Y') default 'Y'")
	private YNEnum useYn;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	@JoinColumn(name = "MEMBER_SEQ")
	private Member member;

	@PrePersist
	public void prePersist() {
		this.viewCnt = this.viewCnt == null ? "0" : this.viewCnt;
		this.useYn = this.useYn == null ? YNEnum.Y : this.useYn;
	}

}
