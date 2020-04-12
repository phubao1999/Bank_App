/**
 * 
 */
package com.BaoPT.api.controller;

import java.util.List;

import javax.persistence.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.BaoPT.api.bean.UserEntity;
import com.BaoPT.api.service.UserService;
import com.BaoPT.api.utils.CustomException;
import com.BaoPT.api.utils.CustomStatus;

/**
 * @author BaoPT
 *
 */

@RequestMapping("/api")
@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
		
	
	/**
	 * @author BaoPT
	 * @return Get All Of User
	 */
	@RequestMapping(value = "/user", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<UserEntity>> getAll() {
		List<UserEntity> userEntity = null;
		try {
			userEntity = userService.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<UserEntity>>(HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(userEntity);
	}
	
	/**
	 * @return Login
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResponseEntity<UserEntity> login(@RequestBody UserEntity user) {
		UserEntity userEntity = null;
		try {
			userEntity = userService.loginById(user.getIdUser(), user.getPassword());
			return ResponseEntity.ok(userEntity);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<UserEntity>(HttpStatus.BAD_REQUEST);
		}
	}
	
	/**
	 * @return Register User
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResponseEntity<UserEntity> register(@RequestBody String json) {
		UserEntity userEntity = null;
//		CustomStatus customStatus = null;
		try {
			userEntity = userService.register(json);
//			customStatus = new CustomStatus("200", "Register Success", userEntity);
			return ResponseEntity.ok(userEntity);
		} catch (CustomException e) {
			e.getStackTrace();
			return new ResponseEntity<UserEntity>(HttpStatus.BAD_REQUEST);
		}
	}
}
