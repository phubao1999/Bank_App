/**
 * 
 */
package com.BaoPT.api.service;

import java.util.List;

import com.BaoPT.api.bean.UserEntity;
import com.BaoPT.api.model.UserInfo;
import com.BaoPT.api.utils.ApiValidateExeption;

/**
 * @author BaoPT
 *
 */
public interface UserService {
	
	public List<UserEntity> getAll();
	
	public UserEntity loginById(String json) throws ApiValidateExeption;
	
	public UserEntity register(String json) throws ApiValidateExeption;
	
	public UserEntity update(String json, int id) throws ApiValidateExeption;
	
	public UserInfo getInfoUser(int id) throws ApiValidateExeption;
	
}
