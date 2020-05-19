/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.BaoPT.api.service;

import java.util.List;

import com.BaoPT.api.bean.UserEntity;
import com.BaoPT.api.model.JwtResponse;
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

    /**
     * getAll
     * @author: (VNEXT) BaoPT
     * @return List UserEntity
     */
    public List<UserEntity> getAll();

    /**
     * register
     * @author: (VNEXT) BaoPT
     * @param json
     * @return UserEntity
     * @throws ApiValidateExeption
     */
    public UserEntity register(String json) throws ApiValidateExeption;

    /**
     * update
     * @author: (VNEXT) BaoPT
     * @param json
     * @param id
     * @return UserEntity
     * @throws ApiValidateExeption
     */
    public UserEntity update(String json, int id) throws ApiValidateExeption;

    /**
     * getInfoUser
     * @author: (VNEXT) BaoPT
     * @param id
     * @return UserInfo
     * @throws ApiValidateExeption
     */
    public UserInfo getInfoUser(int id) throws ApiValidateExeption;

    /**
     * changePassword
     * @author: (VNEXT) BaoPT
     * @param id
     * @param json
     * @return UserEntity
     * @throws ApiValidateExeption
     */
    public UserEntity changePassword(int id, String json) throws ApiValidateExeption;

    /**
     * getByUsername
     * @author: (VNEXT) BaoPT
     * @param username
     * @return UserEntity
     */
    public UserEntity getByUsername(String username);

    /**
     * login
     * @author: (VNEXT) BaoPT
     * @param json
     * @return JwtResponse
     * @throws ApiValidateExeption
     * @throws Exception
     */
    public JwtResponse login(String json) throws ApiValidateExeption, Exception;
    
    /**
     * 
     * @author: (VNEXT) BaoPT
     * @param email
     * @return otpNumber
     * @throws ApiValidateExeption
     * @throws Exception
     */
    public String otpNumber(String json) throws ApiValidateExeption, Exception;

}
