/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.BaoPT.api.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.BaoPT.api.bean.OtpEntity;
import com.BaoPT.api.dao.OtpDao;

/**
 * [OVERVIEW] XXXXX.
 *
 * @author: (VNEXT) BaoPT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2020/05/20      (VNEXT) BaoPT       Create new
*/

@Repository(value = "otpDao")
@Transactional
public class OtpDaoImpl implements OtpDao {

    @Autowired
    private EntityManager entityManager;

    @Override
    public void createOtp(OtpEntity otpEntity) {
        this.entityManager.persist(otpEntity);
    }

    @Override
    public OtpEntity getOtpCode(int idUser) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT o ");
        sql.append(" FROM ");
        sql.append("    OtpEntity o ");
        sql.append(" WHERE ");
        sql.append("    o.idUser = : id ");
        Query query = this.entityManager.createQuery(sql.toString());
        query.setParameter("id", idUser);
        OtpEntity otp = null;
        try {
            otp = (OtpEntity) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return otp;
    }

}
