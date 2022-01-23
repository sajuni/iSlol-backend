package com.insung.lol.media.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insung.lol.common.dto.YNEnum;
import com.insung.lol.media.domain.Media;

@Repository
public interface MediaRepository extends JpaRepository<Media, Long>{

	List<Media> findAllByUseYn(YNEnum y);

}
