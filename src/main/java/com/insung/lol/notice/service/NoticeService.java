package com.insung.lol.notice.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.insung.lol.notice.domain.Notice;
import com.insung.lol.notice.dto.NoticeDTO;

public interface NoticeService {

	public Page<NoticeDTO> getNoticeList(Pageable pageable);
	
	public Optional<Notice> getNoticeDetail(Long id);
	
}
