/**
 * 
 */
package com.BaoPT.api.dao.impl;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.BaoPT.api.bean.TransEntity;
import com.BaoPT.api.dao.TransDao;

/**
 * @author BaoPT
 *
 */

@Repository(value = "transDao")
@Transactional()
public class TransDaoImpl implements TransDao {

	@Autowired
	private EntityManager entityManager;
	
	/**
	 * @return Create new Item
	 */
	@Override
	public void createTrans(TransEntity trans) {
		this.entityManager.persist(trans);
	}

}
