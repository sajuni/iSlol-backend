package com.insung.lol.video.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insung.lol.common.dto.YNEnum;
import com.insung.lol.video.domain.Video;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long>{

	List<Video> findAllByUseYn(YNEnum y);

}
