/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.BaoPT.api.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BaoPT.api.bean.UserEntity;
import com.BaoPT.api.common.CheckToken;
import com.BaoPT.api.common.Define;
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

    @Autowired
    private CheckToken checkToken;

    @Autowired
    private Define define;

    /**
     * @author (VNEXT) BaoPT
     * @param id
     * @param json
     * @param Token (UUID)
     * @return Add Monney to user
     */
    @Override
    public List<TransfferMoney> addMonney(int id, String json, UUID token) throws ApiValidateExeption {
        UserEntity userUpdateMonney = userDao.getUserById(id);
        JSONObject userJson = new JSONObject(json);
        List<TransfferMoney> tranfferList = new ArrayList<TransfferMoney>();
        if (userUpdateMonney == null) {
            throw new ApiValidateExeption("400", "User Is Not Exist");
        } else if (userJson.isEmpty()) {
            throw new ApiValidateExeption("400", "Please Enter All Field");
        } else if (!this.checkToken.checkToken(id, token)) {
            throw new ApiValidateExeption("400", "Invalid Token");
        } else {
            int idUser = userUpdateMonney.getIdUser();
            int idBank = userUpdateMonney.getIdBank();
            int status = 1;
            int monneyTranffer = userJson.getInt("monney");
            Timestamp tranfferDay = new Timestamp(System.currentTimeMillis());
            int fee = 0;
            int monney = userUpdateMonney.getMonney() + monneyTranffer;
            TransfferMoney tranffer = new TransfferMoney(idUser, idBank, tranfferDay, status, monneyTranffer, fee, 0, 0, monney);
            tranfferList.add(tranffer);
            userUpdateMonney.setMonney(tranffer.getMonney());
            userDao.update(userUpdateMonney);

            return tranfferList;
        }
    }

    /**
     * @author (VNEXT) BaoPT
     * @param id
     * @param json
     * @param Token (UUID)
     * @return Tranffer Monney to user
     */
    @Override
    public List<TransfferMoney> tranfferMonney(int id, String json, UUID token) throws ApiValidateExeption {
        UserEntity userUpdateMonney = userDao.getUserById(id);
        JSONObject userJson = new JSONObject(json);
        List<TransfferMoney> tranfferList = new ArrayList<TransfferMoney>();
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
            } else if (!this.checkToken.checkToken(id, token)) {
                throw new ApiValidateExeption("400", "Invalid Token");
            }
            Timestamp tranfferDay = new Timestamp(System.currentTimeMillis());
            int fee;
            if (idBank == 1) {
                fee = 0;
            } else {
                if (monneyTranffer < 5000) {
                    fee = (int) this.define.getFee(idBank, 1);
                } else if (monneyTranffer < 10000) {
                    fee = (int) this.define.getFee(idBank, 2);
                } else {
                    fee = (int) (monneyTranffer * this.define.getFee(idBank, 3));
                }
            }
            int monney = userUpdateMonney.getMonney() - monneyTranffer - fee;
            TransfferMoney tranffer = new TransfferMoney(idUser, idBank, tranfferDay, status, monneyTranffer, fee, 0, 0, monney);

            userUpdateMonney.setMonney(tranffer.getMonney());
            userDao.update(userUpdateMonney);
            tranfferList.add(tranffer);
            return tranfferList;
        }
    }

    /**
     * @author (VNEXT) BaoPT
     * @param id
     * @param json
     * @param Token (UUID)
     * @return Send Money To Another User
     */
    @Override
    public List<TransfferMoney> sendMonney(int id, String json, UUID token) throws ApiValidateExeption {
        UserEntity userSendMoney = userDao.getUserById(id);

        JSONObject userJson = new JSONObject(json);
        List<TransfferMoney> tranfferResponse = new ArrayList<TransfferMoney>();
        TransfferMoney tranffer = null;
        TransfferMoney tranfferTo = null;
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
            } else if (!this.checkToken.checkToken(id, token)) {
                throw new ApiValidateExeption("400", "Invalid Token");
            } else {
                if (userSendMoney.getIdBank() != userTakeMoney.getIdBank()) {
                    fee = (int) (monneyTransfer * this.define.getFee(userSendMoney.getIdBank(), 4));
                } else {
                    fee = 10;
                }
            }

            userSendMoney.setMonney(userSendMoney.getMonney() - monneyTransfer - fee);
            userDao.update(userSendMoney);

            userTakeMoney.setMonney(userTakeMoney.getMonney() + monneyTransfer);
            userDao.update(userTakeMoney);

            tranffer = new TransfferMoney(id, userSendMoney.getIdBank(), tranfferDay, 3, monneyTransfer, fee, idUserTo, userTakeMoney.getIdBank(),
                    userSendMoney.getMonney());

            tranfferTo = new TransfferMoney(idUserTo, userTakeMoney.getIdBank(), tranfferDay, 4, monneyTransfer, 0, id, userSendMoney.getIdBank(),
                    userTakeMoney.getMonney());

            //            tranfferResponse.add(tranffer);
            tranfferResponse.add(0, tranffer);
            tranfferResponse.add(1, tranfferTo);

        }
        return tranfferResponse;
    }

}
