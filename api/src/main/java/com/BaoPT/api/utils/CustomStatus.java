/**
 * 
 */
package com.BaoPT.api.utils;

import org.springframework.http.HttpStatus;

import java.io.Serializable;

import javax.persistence.Entity;

/**
 * @author BaoPT
 *
 */
public class CustomStatus implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private HttpStatus status;
	
	private String message;
	
	private Entity entity;
	
	public CustomStatus() {
		super();
	}
	
	public CustomStatus(HttpStatus status, String message, Entity entity) {
		this.status = status;
		this.message = message;
		this.entity = entity;
	}

	public CustomStatus(HttpStatus status, String message) {
		this.status = status;
		this.message = message;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}
	
}
