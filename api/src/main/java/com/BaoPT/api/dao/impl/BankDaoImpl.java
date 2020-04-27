/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.BaoPT.api.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.BaoPT.api.bean.BankEntity;
import com.BaoPT.api.dao.BankDao;
import com.BaoPT.api.service.impl.UserServiceImpl;

/**
 * [OVERVIEW] XXXXX.
 *
 * @author: (VNEXT) BaoPT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2020/04/18      (VNEXT) BaoPT       Create new
*/

@Repository(value = "bankDao")
@Transactional
public class BankDaoImpl implements BankDao {

    private static final Log log = LogFactory.getLog(UserServiceImpl.class);

    @Autowired
    private EntityManager entityManager;

    /**
     * @author (VNEXT) BaoPT
     * @return Get All Record
     */
    @Override
    public List<BankEntity> getAll() {
        return this.entityManager.createQuery("FROM BankEntity b", BankEntity.class).getResultList();
    }

    /**
     * @author (VNEXT) BaoPT
     * @return Get col Record By Id
     * @throws Exception 
     */
    @Override
    public BankEntity getBank(int id) {
        log.debug("### Get Bank Start ###");
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT b ");
        sql.append(" FROM BankEntity b ");
        sql.append(" WHERE ");
        sql.append(" b.idBank = :id ");
        Query query = this.entityManager.createQuery(sql.toString());
        query.setParameter("id", id);
        BankEntity entity = null;
        try {
            entity = (BankEntity) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.debug("### Get Bank End ###");
        return entity;
    }

}
