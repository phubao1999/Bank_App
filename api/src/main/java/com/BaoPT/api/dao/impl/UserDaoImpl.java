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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.BaoPT.api.bean.UserEntity;
import com.BaoPT.api.dao.UserDao;
import com.BaoPT.api.model.UserInfo;

/**
 * [OVERVIEW] UserDaoImpl.
 *
 * @author: (VNEXT) BaoPT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2020/04/14      (VNEXT) BaoPT       Create new
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
    public UserInfo getInforUser(int id) {
        // TODO Auto-generated method stub
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT new com.BaoPT.api.model.UserInfo ( ");
        sql.append("  u.idUser, u.name, u.sdt, u.password, u.idBank, u.monney, b.bankName) ");
        sql.append(" FROM ");
        sql.append("    UserEntity u ");
        sql.append(" JOIN ");
        sql.append("    BankEntity b ");
        sql.append(" ON ");
        sql.append(" u.idBank = b.idBank ");
        sql.append(" WHERE ");
        sql.append(" u.idUser = :id ");
        Query query = this.entityManager.createQuery(sql.toString());
        query.setParameter("id", id);
        UserInfo entity = null;
        try {
            entity = (UserInfo) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entity;
    }

}
