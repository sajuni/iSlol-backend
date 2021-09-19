package com.insung.lol.common.constant;

/**
 * @fileName	: ApiConstant.java
 * @author		: "sajuni11"
 * @date		: 2021. 8. 7.
 * @description	: API 키/코드 값 관련 Constant
 */
public class ApiConstant {
	// API응답 헤더 키
	public static final String API_HEAD = "head";
	// API응답 결과코드 키
	public static final String API_HEAD_RESULT_CODE = "resultCode";
	// API응답 결과메세지
	public static final String API_HEAD_RESULT_MESSAGE = "resultMessage";
	// API응답 결과 바디 키
	public static final String API_BODY = "body";
	
	// API응답 성공코드
	public static final String RESULT_CODE_SUCCESS = "0000";
	// API응답 실패코드
	public static final String RESULT_CODE_FAIL = "9999";
	// API응답 성공메세지
	public static final String RESULT_MESSAGE_SUCCESS = "SUCCESS";
	// API응답 실패메세지
	public static final String RESULT_MESSAGE_FAIL = "FAIL";
	
}
