/**
 * 
 */
package com.BaoPT.api.service.impl;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BaoPT.api.bean.UserEntity;
import com.BaoPT.api.dao.UserDao;
import com.BaoPT.api.service.UserService;
import com.BaoPT.api.utils.CustomException;

/**
 * @author BaoPT
 *
 */

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	@Override
	public List<UserEntity> getAll() {
		List<UserEntity> userList = userDao.getAll();
		return userList;
	}

	@Override
	public UserEntity loginById(String json) throws CustomException {
		JSONObject userJson = new JSONObject(json);
		if (userJson.isEmpty()) {
			throw new CustomException("Please Enter All Field");
		} else {
			UserEntity user = userDao.getUserById(userJson.getInt("id"));
			if (user == null) {
				throw new CustomException("Id User Is Not Found");
			} else {
				if (!user.getPassword().equals(userJson.getString("password"))) {
					throw new CustomException("Password Is Not Right");
				} else {
					return user;
				}
			}
		}
	}
	
	@Override
	public UserEntity register(String json) throws CustomException {
		// Check If User Exist => throw Exception
		JSONObject userJson = new JSONObject(json);
		if (userJson.isEmpty()) {
			throw new CustomException("Please Enter All Field");
		} else {
			UserEntity userEntity = new UserEntity();
			userEntity.setName(userJson.getString("name"));
			userEntity.setSdt(userJson.getString("sdt"));
			userEntity.setDayOfBirth(userJson.getString("day_of_birth"));
			userEntity.setPassword(userJson.getString("password"));
			userEntity.setIdBank(userJson.getInt("id_bank"));
			userEntity.setMonney(userJson.getInt("monney"));
			userDao.register(userEntity);
			return userEntity;			
		}
	}

}
