package com.insung.lol.video.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insung.lol.common.dto.YNEnum;
import com.insung.lol.member.dto.MemberDTO;
import com.insung.lol.video.domain.Video;
import com.insung.lol.video.dto.VideoDTO;
import com.insung.lol.video.repository.VideoRepository;

@Service
public class VideoServiceImpl implements VideoService {

	@Autowired
	private VideoRepository videoRepository;
	
	@Override
	public List<VideoDTO> getVideoList() {
		List<Video> result = videoRepository.findAllByUseYn(YNEnum.Y);
		List<VideoDTO> list = result.stream().map(v -> new VideoDTO(v.getVideoSeq(), new MemberDTO(v.getMember().getMemberSeq(), v.getMember().getMemberName())
											, v.getVideoUrl(), v.getName(), v.getDscrp(), v.getUseYn())).collect(Collectors.toList()); 
		return list;
	}

}
