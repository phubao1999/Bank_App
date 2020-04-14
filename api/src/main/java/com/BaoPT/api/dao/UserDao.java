/**
 * 
 */
package com.BaoPT.api.dao;

import java.util.List;

import com.BaoPT.api.bean.UserEntity;
import com.BaoPT.api.model.UserInfo;

/**
 * @author BaoPT
 *
 */
public interface UserDao {

	public List<UserEntity> getAll();
	
	public UserEntity getUserById(int id);
	
	public void register(UserEntity userEntity);
	
	public void update(UserEntity userEntity);
	
	public UserInfo getInforUser(int id);
			
}
