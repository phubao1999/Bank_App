/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.BaoPT.api.service.impl;

import java.sql.Timestamp;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BaoPT.api.bean.UserEntity;
import com.BaoPT.api.dao.UserDao;
import com.BaoPT.api.model.TransfferMoney;
import com.BaoPT.api.service.TranfferService;
import com.BaoPT.api.utils.ApiValidateExeption;

/**
 * [OVERVIEW] TranfferServiceImpl.
 *
 * @author: (VNEXT) BaoPT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2020/04/15      (VNEXT) BaoPT       Create new
*/

@Service
public class TranfferServiceImpl implements TranfferService {

    @Autowired
    private UserDao userDao;

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
            TransfferMoney tranffer = new TransfferMoney(idUser, idBank, tranfferDay, status, monneyTranffer, fee, 0, 0, monney);

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
            TransfferMoney tranffer = new TransfferMoney(idUser, idBank, tranfferDay, status, monneyTranffer, fee, 0, 0, monney);

            userUpdateMonney.setMonney(tranffer.getMonney());
            userDao.update(userUpdateMonney);

            return tranffer;
        }
    }

    @Override
    public TransfferMoney sendMonney(int id, String json) throws ApiValidateExeption {
        UserEntity userSendMoney = userDao.getUserById(id);
        JSONObject userJson = new JSONObject(json);
        TransfferMoney transfer = null;
        if (userSendMoney == null) {
            throw new ApiValidateExeption("400", "User Is Not Exist");
        } else if (userJson.isEmpty()) {
            throw new ApiValidateExeption("400", "Please Enter All Field");
        } else {
            int idUserTo = userJson.getInt("id_user_to");
            int monneyTransfer = userJson.getInt("money_transfer");
            int fee = 0;
            Timestamp tranfferDay = new Timestamp(System.currentTimeMillis());
            UserEntity userTakeMoney = userDao.getUserById(idUserTo);

            if (userTakeMoney == null) {
                throw new ApiValidateExeption("400", "User Is Not Exist");
            } else if (userSendMoney.getMonney() < 50) {
                throw new ApiValidateExeption("400", "Can not tranffer Because Your Money Less than 50");
            } else if (userSendMoney.getMonney() < monneyTransfer) {
                throw new ApiValidateExeption("400", "Can not tranffer Because Your Money Less than money that you want to transfer");
            } else {
                if (userSendMoney.getIdBank() != userTakeMoney.getIdBank()) {
                    fee = (int) (monneyTransfer * 0.8);
                } else {
                    fee = 10;
                }
            }

            userSendMoney.setMonney(userSendMoney.getMonney() - monneyTransfer - fee);
            userDao.update(userSendMoney);

            userTakeMoney.setMonney(userTakeMoney.getMonney() + monneyTransfer);
            userDao.update(userTakeMoney);

            transfer = new TransfferMoney(id, userSendMoney.getIdBank(), tranfferDay, 3, monneyTransfer, fee, idUserTo,
                    userTakeMoney.getIdBank(), userSendMoney.getMonney());

        }
        return transfer;
    }

}
