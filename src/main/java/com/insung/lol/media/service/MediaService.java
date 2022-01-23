package com.insung.lol.media.service;

import com.insung.lol.media.dto.MediaDTO;

import java.util.List;
import java.util.Map;

public interface MediaService {
	
	public Map<String, List<MediaDTO>> getMediaList();
	
}
