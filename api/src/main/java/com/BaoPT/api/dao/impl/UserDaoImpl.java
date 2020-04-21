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

import com.BaoPT.api.bean.UserEntity;
import com.BaoPT.api.dao.UserDao;
import com.BaoPT.api.model.UserInfo;
import com.BaoPT.api.service.impl.UserServiceImpl;

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

    private static final Log log = LogFactory.getLog(UserServiceImpl.class);

    @Autowired
    private EntityManager entityManager;

    /**
     * @author (VNEXT) BaoPT
     * @return Get All Record
     */
    @Override
    public List<UserEntity> getAll() {
        return entityManager.createQuery("FROM UserEntity ue", UserEntity.class).getResultList();
    }

    /**
     * @author (VNEXT) BaoPT
     * @return Get Cols By Id
     */
    @Override
    public UserEntity getUserById(int id) {
        return entityManager.find(UserEntity.class, id);
    }

    /**
     * @author (VNEXT) BaoPT
     * @return Create New Record
     */
    @Override
    public void register(UserEntity userEntity) {
        entityManager.persist(userEntity);
    }

    /**
     * @author (VNEXT) BaoPT
     * @return Update Record
     */
    @Override
    public void update(UserEntity userEntity) {
        entityManager.merge(userEntity);
    }

    /**
     * @author (VNEXT) BaoPT
     * @return Join User And Bank Table by "id_bank" And Then Get That Record
     */
    @Override
    public UserInfo getInforUser(int id) {
        log.debug("### Get User Info Start ###");
        // TODO Auto-generated method stub
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT new com.BaoPT.api.model.UserInfo ( ");
        sql.append("  u.idUser, u.username, u.sdt, u.password, u.idBank, u.monney, b.bankName, u.dayOfBirth) ");
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
        log.debug("### Get User Info End ###");
        return entity;
    }

}
