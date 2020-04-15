/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.BaoPT.api.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BaoPT.api.bean.UserEntity;
import com.BaoPT.api.dao.UserDao;
import com.BaoPT.api.model.TransfferMoney;
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
            
            userEntity.setName(userJson.getString("name"));
            userEntity.setSdt(userJson.getString("sdt"));
            userEntity.setDayOfBirth(userJson.getString("day_of_birth"));
            userEntity.setPassword(userJson.getString("password"));
            userEntity.setMonney(userJson.getInt("monney"));
            userEntity.setIdBank(userJson.getInt("id_bank"));
            
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
            
            userUpdate.setName(userJson.getString("name"));
            userUpdate.setSdt(userJson.getString("sdt"));
            userUpdate.setDayOfBirth(userJson.getString("day_of_birth"));
            
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
        } else {
            userUpdatePassword.setPassword(userJson.getString("password"));
            userDao.update(userUpdatePassword);
            return userUpdatePassword;
        }
    }

    @Override
    public TransfferMoney addMonney(int id, String json) throws ApiValidateExeption {
        UserEntity userUpdateMonney = userDao.getUserById(id);
        JSONObject userJson = new JSONObject(json);
        if (userUpdateMonney == null) {
            throw new ApiValidateExeption("400", "User Is Not Exist");
        } else if (userJson.isEmpty()) {
            throw new ApiValidateExeption("400", "Please Enter All Field");
        } else {
            int idUser = userUpdateMonney.getIdUser();
            int idBank = userUpdateMonney.getIdBank();
            int status = 1;
            int monneyTranffer = userJson.getInt("monney");
            Timestamp tranfferDay = new Timestamp(System.currentTimeMillis());
            int fee = 0;
            int monney = userUpdateMonney.getMonney() + monneyTranffer;
            TransfferMoney tranffer = new TransfferMoney(idUser, idBank, tranfferDay, status, monneyTranffer, fee, monney);
            
            userUpdateMonney.setMonney(tranffer.getMonney());
            userDao.update(userUpdateMonney);
            
            return tranffer;
        }
    }

    @Override
    public TransfferMoney tranfferMonney(int id, String json) throws ApiValidateExeption {
        UserEntity userUpdateMonney = userDao.getUserById(id);
        JSONObject userJson = new JSONObject(json);
        if (userUpdateMonney == null) {
            throw new ApiValidateExeption("400", "User Is Not Exist");
        } else if (userJson.isEmpty()) {
            throw new ApiValidateExeption("400", "Please Enter All Field");
        } else {
            int idUser = userUpdateMonney.getIdUser();
            int idBank = userUpdateMonney.getIdBank();
            int status = 2;
            int monneyTranffer = userJson.getInt("monney");
            if (userUpdateMonney.getMonney() < 50) {
                throw new ApiValidateExeption("400", "The amount in your account is less than 50 so you can not tranffer now");
            } else if (userUpdateMonney.getMonney() < monneyTranffer) {
                throw new ApiValidateExeption("400", "The amount in your account is less than your tranffer monney so you can not tranffer now");
            }
            Timestamp tranfferDay = new Timestamp(System.currentTimeMillis());
            int fee;
            if (idBank == 1) {
                fee = 0;
            } else {
                if (monneyTranffer < 5000) {
                    fee = 10;
                } else if (monneyTranffer < 10000) {
                    fee = 20;
                } else {
                    fee = (int) (monneyTranffer * 0.08);
                }
            }
            int monney = userUpdateMonney.getMonney() - monneyTranffer - fee;
            TransfferMoney tranffer = new TransfferMoney(idUser, idBank, tranfferDay, status, monneyTranffer, fee, monney);
            
            userUpdateMonney.setMonney(tranffer.getMonney());
            userDao.update(userUpdateMonney);
            
            return tranffer;
        }
    }

}
