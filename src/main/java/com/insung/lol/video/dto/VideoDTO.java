package com.insung.lol.video.dto;

import com.insung.lol.common.dto.YNEnum;
import com.insung.lol.member.dto.MemberDTO;

import lombok.Data;

@Data
public class VideoDTO {
	
	private Long videoSeq;
	private MemberDTO member;
	private String videoUrl;
	private String name;
	private String dscrp;
	private YNEnum useYn;
	
	public VideoDTO() {};
	
	public VideoDTO(Long videoSeq, MemberDTO member, String videoUrl, String name, String dscrp, YNEnum useYn) {
		this.videoSeq = videoSeq;
		this.member = member;
		this.videoUrl = videoUrl;
		this.name = name;
		this.dscrp = dscrp;
		this.useYn = useYn;
	}

}
