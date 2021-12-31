package com.insung.lol.notice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.insung.lol.common.dto.YNEnum;
import com.insung.lol.member.dto.MemberDTO;
import com.insung.lol.notice.domain.Notice;
import com.insung.lol.notice.dto.NoticeDTO;
import com.insung.lol.notice.repository.NoticeRepository;

@Service
public class NoticeServiceImpl implements NoticeService{
	
	@Autowired
	private NoticeRepository noticeRepository;
	
	/** 
	* @methodName 	: getNoticeList 
	* @author 		: Seung Hyo 
	* @description 	: 공지사항 리스트 페이징
	* @date 		: 2021.11.21 
	* @param pageable
	* @return 
	*/
	public Page<NoticeDTO> getNoticeList(Pageable pageable) {
		Page<Notice> result = noticeRepository.findAllByUseYn(YNEnum.Y, pageable);
		Page<NoticeDTO> notice = result.map(v -> 
									new NoticeDTO(v.getNoticeSeq(), v.getTitle(), v.getContent(),v.getRegDate(),
													v.getUpdateDate(), v.getViewCnt(), v.getUseYn(), 
													new MemberDTO(v.getMember().getMemberSeq(), v.getMember().getMemberName())));
		
		return notice;
	}
	
	
	public Optional<Notice> getNoticeDetail(Long id) {
		return noticeRepository.findById(id);
	}
	
}
