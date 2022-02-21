package com.insung.lol.notice.controller;

import java.util.*;

import com.insung.lol.common.annotation.TraceLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
@TraceLog
@RestController
@RequestMapping("/api/notice")
@Slf4j
public class NoticeController extends BaseController {

	@Autowired
	private NoticeServiceImpl noticeService;

	@PostMapping()
	public ResponseEntity<?> getNoticeList(@RequestBody Map<String, Object> reqParam) {
		int pageNum = (int)(reqParam.get("pageNum") == null ? 0 : reqParam.get("pageNum"));
		int itemPerPage = (int)(reqParam.get("itemPerPage") == null ? 1 : reqParam.get("itemPerPage"));

		Page<NoticeDTO> noticeList = noticeService.getNoticeList(PageRequest.of(pageNum, itemPerPage));
		Map<String, Object> responseData = new HashMap<>();
		responseData.put("noticeList", noticeList);
		return getResponseEntity(responseData);
	}

	@PostMapping("/save")
	public ResponseEntity<?> saveNotice(@RequestBody @Validated NoticeDTO noticeDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()){
			return getRequestEntityError(bindingResult);
		}
		Notice result = noticeService.save(noticeDTO);
		Map<String, Object> responseData = new HashMap<>();
		responseData.put("saveEntity", result);
		return getResponseEntity(responseData);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getNoticeDetail(@PathVariable("id") Long id) {
		Notice result = noticeService.getNoticeDetail(id);

		Map<String, Object> responseData = new HashMap<>();
		responseData.put("detail", result);
		return getResponseEntity(responseData);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteNotice(@PathVariable("id") Long id) {
		Map<String, Object> responseData = new HashMap<>();

		try {
			noticeService.delete(id);
		} catch (Exception e) {
			log.info("공지사항 삭제 실패!! {}" , e.getMessage());
			responseData.put("delete", "게시물 " + id + "번 삭제가 실패하였습니다.");
			return getErrorEntity(responseData);
		}

		responseData.put("delete", "게시물 " + id + "번이 삭제 되었습니다.");
		return	getResponseEntity(responseData);
	}

}
