package com.insung.lol.notice.repository;

import java.util.Optional;

import com.insung.lol.common.annotation.TraceLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insung.lol.common.dto.YNEnum;
import com.insung.lol.notice.domain.Notice;

@TraceLog
@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long>{

	Page<Notice> findAllByUseYn(YNEnum Y, Pageable pageable);

	@Override
	Notice save(Notice entity);


}

