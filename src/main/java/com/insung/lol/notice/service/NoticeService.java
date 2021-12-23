package com.insung.lol.notice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.insung.lol.notice.domain.Notice;
import com.insung.lol.notice.repository.NoticeRepository;

@Service
public class NoticeService {
	
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
	public Page<Notice> getNoticeList(Pageable pageable) {
		return noticeRepository.findAll(pageable);
	}
	
}
