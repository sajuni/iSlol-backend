package com.insung.lol.media.service;

import com.insung.lol.auth.security.jwt.JwtUtils;
import com.insung.lol.common.annotation.TraceLog;
import com.insung.lol.common.dto.YNEnum;
import com.insung.lol.media.domain.Media;
import com.insung.lol.media.dto.MEDIAEnum;
import com.insung.lol.media.dto.MediaDTO;
import com.insung.lol.media.repository.MediaRepository;
import com.insung.lol.member.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@TraceLog
@Service
public class MediaServiceImpl implements MediaService {

	@Autowired
	private MediaRepository mediaRepository;

	@Autowired
	private JwtUtils jwtUtils;

	@Value("${file.dir}")
	private String fileDir;

	public String getSavePath(String filename) {
		LocalDateTime dateTime = LocalDateTime.now();
		String date = dateTime.format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
		String savePath = date + "_" + filename;
		return savePath;
	}

	public String getFullPath(String filename) {
		String fullPath = fileDir + filename;
		return fullPath;
	}

	@Override
	public Map<String, List<MediaDTO>> getMediaList() {
		List<Media> result = mediaRepository.findAllByUseYn(YNEnum.Y);

		List<MediaDTO> videoList = result.stream().filter(v -> v.getType().equals(MEDIAEnum.VIDEO)).map(v -> new MediaDTO().build(v)).collect(Collectors.toList());
		List<MediaDTO> imageList = result.stream().filter(v -> v.getType().equals(MEDIAEnum.IMAGE)).map(v -> new MediaDTO().build(v)).collect(Collectors.toList());

		Map<String, List<MediaDTO>> resultMap = new HashMap<>();

		resultMap.put("videoList", videoList);
		resultMap.put("imageList", imageList);


		return resultMap;
	}

	@Override
	public Media saveMedia(MultipartFile file, MediaDTO mediaDTO) throws IOException {
		if (!file.isEmpty()) {
			Member member = jwtUtils.getLoginUserEntity();
			String filename = file.getOriginalFilename();
			String savePath = getSavePath(filename);
			String fullPath = getFullPath(savePath);
			log.info("파일 저장 fullPath = {}", fullPath);
			file.transferTo(new File(fullPath));
			Media media = new Media(member, savePath, mediaDTO.getName(), mediaDTO.getDscrp(), mediaDTO.getType(), YNEnum.Y);
			return mediaRepository.save(media);
		}
		return null;
	}

	@Override
	public Media saveMedia(MediaDTO mediaDTO) {
		Member member = jwtUtils.getLoginUserEntity();
		Media media = new Media(member, mediaDTO.getMediaUrl(), mediaDTO.getName(), mediaDTO.getDscrp(), mediaDTO.getType(), YNEnum.Y);
		return mediaRepository.save(media);
	}
}
