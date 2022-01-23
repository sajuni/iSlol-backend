package com.insung.lol.media.dto;

import com.insung.lol.common.dto.YNEnum;
import com.insung.lol.media.domain.Media;
import com.insung.lol.member.dto.MemberDTO;
import lombok.Data;

@Data
public class MediaDTO {
	
	private Long mediaSeq;
	private MemberDTO member;
	private String mediaUrl;
	private String name;
	private String dscrp;
	private MEDIAEnum type;
	private YNEnum useYn;
	
	public MediaDTO() {};
	
	public MediaDTO(MemberDTO member, Long mediaSeq, String mediaUrl, String name, String dscrp, MEDIAEnum type, YNEnum useYn) {
		this.member = member;
		this.mediaSeq = mediaSeq;
		this.mediaUrl = mediaUrl;
		this.name = name;
		this.type = type;
		this.dscrp = dscrp;
		this.useYn = useYn;
	}

	public MediaDTO build(Media v) {
		MemberDTO memberDTO = new MemberDTO(v.getMember().getMemberSeq(), v.getMember().getMemberName());
		MediaDTO mediaDTO = new MediaDTO(memberDTO, v.getMediaSeq(), v.getMediaUrl(), v.getName(),
								v.getDscrp(), v.getType(), v.getUseYn());
		return mediaDTO;
	}


}
