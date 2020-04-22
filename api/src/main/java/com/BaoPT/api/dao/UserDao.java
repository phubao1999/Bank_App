/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.BaoPT.api.dao;

import java.util.List;

import com.BaoPT.api.bean.UserEntity;
import com.BaoPT.api.model.UserInfo;

/**
 * [OVERVIEW] UserDao.
 *
 * @author: (VNEXT) BaoPT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2020/04/14      (VNEXT) BaoPT       Create new
*/

public interface UserDao {

    /**
     * getAll
     * @author: (VNEXT) BaoPT
     * @return List UserEntity
     */
    public List<UserEntity> getAll();

    /**
     * getUserById
     * @author: (VNEXT) BaoPT
     * @param id
     * @return UserEntity
     */
    public UserEntity getUserById(int id);

    /**
     * register
     * @author: (VNEXT) BaoPT
     * @param userEntity
     */
    public void register(UserEntity userEntity);

    /**
     * update
     * @author: (VNEXT) BaoPT
     * @param userEntity
     */
    public void update(UserEntity userEntity);

    /**
     * getInforUser
     * @author: (VNEXT) BaoPT
     * @param id
     * @return UserInfo
     */
    public UserInfo getInforUser(int id);

}
