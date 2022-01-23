package com.insung.lol.media.domain;

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
import com.insung.lol.media.dto.MEDIAEnum;
import com.insung.lol.member.domain.Member;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "TB_MEDIA")
@ToString(exclude = {"member"})
public class Media extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MEDIA_SEQ")
	private Long mediaSeq;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	@JoinColumn(name = "MEMBER_SEQ", nullable = false)
	private Member member;

	@Column(name = "URL", length = 200, nullable = false)
	private String mediaUrl;

	@Column(name = "NAME", length = 10, nullable = false)
	private String name;

	@Column(name = "DSCRP", length = 50, nullable = true)
	private String dscrp;

	@Enumerated(EnumType.STRING)
	@Column(name = "type", length = 1, nullable = false, columnDefinition = "enum('VIDEO', 'IMAGE')")
	private MEDIAEnum type;

	@Enumerated(EnumType.STRING)
	@Column(name = "USE_YN", length = 1, nullable = true, columnDefinition = "enum('N', 'Y') default 'Y'")
	private YNEnum useYn;


}
