package com.insung.lol.media.service;

import com.insung.lol.media.domain.Media;
import com.insung.lol.media.dto.MediaDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface MediaService {
	
	public Map<String, List<MediaDTO>> getMediaList();

	public Media saveMedia(MultipartFile file, MediaDTO mediaDTO) throws IOException;

	public Media saveMedia(MediaDTO mediaDTO);

}
