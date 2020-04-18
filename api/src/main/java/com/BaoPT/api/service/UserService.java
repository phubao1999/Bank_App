/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.BaoPT.api.service;

import java.util.List;
import java.util.UUID;

import com.BaoPT.api.bean.UserEntity;
import com.BaoPT.api.model.UserInfo;
import com.BaoPT.api.utils.ApiValidateExeption;

/**
 * [OVERVIEW] UserService.
 *
 * @author: (VNEXT) BaoPT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2020/04/14      (VNEXT) BaoPT       Create new
*/

public interface UserService {

    public List<UserEntity> getAll();

    public UserEntity loginById(String json) throws ApiValidateExeption;

    public UserEntity register(String json) throws ApiValidateExeption;

    public UserEntity update(String json, int id, UUID token) throws ApiValidateExeption;

    public UserInfo getInfoUser(int id, UUID token) throws ApiValidateExeption;

    public UserEntity changePassword(int id, String json, UUID token) throws ApiValidateExeption;

}
