package com.insung.lol.common.domain;

import java.time.LocalDateTime;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseTimeEntity {

	@CreatedDate
	@JsonFormat(pattern = "yyyy-MM-dd kk:mm")
	private LocalDateTime createdDate;

	@LastModifiedDate
	@JsonFormat(pattern = "yyyy-MM-dd kk:mm")
	private LocalDateTime modifiedDate;
}
