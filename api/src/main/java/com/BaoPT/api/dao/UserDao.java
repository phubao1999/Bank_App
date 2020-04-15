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

    public List<UserEntity> getAll();

    public UserEntity getUserById(int id);

    public void register(UserEntity userEntity);

    public void update(UserEntity userEntity);

    public UserInfo getInforUser(int id);

}
