package com.insung.lol.user.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import lombok.Data;

@Data
@Entity
@Table(name = "TB_USER_RULE")
public class UserRule {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RULE_SEQ")
	private long ruleSeq;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "RUNE_NAME", columnDefinition = "enum('RULE_USER', 'RULE_ADMIN')")
	@ColumnDefault("'ROLE_USER'")
	private UserERole ruleName;
	
	
}
