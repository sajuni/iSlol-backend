package com.insung.lol.media.repository;

import java.util.List;

import com.insung.lol.common.annotation.TraceLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insung.lol.common.dto.YNEnum;
import com.insung.lol.media.domain.Media;

@TraceLog
@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {

	List<Media> findAllByUseYn(YNEnum y);

}
