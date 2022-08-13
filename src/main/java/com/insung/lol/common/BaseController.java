package com.insung.lol.common;


import com.insung.lol.common.exception.BizException;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.insung.lol.common.constant.ApiConstant;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
* @packageName 	: com.insung.lol.common
* @fileName 	: BaseController.java
* @author 		: Seung Hyo
* @date 		: 2021.10.29
* @description 	: 컨트롤러에서 공통적으로 사용하는 함수를 정의한다.
* ===========================================================
* DATE 			AUTHOR 		NOTE
* -----------------------------------------------------------
* 2021.10.29 	Seung Hyo 	최초 생성
*/
@Slf4j
@Controller
public class BaseController {

	@ExceptionHandler(BizException.class)
	public ResponseEntity<String> BizExceptionHand(HttpServletRequest request, HttpServletResponse response, BizException e) {
		log.error("BizException {}", e);
		JSONObject responseJson = getResponseJson(e.getCode(), e.getMessage(), new HashMap<>());
		return new ResponseEntity<String>(responseJson.toString(), HttpStatus.OK);
	}


	/**
	 * @fileName	: getResponseEntity
	 * @author		: "sajuni11"
	 * @date		: 2021. 8. 7.
	 * @description	: 공통 json응답 생성
	 */
	protected ResponseEntity<String> getResponseEntity(Object bodyObject){
		JSONObject json = getResponseJson(ApiConstant.RESULT_CODE_SUCCESS, ApiConstant.RESULT_MESSAGE_SUCCESS, bodyObject);
		return new ResponseEntity<>(json.toString(), HttpStatus.OK);
	}

	protected ResponseEntity<String> getErrorEntity(Object bodyObject) {
		JSONObject json = getResponseJson(ApiConstant.RESULT_CODE_FAIL, ApiConstant.RESULT_MESSAGE_FAIL, bodyObject);
		return new ResponseEntity<>(json.toString(), HttpStatus.OK);
	}

	protected ResponseEntity<String> getSaveEntity(Object bodyObject) {
		JSONObject json = getResponseJson(ApiConstant.RESULT_CODE_SUCCESS, ApiConstant.RESULT_MESSAGE_SUCCESS, bodyObject);
		return new ResponseEntity<>(json.toString(), HttpStatus.CREATED);
	}

	protected ResponseEntity<String> getUpdateEntity(Object bodyObject) {
		return new ResponseEntity<>(HttpStatus.OK);
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
			responsJson.put(ApiConstant.API_BODY, new JSONObject(new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(bodyObject)));
		} catch (JSONException | JsonProcessingException ex) {
			log.error("getResponseJson exception", ex);
		}

		return responsJson;
	}

	// 벨리데이션 에러 처리
	protected ResponseEntity<String> getRequestEntityError(BindingResult bindingResult) {
		List<HashMap<String, String>> collect = bindingResult.getFieldErrors().stream()
				.map(v -> new HashMap<String, String>() {{put(v.getField(), v.getDefaultMessage());}}).collect(Collectors.toList());

		log.error("리퀘스트 엔티티 에러: {}", bindingResult.getFieldError());
		Map<String, Object> responseData = new HashMap<>();
		responseData.put("requestError", collect);
		return getErrorEntity(responseData);
	}

}
