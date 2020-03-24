package com.briup.demo.utils;


	
/**
 * 自定义异常
 * @author DELL
 *
 */
public class CustomerException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	private Integer code;
	public Integer getCode;
	
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Integer getGetCode() {
		return getCode;
	}

	public void setGetCode(Integer getCode) {
		this.getCode = getCode;
	}

	public CustomerException(Integer code,String message) {
		super();
	}

	public CustomerException(Integer code, Integer getCode) {
		super();
		this.code = code;
		this.getCode = getCode;
	}
}
