package com.insung.lol.media.controller;

import com.insung.lol.common.BaseController;
import com.insung.lol.common.annotation.TraceLog;
import com.insung.lol.media.domain.Media;
import com.insung.lol.media.dto.MEDIAEnum;
import com.insung.lol.media.dto.MediaDTO;
import com.insung.lol.media.service.MediaServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@TraceLog
@RestController
@RequestMapping("/api/media")
public class MediaController extends BaseController {

	@Autowired
	private MediaServiceImpl mediaService;
	

	@GetMapping()
	public ResponseEntity<?> getMediaList() {
		Map<String, List<MediaDTO>> result = mediaService.getMediaList();
		return getResponseEntity(result);
	}

//	@GetMapping()
//	public ResponseEntity<?> getRankMediaList() {
//		Map<String, List<MediaDTO>> result = mediaService.getRankMediaList();
//
//		return getResponseEntity(result);
//	}

	@PostMapping("/save")
	public ResponseEntity<?> saveMedia(@RequestPart(name = "file", required = false)MultipartFile file,
									   @RequestPart(value = "mediaDTO") MediaDTO mediaDTO) {
		Map<String, Object> responseData = new HashMap<>();

		// 이넘타입 DTO로 변환하는법을 몰라서 typeValue로 이넘 주입
		if (mediaDTO.getTypeValue().equals("IMAGE")) {
			mediaDTO.setType(MEDIAEnum.IMAGE);
		} else {
			mediaDTO.setType(MEDIAEnum.VIDEO);
		}

		// 비디오 업로드는 url만 가능
		if (mediaDTO.getType() == MEDIAEnum.VIDEO) {
			Media media = mediaService.saveMedia(mediaDTO);
			responseData.put("imageSave", media);
			return getSaveEntity(responseData);
		} else {
			// 이미지 업로드
			try {
				Media media = mediaService.saveMedia(file, mediaDTO);
				responseData.put("videoSave", media);
				return getSaveEntity(responseData);
			} catch (IOException e) {
				responseData.put("saveError", "미디어 업로드에 실패 하였습니다.");
				log.error("error saveMedia {}", e.getMessage());
				return getErrorEntity(responseData);
			}
		}
	}

	@ResponseBody
	@GetMapping("/{filename}")
	public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {
		log.info("파일네임: {}", filename);
		return new UrlResource("file:" + mediaService.getFullPath(filename));
	}


}
