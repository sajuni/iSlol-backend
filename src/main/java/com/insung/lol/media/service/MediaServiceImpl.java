package com.insung.lol.media.service;

import com.insung.lol.common.dto.YNEnum;
import com.insung.lol.media.domain.Media;
import com.insung.lol.media.dto.MEDIAEnum;
import com.insung.lol.media.dto.MediaDTO;
import com.insung.lol.media.repository.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MediaServiceImpl implements MediaService {

	@Autowired
	private MediaRepository videoRepository;
	
	@Override
	public Map<String, List<MediaDTO>> getMediaList() {
		List<Media> result = videoRepository.findAllByUseYn(YNEnum.Y);

		List<MediaDTO> videoList = result.stream().filter(v -> v.getType().equals(MEDIAEnum.VIDEO)).map(v -> new MediaDTO().build(v)).collect(Collectors.toList());
		List<MediaDTO> imageList = result.stream().filter(v -> v.getType().equals(MEDIAEnum.IMAGE)).map(v -> new MediaDTO().build(v)).collect(Collectors.toList());

		Map<String, List<MediaDTO>> resultMap = new HashMap<>();

		resultMap.put("videoList", videoList);
		resultMap.put("imageList", imageList);


		return resultMap;
	}

}
