package com.insung.lol.media.controller;

import com.insung.lol.common.BaseController;
import com.insung.lol.media.dto.MediaDTO;
import com.insung.lol.media.service.MediaServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/media")
@Slf4j
public class MediaController extends BaseController {

	@Autowired
	private MediaServiceImpl mediaService;
	

	@GetMapping("/list")
	public ResponseEntity<?> getMediaList() {
		log.info("start getMediaList!!");
		Map<String, List<MediaDTO>> result = mediaService.getMediaList();
		
		log.info("end endMediaList!!");
		Map<String, Object> responseData = new HashMap<>();
		responseData.put("mediaList", result);
		return getResponseEntity(result);
	}


}
