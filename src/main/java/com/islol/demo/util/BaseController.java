package com.islol.demo.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@Slf4j
@RestController
public class BaseController {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handelException(Exception ex) {
        JSONObject responseJson = getResponseJson(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), ex.getMessage(), new HashMap<>());
        return new ResponseEntity<>(responseJson.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    protected ResponseEntity<String> getResponseEntity(Object bodyObject) {
        JSONObject success = getResponseJson("0000", "SUCCESS", bodyObject);
        return new ResponseEntity<>(success.toString(), HttpStatus.OK);
    }

    protected ResponseEntity<String> getCreateResponseEntity(Object bodyObject) {
        JSONObject success = getResponseJson("0000", "SUCCESS", bodyObject);
        return new ResponseEntity<>(success.toString(), HttpStatus.CREATED);
    }

    private JSONObject getResponseJson(String resultCode, String resultMessage, Object bodyObject) {
        JSONObject responseJson = new JSONObject();
        JSONObject hadJson = new JSONObject();

        try {
            hadJson.put("resultCode ", resultCode);
            hadJson.put("resultMessage", resultMessage);
            responseJson.put("head", hadJson);
            responseJson.put("body", new JSONObject(new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(bodyObject)));
        } catch (JSONException | JsonProcessingException ex) {
            log.error("getResponseJson Exception", ex);
        }
        return responseJson;
    }
}
