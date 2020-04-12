/**
 * 
 */
package com.BaoPT.api.service;

import java.util.List;

import com.BaoPT.api.bean.UserEntity;
import com.BaoPT.api.utils.CustomException;

/**
 * @author BaoPT
 *
 */
public interface UserService {
	
	public List<UserEntity> getAll();
	
	public UserEntity loginById(int id, String password) throws CustomException;
	
	public UserEntity register(String json) throws CustomException;
	
}
