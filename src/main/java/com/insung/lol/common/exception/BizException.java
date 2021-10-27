package com.insung.lol.common.exception;



/** 
* @packageName 		: com.insung.lol.common.exception 
* @fileName 		: BizException.java 
* @author 			: Seung Hyo 
* @date 			: 2021.10.16 
* @description 		: 비즈니스 exception 처리
* =========================================================== 
* DATE 					   AUTHOR 			NOTE * 
----------------------------------------------------------- 
* 2021.10.16 			   Seung Hyo 	    최초 생성 
*/
public class BizException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private String code = "";
	
	public BizException() {
		super("errorMessage", null);
	}
	
	public BizException(String code) {
		super("errorMessage", null);
		this.code = code;
	}
	
	public BizException(String code, String message) {
		super(message, null);
		this.code = code;
	}
	
	public String getCode() {
		return this.code;
	}
	
}
