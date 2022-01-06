package com.insung.lol.video.domain;

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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.insung.lol.common.domain.BaseTimeEntity;
import com.insung.lol.common.dto.YNEnum;
import com.insung.lol.member.domain.Member;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "TB_VIDEO")
@ToString(exclude = {"member"})
public class Video extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "VIDEO_SEQ")
	private Long videoSeq;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	@JoinColumn(name = "MEMBER_SEQ", nullable = false)
	private Member member;

	@Column(name = "URL", length = 50, nullable = false)
	private String videoUrl;

	@Column(name = "NAME", length = 10, nullable = false)
	private String name;

	@Column(name = "DSCRP", length = 50, nullable = true)
	private String dscrp;

	@Enumerated(EnumType.STRING)
	@Column(name = "USE_YN", length = 1, nullable = true, columnDefinition = "enum('N', 'Y') default 'Y'")
	private YNEnum useYn;


}
