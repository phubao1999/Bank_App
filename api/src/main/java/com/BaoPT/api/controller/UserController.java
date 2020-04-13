/**
 * 
 */
package com.BaoPT.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.BaoPT.api.bean.ResultBean;
import com.BaoPT.api.bean.UserEntity;
import com.BaoPT.api.service.UserService;
import com.BaoPT.api.utils.ApiValidateExeption;

/**
 * @author BaoPT
 *
 */

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
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
	public ResultBean getAll() {
		List<UserEntity> userEntity = null;
		ResultBean resultBean = null;
		try {
			userEntity = userService.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		resultBean = new ResultBean(userEntity, "200", "Done");
		return resultBean;
	}

	/**
	 * @return Login
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResultBean login(@RequestBody String json) {
		UserEntity userEntity = null;
		ResultBean resultBean = null;
		try {
			userEntity = userService.loginById(json);
		} catch (ApiValidateExeption e) {
			resultBean = new ResultBean(e.getCode(), e.getField(), e.getMessage());
		}
		catch (Exception e) {
			e.printStackTrace();
			resultBean = new ResultBean("400", e.getMessage());
			return resultBean;
		}
		resultBean = new ResultBean(userEntity, "200", "Login Successfully");
		return resultBean;
	}

	/**
	 * @return Register User
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResultBean register(@RequestBody String json) {
		UserEntity userEntity = null;
		ResultBean resultBean = null;
		try {
			userEntity = userService.register(json);
		} catch (ApiValidateExeption e) {
			resultBean = new ResultBean(e.getCode(), e.getField(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		resultBean = new ResultBean(userEntity, "200", "Create Account Success");
		return resultBean;
	}
	
	/**
	 * @return Update User By Id
	 */
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "application/json")
	public @ResponseBody ResultBean updateUser(@RequestBody String json, @RequestParam Integer id) {
		UserEntity userUpdate = null;
		ResultBean resultBean = null;
		try {
			userUpdate = userService.update(json, id);
		} catch (ApiValidateExeption e) {
			resultBean = new ResultBean(e.getCode(), e.getField(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		resultBean = new ResultBean(userUpdate, "200", "Update Successfully");
		return resultBean;
	}
}
