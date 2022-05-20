package com.insung.lol.media.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.insung.lol.common.dto.YNEnum;
import com.insung.lol.media.domain.Media;
import com.insung.lol.member.dto.MemberDTO;
import lombok.Data;

@Data
public class MediaDTO {
	
	private Long mediaSeq;
	private MemberDTO member;
	private String url;
	private String name;
	private String dscrp;
	private MEDIAEnum type;
	private Boolean isDelete;
	@JsonIgnore
	private String typeValue;
	private String sort;
	public MediaDTO() {};

	public MediaDTO(MemberDTO member, Long mediaSeq, String url, String name, String dscrp, MEDIAEnum type, Boolean isDelete, String sort) {
		this.member = member;
		this.mediaSeq = mediaSeq;
		this.url = url;
		this.name = name;
		this.type = type;
		this.dscrp = dscrp;
		this.isDelete = isDelete;
		this.sort = sort;
	}

	public MediaDTO build(Media v) {
		MemberDTO memberDTO = new MemberDTO(v.getMember().getMemberSeq(), v.getMember().getMemberName(), v.getMember().getMemberNick());
		MediaDTO mediaDTO = new MediaDTO(memberDTO, v.getMediaSeq(), v.getUrl(), v.getName(),
								v.getDscrp(), v.getType(), v.getIsDeleted(), v.getSort());
		return mediaDTO;
	}


}
