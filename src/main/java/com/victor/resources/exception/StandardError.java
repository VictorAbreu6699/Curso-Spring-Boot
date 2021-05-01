package com.victor.resources.exception;

import java.io.Serializable;
/**
 * Classe responsável por representar uma mensagem de erro de REST.
 * @author Victor
 *
 */
public class StandardError implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	private Integer status;
	private String message;	
	private long timeStamp;
		
	public StandardError(Integer status, String message, long timeStamp) {
		super();
		this.status = status;
		this.message = message;
		this.timeStamp = timeStamp;
	}
	
	public Integer getStatus() {
		return status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}	
	
}
