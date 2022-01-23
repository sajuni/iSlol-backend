package com.insung.lol.notice.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insung.lol.common.BaseController;
import com.insung.lol.notice.domain.Notice;
import com.insung.lol.notice.dto.NoticeDTO;
import com.insung.lol.notice.service.NoticeServiceImpl;

import lombok.extern.slf4j.Slf4j;

/**
* @packageName 	: com.insung.lol.notice.controller
* @fileName 	: NoticeController.java
* @author 		: Seung Hyo
* @date 		: 2021.11.21
* @description 	: 공지사항 컨트롤러
* ===========================================================
* DATE 			AUTHOR 		NOTE
* -----------------------------------------------------------
* 2021.11.21 	Seung Hyo 	최초 생성
*/
@RestController
@RequestMapping("/api/notice")
@Slf4j
public class NoticeController extends BaseController {

	@Autowired
	private NoticeServiceImpl noticeService;

	@PostMapping("/list")
	public ResponseEntity<?> getNoticeList(@RequestBody Map<String, Object> reqParam) {
		log.info("start getNoticeList");

		int pageNum = (int)(reqParam.get("pageNum") == null ? 0 : reqParam.get("pageNum"));
		int itemPerPage = (int)(reqParam.get("itemPerPage") == null ? 1 : reqParam.get("itemPerPage"));

		Page<NoticeDTO> noticeList = noticeService.getNoticeList(PageRequest.of(pageNum, itemPerPage));
		log.info("end getNoticeList");
		Map<String, Object> responseData = new HashMap<>();
		responseData.put("noticeList", noticeList);
		return getResponseEntity(responseData);
	}

	@PostMapping("/save")
	public ResponseEntity<?> saveNotice(@RequestBody NoticeDTO noticeDTO) {
		Notice result = noticeService.save(noticeDTO);

		Map<String, Object> responseData = new HashMap<>();
		responseData.put("saveEntity", result);
		return getResponseEntity(responseData);
	}

	@GetMapping("/detail/{id}")
	public ResponseEntity<?> getNoticeDetail(@PathVariable("id") Long id) {
		Notice result = noticeService.getNoticeDetail(id);

		Map<String, Object> responseData = new HashMap<>();
		responseData.put("detail", result);
		return getResponseEntity(responseData);
	}

}
