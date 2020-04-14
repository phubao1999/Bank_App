/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.BaoPT.api.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.BaoPT.api.bean.TransEntity;
import com.BaoPT.api.dao.TransDao;

/**
 * [OVERVIEW] TransDaoImpl.
 *
 * @author: (VNEXT) BaoPT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2020/04/14      (VNEXT) BaoPT       Create new
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

	@SuppressWarnings("unchecked")
	@Override
	public List<TransEntity> getAllById(int id) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT t ");
		sql.append(" FROM ");
		sql.append("    TransEntity t ");
		sql.append(" WHERE ");
		sql.append("    t.idUser = :id ");
		Query query = this.entityManager.createQuery(sql.toString());
		query.setParameter("id", id);
		List<TransEntity> entity = null;
		try {
			entity = (List<TransEntity>) query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TransEntity> filter(int id, Date from, Date to) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT t ");
		sql.append(" FROM ");
		sql.append("   TransEntity t ");
		sql.append(" WHERE ");
		sql.append("    t.idUser = :id ");
		sql.append(" AND ");
		sql.append("    t.tranfferDay > :from ");
		sql.append(" AND ");
		sql.append("    t.tranfferDay < :to ");
		Query query = this.entityManager.createQuery(sql.toString());
		query.setParameter("id", id);
		query.setParameter("from", from);
		query.setParameter("to", to);
		List<TransEntity> entity = null;
		try {
			entity = (List<TransEntity>) query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entity;
	}

}
