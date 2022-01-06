package com.insung.lol.video.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insung.lol.common.BaseController;
import com.insung.lol.video.dto.VideoDTO;
import com.insung.lol.video.service.VideoServiceImpl;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/video")
@Slf4j
public class VideoController extends BaseController {

	@Autowired
	private VideoServiceImpl videoService;
	

	@GetMapping("/list")
	public ResponseEntity<?> getVideoList() {
		log.info("start getVideoList!!");
		List<VideoDTO> result = videoService.getVideoList();
		
		log.info("end endVideoList!!");
		Map<String, Object> responseData = new HashMap<>();
		responseData.put("videoList", result);
		return getResponseEntity(responseData);
	}

}
