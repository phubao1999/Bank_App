/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.BaoPT.api.service.impl;

import java.util.List;
import java.util.UUID;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BaoPT.api.bean.UserEntity;
import com.BaoPT.api.common.CheckToken;
import com.BaoPT.api.common.EncodeDecode;
import com.BaoPT.api.dao.UserDao;
import com.BaoPT.api.model.UserInfo;
import com.BaoPT.api.service.UserService;
import com.BaoPT.api.utils.ApiValidateExeption;
import com.BaoPT.api.utils.Constant;
import com.BaoPT.api.utils.Validate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * [OVERVIEW] UserServiceImpl.
 *
 * @author: (VNEXT) BaoPT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2020/04/14      (VNEXT) BaoPT       Create new
*/

@Service
public class UserServiceImpl implements UserService {

    private static final Log log = LogFactory.getLog(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private CheckToken checkToken;

    @Autowired
    private EncodeDecode encodeDecode;

    @Override
    public List<UserEntity> getAll() {
        List<UserEntity> userList = userDao.getAll();
        return userList;
    }

    /**
     * @author (VNEXT) BaoPT
     * @param Token (UUID)
     * @return Login
     */
    @Override
    public UserEntity loginById(String json) throws ApiValidateExeption {
        log.debug("### Login START ###");
        JSONObject userJson = new JSONObject(json);
        if (userJson.isEmpty()) {
            throw new ApiValidateExeption(Constant.BAD_REQUEST, "Please Enter All Field");
        } else {
            UserEntity user = userDao.getUserById(userJson.getInt("id"));
            if (user == null) {
                throw new ApiValidateExeption(Constant.BAD_REQUEST, "Id User Is Not Found");
            } else {
                if (!user.getPassword().equals(this.encodeDecode.encode(userJson.getString("password")))) {
                    throw new ApiValidateExeption(Constant.BAD_REQUEST, "Password Is Not Right");
                } else {
                    log.debug("### Login End ###");
                    return user;
                }
            }
        }
    }

    /**
     * @author (VNEXT) BaoPT
     * @param json
     * @return Register User
     */
    @Override
    public UserEntity register(String json) throws ApiValidateExeption {
        log.debug("### Register Start ###");
        // Check If User Exist => throw Exception
        JSONObject userJson = new JSONObject(json);
        if (userJson.isEmpty()) {
            throw new ApiValidateExeption(Constant.BAD_REQUEST, "Please Enter All Field");
        } else {
            UserEntity userEntity = new UserEntity();
            if (!userJson.getString("name").trim().matches(Validate.USER_NAME)) {
                throw new ApiValidateExeption(Constant.BAD_REQUEST, "Name Have To Be Less Than 10 Character");
            } else if (userJson.getString("sdt").length() < 10 || userJson.getString("sdt").length() > 11) {
                throw new ApiValidateExeption(Constant.BAD_REQUEST, "Phone Number Is Between 10 and 11 Number");
            } else if (!userJson.getString("day_of_birth").trim().matches(Validate.DATE)) {
                throw new ApiValidateExeption(Constant.BAD_REQUEST, "Day of birth must like ex: 1999/08/27");
            } else if (!userJson.getString("password").matches(Validate.PASSWORD)) {
                throw new ApiValidateExeption(Constant.BAD_REQUEST,
                        "Password Must Have Less Then 8 Character, Must Have Character, Number And Special Character. Ex: Bao@123");
            } else if (userJson.getInt("id_bank") > 3) {
                throw new ApiValidateExeption(Constant.BAD_REQUEST, "Id Bank Must Be Between 1 and 3");
            } else {
                userEntity.setName(userJson.getString("name"));
                userEntity.setSdt(userJson.getString("sdt"));
                userEntity.setDayOfBirth(userJson.getString("day_of_birth"));
                userEntity.setPassword(this.encodeDecode.encode(userJson.getString("password")));
                userEntity.setMonney(userJson.getInt("monney"));
                userEntity.setIdBank(userJson.getInt("id_bank"));
                userEntity.setToken(UUID.randomUUID());
            }

            userDao.register(userEntity);
            log.debug("### Register End ###");
            return userEntity;
        }
    }

    /**
     * @author (VNEXT) BaoPT
     * @param id
     * @param json
     * @param Token (UUID)
     * @return Update User Info
     */
    @Override
    public UserEntity update(String json, int id, UUID token) throws ApiValidateExeption {
        log.debug("### Update User Start ###");
        JSONObject userJson = new JSONObject(json);
        if (userJson.isEmpty()) {
            throw new ApiValidateExeption(Constant.BAD_REQUEST, "Please Enter All Field");
        } else {
            UserEntity userUpdate = userDao.getUserById(id);

            if (!userJson.getString("name").trim().matches(Validate.USER_NAME)) {
                throw new ApiValidateExeption(Constant.BAD_REQUEST, "Name Have To Be Less Than 10 Character");
            } else if (userJson.getString("sdt").length() < 10 || userJson.getString("sdt").length() > 11) {
                throw new ApiValidateExeption(Constant.BAD_REQUEST, "Phone Number Is Between 10 and 11 Number");
            } else if (!userJson.getString("day_of_birth").trim().matches(Validate.DATE)) {
                throw new ApiValidateExeption(Constant.BAD_REQUEST, "Day of birth must like ex: 1999/08/27");
            } else if (!this.checkToken.checkToken(id, token)) {
                throw new ApiValidateExeption(Constant.BAD_REQUEST, "Invalid Token");
            } else {
                userUpdate.setName(userJson.getString("name"));
                userUpdate.setSdt(userJson.getString("sdt"));
                userUpdate.setDayOfBirth(userJson.getString("day_of_birth"));
            }

            userDao.update(userUpdate);
            log.debug("### Update User End ###");
            return userUpdate;
        }
    }

    /**
     * @author (VNEXT) BaoPT
     * @param id
     * @param Token (UUID)
     * @return Get User Info
     */
    @Override
    public UserInfo getInfoUser(int id, UUID token) throws ApiValidateExeption {
        log.debug("### Get User Info Start ###");
        UserInfo userInfo = userDao.getInforUser(id);
        if (userInfo == null) {
            throw new ApiValidateExeption(Constant.BAD_REQUEST, "User Is Not Exist");
        } else if (!this.checkToken.checkToken(id, token)) {
            throw new ApiValidateExeption(Constant.BAD_REQUEST, "Invalid Token");
        }
        String dobFormat = userInfo.getDob().replace("-", "/");
        userInfo.setDob(dobFormat);
        log.debug("### Get User Info End ###");
        return userInfo;
    }

    /**
     * @author (VNEXT) BaoPT
     * @param id
     * @param json
     * @param Token (UUID)
     * @return Change Password User
     */
    @Override
    public UserEntity changePassword(int id, String json, UUID token) throws ApiValidateExeption {
        log.debug("### Change Password Start ###");
        UserEntity userUpdatePassword = userDao.getUserById(id);
        JSONObject userJson = new JSONObject(json);
        if (userUpdatePassword == null) {
            throw new ApiValidateExeption(Constant.BAD_REQUEST, "User Is Not Exist");
        } else if (userJson.isEmpty()) {
            throw new ApiValidateExeption(Constant.BAD_REQUEST, "Please Enter All Field");
        } else if (!userJson.getString("password").matches(Validate.PASSWORD)) {
            throw new ApiValidateExeption(Constant.BAD_REQUEST, "Password Must Have Less Then 8 Character, Must Have Character, Number And Special Character. Ex: Bao@123");
        } else if (!this.checkToken.checkToken(id, token)) {
            throw new ApiValidateExeption(Constant.BAD_REQUEST, "Invalid Token");
        } else {
            userUpdatePassword.setPassword(userJson.getString("password"));
            userDao.update(userUpdatePassword);
            log.debug("### Change Password End ###");
            return userUpdatePassword;
        }
    }

}
