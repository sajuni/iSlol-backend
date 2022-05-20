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
import com.fasterxml.jackson.annotation.JsonIgnore;
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
	@Column(name = "media_seq")
	private Long mediaSeq;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	@JoinColumn(name = "member_seq", nullable = false)
	private Member member;

	@Column(name = "url", length = 200, nullable = false)
	private String url;

	@Column(name = "name", length = 10, nullable = false)
	private String name;

	@Column(name = "dscrp", length = 50, nullable = true)
	private String dscrp;

	@Enumerated(EnumType.STRING)
	@Column(name = "type", length = 1, nullable = false, columnDefinition = "enum('VIDEO', 'IMAGE')")
	private MEDIAEnum type;

	@Column(name = "SORT", length = 1, nullable = true)
	private String sort;

	@Column(name = "is_deleted", nullable = true, insertable = false, updatable = false)
	@JsonIgnore
	private Boolean isDeleted;

	public Media() {
	}

	public Media(Member member, String url, String name, String dscrp, MEDIAEnum type) {
		this.member = member;
		this.url = url;
		this.name = name;
		this.dscrp = dscrp;
		this.type = type;
	}

	public Media(Long mediaSeq, Member member, String url, String name, String dscrp, MEDIAEnum type, String sort) {
		this.mediaSeq = mediaSeq;
		this.member = member;
		this.url = url;
		this.name = name;
		this.dscrp = dscrp;
		this.type = type;
		this.sort = sort;
	}
}
