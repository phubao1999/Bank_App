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
import com.BaoPT.api.model.UserInfo;
import com.BaoPT.api.service.UserService;
import com.BaoPT.api.utils.ApiValidateExeption;

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
	public UserEntity loginById(String json) throws ApiValidateExeption {
		JSONObject userJson = new JSONObject(json);
		if (userJson.isEmpty()) {
			throw new ApiValidateExeption("400", "Please Enter All Field");
		} else {
			UserEntity user = userDao.getUserById(userJson.getInt("id"));
			if (user == null) {
				throw new ApiValidateExeption("400", "Id User Is Not Found");
			} else {
				if (!user.getPassword().equals(userJson.getString("password"))) {
					throw new ApiValidateExeption("400", "Password Is Not Right");
				} else {
					return user;
				}
			}
		}
	}
	
	@Override
	public UserEntity register(String json) throws ApiValidateExeption {
		// Check If User Exist => throw Exception
		JSONObject userJson = new JSONObject(json);
		if (userJson.isEmpty()) {
			throw new ApiValidateExeption("400", "Please Enter All Field");
		} else {
			UserEntity userEntity = new UserEntity();
			userEntity.setName(userJson.getString("name"));
			userEntity.setSdt(userJson.getString("sdt"));
			userEntity.setDayOfBirth(userJson.getString("day_of_birth"));
			userEntity.setPassword(userJson.getString("password"));
//			userEntity.setIdBank(userJson.getInt("id_bank"));
			userEntity.setMonney(userJson.getInt("monney"));
			userDao.register(userEntity);
			return userEntity;	
		}
	}

	@Override
	public UserEntity update(String json, int id) throws ApiValidateExeption {
		JSONObject userJson = new JSONObject(json);
		if (userJson.isEmpty()) {
			throw new ApiValidateExeption("400", "Please Enter All Field");
		} else {
			UserEntity userUpdate = userDao.getUserById(id);
			userUpdate.setName(userJson.getString("name"));
			userUpdate.setSdt(userJson.getString("sdt"));
			userUpdate.setDayOfBirth(userJson.getString("day_of_birth"));
			userUpdate.setPassword(userJson.getString("password"));
//			userUpdate.setIdBank(userJson.getInt("id_bank"));
			userUpdate.setMonney(userJson.getInt("monney"));
			userDao.update(userUpdate);
			return userUpdate;
		}
	}

	@Override
	public UserInfo getInfoUser(int id) throws ApiValidateExeption {
		UserInfo userInfo = userDao.getInforUser(id);
		if (userInfo == null) {
			throw new ApiValidateExeption("400", "User Is Not Exist");
		}
		return userInfo;
	}

}
