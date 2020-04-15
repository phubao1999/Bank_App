/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.BaoPT.api.service.impl;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BaoPT.api.bean.UserEntity;
import com.BaoPT.api.common.Validate;
import com.BaoPT.api.dao.UserDao;
import com.BaoPT.api.model.UserInfo;
import com.BaoPT.api.service.UserService;
import com.BaoPT.api.utils.ApiValidateExeption;

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

    @Autowired
    private UserDao userDao;

    @Override
    public List<UserEntity> getAll() {
        List<UserEntity> userList = userDao.getAll();
        return userList;
    }

    @Override
    public UserEntity loginById(String json) throws ApiValidateExeption {
        JSONObject userJson = new JSONObject(json);
        if (userJson.isEmpty()) {
            throw new ApiValidateExeption("400", "Please Enter All Field");
        } else {
            UserEntity user = userDao.getUserById(userJson.getInt("id"));
            if (user == null) {
                throw new ApiValidateExeption("400", "Id User Is Not Found");
            } else {
                if (!user.getPassword().equals(userJson.getString("password"))) {
                    throw new ApiValidateExeption("400", "Password Is Not Right");
                } else {
                    return user;
                }
            }
        }
    }

    @Override
    public UserEntity register(String json) throws ApiValidateExeption {
        // Check If User Exist => throw Exception
        JSONObject userJson = new JSONObject(json);
        if (userJson.isEmpty()) {
            throw new ApiValidateExeption("400", "Please Enter All Field");
        } else {
            UserEntity userEntity = new UserEntity();
            if (!userJson.getString("name").trim().matches(Validate.USER_NAME)) {
                throw new ApiValidateExeption("400", "Name Have To Be Less Than 10 Character");
            } else if (userJson.getString("sdt").length() < 10 || userJson.getString("sdt").length() > 11) {
                throw new ApiValidateExeption("400", "Phone Number Is Between 10 and 11 Number");
            } else if (!userJson.getString("day_of_birth").trim().matches(Validate.DATE)) {
                throw new ApiValidateExeption("400", "Day of birth must like ex: 1999/08/27");
            } else if (!userJson.getString("password").matches(Validate.PASSWORD)) {
                throw new ApiValidateExeption("400",
                        "Password Must Have Less Then 8 Character, Must Have Character, Number And Special Character. Ex: Bao@123");
            } else if (userJson.getInt("id_bank") > 3) {
                throw new ApiValidateExeption("400", "Id Bank Must Be Between 1 and 3");
            } 
            else {
                userEntity.setName(userJson.getString("name"));
                userEntity.setSdt(userJson.getString("sdt"));
                userEntity.setDayOfBirth(userJson.getString("day_of_birth"));
                userEntity.setPassword(userJson.getString("password"));
                userEntity.setMonney(userJson.getInt("monney"));
                userEntity.setIdBank(userJson.getInt("id_bank"));
            }

            userDao.register(userEntity);
            return userEntity;
        }
    }

    @Override
    public UserEntity update(String json, int id) throws ApiValidateExeption {
        JSONObject userJson = new JSONObject(json);
        if (userJson.isEmpty()) {
            throw new ApiValidateExeption("400", "Please Enter All Field");
        } else {
            UserEntity userUpdate = userDao.getUserById(id);

            if (!userJson.getString("name").trim().matches(Validate.USER_NAME)) {
                throw new ApiValidateExeption("400", "Name Have To Be Less Than 10 Character");
            } else if (userJson.getString("sdt").length() < 10 || userJson.getString("sdt").length() > 11) {
                throw new ApiValidateExeption("400", "Phone Number Is Between 10 and 11 Number");
            } else if (!userJson.getString("day_of_birth").trim().matches(Validate.DATE)) {
                throw new ApiValidateExeption("400", "Day of birth must like ex: 1999/08/27");
            } else {
                userUpdate.setName(userJson.getString("name"));
                userUpdate.setSdt(userJson.getString("sdt"));
                userUpdate.setDayOfBirth(userJson.getString("day_of_birth"));
            }

            userDao.update(userUpdate);
            return userUpdate;
        }
    }

    @Override
    public UserInfo getInfoUser(int id) throws ApiValidateExeption {
        UserInfo userInfo = userDao.getInforUser(id);
        if (userInfo == null) {
            throw new ApiValidateExeption("400", "User Is Not Exist");
        }
        String dobFormat = userInfo.getDob().replace("-", "/");
        userInfo.setDob(dobFormat);
        return userInfo;
    }

    @Override
    public UserEntity changePassword(int id, String json) throws ApiValidateExeption {
        UserEntity userUpdatePassword = userDao.getUserById(id);
        JSONObject userJson = new JSONObject(json);
        if (userUpdatePassword == null) {
            throw new ApiValidateExeption("400", "User Is Not Exist");
        } else if (userJson.isEmpty()) {
            throw new ApiValidateExeption("400", "Please Enter All Field");
        } else if (!userJson.getString("password").matches(Validate.PASSWORD)) {
            throw new ApiValidateExeption("400", "Password Must Have Less Then 8 Character, Must Have Character, Number And Special Character. Ex: Bao@123");
        } else {
            userUpdatePassword.setPassword(userJson.getString("password"));
            userDao.update(userUpdatePassword);
            return userUpdatePassword;
        }
    }

}
