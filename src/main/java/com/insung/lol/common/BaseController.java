package com.insung.lol.common;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.insung.lol.common.constant.ApiConstant;

import lombok.extern.slf4j.Slf4j;


/**
 * @fileName	: BaseController.java
 * @author		: "sajuni11"
 * @date		: 2021. 8. 7.
 * @description	: 컨트롤러에서 공통적으로 사용하는 함수를 정의한다.
 */
@Slf4j
public class BaseController {

	/**
	 * @fileName	: getResponseEntity
	 * @author		: "sajuni11"
	 * @date		: 2021. 8. 7.
	 * @description	: 공통 json응답 생성
	 */
	protected ResponseEntity<String> getResponseEntity(Object bodyObject){
		JSONObject json = getResponseJson(ApiConstant.RESULT_CODE_SUCCESS, ApiConstant.RESULT_MESSAGE_SUCCESS, bodyObject);
		return new ResponseEntity<String>(json.toString(), HttpStatus.OK);
	}
	
	/**
	 * @fileName	: getResponseJson
	 * @author		: "sajuni11"
	 * @date		: 2021. 8. 7.
	 * @description	: 공통 json응답 생성
	 */
	private JSONObject getResponseJson(String resultCode, String resultMessage, Object bodyObject) {
		JSONObject responsJson = new JSONObject();
		JSONObject headJson = new JSONObject();
		
		try {
			headJson.put(ApiConstant.API_HEAD_RESULT_CODE, resultCode);
			headJson.put(ApiConstant.API_HEAD_RESULT_MESSAGE, resultMessage);
			
			responsJson.put(ApiConstant.API_HEAD, headJson);
			responsJson.put(ApiConstant.API_BODY, new JSONObject(new ObjectMapper().writeValueAsString(bodyObject)));
		} catch (JSONException | JsonProcessingException ex) {
			log.error("getResponseJson exception", ex);
		}
		
		return responsJson;
	}
}