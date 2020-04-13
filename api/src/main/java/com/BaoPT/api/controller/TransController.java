/**
 * 
 */
package com.BaoPT.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.BaoPT.api.bean.ResultBean;
import com.BaoPT.api.bean.TransEntity;
import com.BaoPT.api.service.TransService;
import com.BaoPT.api.utils.ApiValidateExeption;

/**
 * @author BaoPT
 *
 */

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("/api")
@RestController
public class TransController {

	@Autowired
	private TransService transService;

	/**
	 * @return Create New Transaction
	 */

	@RequestMapping(value = "/trans", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResultBean create(@RequestBody String json) {
		TransEntity transEntity = null;
		ResultBean resultBean = null;
		try {
			transEntity = transService.create(json);
		} catch (ApiValidateExeption e) {
			resultBean = new ResultBean(e.getCode(), e.getField(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		resultBean = new ResultBean(transEntity, "200", "Create Transaction Success");
		return resultBean;
	}

}