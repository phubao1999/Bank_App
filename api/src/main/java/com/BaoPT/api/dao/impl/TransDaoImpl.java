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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.BaoPT.api.bean.TransEntity;
import com.BaoPT.api.dao.TransDao;
import com.BaoPT.api.service.impl.UserServiceImpl;

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

    private static final Log log = LogFactory.getLog(UserServiceImpl.class);

    @Autowired
    private EntityManager entityManager;

    /**
     * @author (VNEXT) BaoPT
     * @return Create New Col
     */
    @Override
    public void createTrans(TransEntity trans) {
        this.entityManager.persist(trans);
    }

    /**
     * @author (VNEXT) BaoPT
     * @return Filter Record By Id
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<TransEntity> getAllById(int id) {
        log.debug("### Get Transaction By Id Start ###");
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
        log.debug("### Get Transaction By Id End ###");
        return entity;
    }

    /**
     * @author (VNEXT) BaoPT
     * @return Get col Record By Id Filter By Date
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<TransEntity> filter(int id, Date from, Date to) {
        log.debug("### Get Transaction By Id and Date Start ###");
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
        log.debug("### Get Transaction By Id and Date End ###");
        return entity;
    }

}
