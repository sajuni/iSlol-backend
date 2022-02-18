package com.insung.lol.media.service;

import com.insung.lol.media.domain.Media;
import com.insung.lol.media.dto.MediaDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface MediaService {
	
	Map<String, List<MediaDTO>> getMediaList();

	Media saveMedia(MultipartFile file, MediaDTO mediaDTO) throws IOException;

	Media saveMedia(MediaDTO mediaDTO);

}
