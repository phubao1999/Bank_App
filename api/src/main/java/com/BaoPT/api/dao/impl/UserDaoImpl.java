/**
 * 
 */
package com.BaoPT.api.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.BaoPT.api.bean.UserEntity;
import com.BaoPT.api.dao.UserDao;

/**
 * @author BaoPT
 *
 */

@Repository(value = "userDao")
@Transactional()
public class UserDaoImpl implements UserDao {

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public List<UserEntity> getAll() {
		return entityManager.createQuery("FROM UserEntity ue", UserEntity.class).getResultList();
	}

	@Override
	public UserEntity getUserById(int id) {
		return entityManager.find(UserEntity.class, id);
	}

	@Override
	public void register(UserEntity userEntity) {
		entityManager.persist(userEntity);
	}

	@Override
	public void update(UserEntity userEntity) {
		entityManager.merge(userEntity);
	}

	@Override
	public void getInforUser(int id) {
		// TODO Auto-generated method stub
		
	}

}
