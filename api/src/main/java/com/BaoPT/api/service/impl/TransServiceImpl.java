/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.BaoPT.api.service.impl;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BaoPT.api.bean.TransEntity;
import com.BaoPT.api.common.CheckToken;
import com.BaoPT.api.common.Define;
import com.BaoPT.api.dao.TransDao;
import com.BaoPT.api.service.TransService;
import com.BaoPT.api.utils.ApiValidateExeption;
import com.opencsv.CSVWriter;

/**
 * [OVERVIEW] TransServiceImpl.
 *
 * @author: (VNEXT) BaoPT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2020/04/18      (VNEXT) BaoPT       Create new
*/

@Service
public class TransServiceImpl implements TransService {

    @Autowired
    private TransDao transDao;

    @Autowired
    private CheckToken checkToken;

    @Override
    public List<TransEntity> create(String json) throws ApiValidateExeption {
        JSONObject transJson = new JSONObject(json);
        Timestamp tranfferDay = new Timestamp(System.currentTimeMillis());
        List<TransEntity> transEntityList = new ArrayList<TransEntity>();
        if (transJson.isEmpty()) {
            throw new ApiValidateExeption("400", "Please Enter All Field");
        } else {
            JSONArray transList = transJson.getJSONArray("data");
            for (int i = 0; i < transList.length(); i++) {
                JSONObject obj = transList.getJSONObject(i);
                TransEntity transEntity = new TransEntity();
                transEntity.setIdUser(obj.getInt("id_user"));
                transEntity.setIdBank(obj.getInt("id_bank"));
                transEntity.setStatus(obj.getInt("status"));
                transEntity.setMonneyTranffer(obj.getInt("monney_tranffer"));
                transEntity.setFee(obj.getInt("fee"));
                transEntity.setTranfferDay(tranfferDay);
                transEntity.setIdUserTransfer(obj.getInt("id_user_trans"));
                transEntity.setIdBankTransfer(obj.getInt("id_bank_trans"));
                transEntityList.add(transEntity);
                this.transDao.createTrans(transEntity);
            }

            return transEntityList;
        }
    }

    @Override
    public List<TransEntity> getAllById(int id, UUID token) throws ApiValidateExeption {
        List<TransEntity> transList = (List<TransEntity>) transDao.getAllById(id);
        if (transList.size() == 0) {
            throw new ApiValidateExeption("400", "No Data");
        } else if (!this.checkToken.checkToken(id, token)) {
            throw new ApiValidateExeption("400", "Invalid Token");
        } else {
            return transList;
        }
    }

    @Override
    public List<TransEntity> filter(int id, String json, UUID token) throws ApiValidateExeption {
        JSONObject transJson = new JSONObject(json);
        List<TransEntity> transList = null;
        if (transJson.isEmpty()) {
            throw new ApiValidateExeption("400", "Please Enter All Field");
        } else {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
            Date fromDate = null;
            Date toDate = null;
            try {
                String from = transJson.getString("from");
                String to = transJson.getString("to");
                fromDate = dateFormat.parse(from + " 00:00");
                toDate = dateFormat.parse(to + " 23:59");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            transList = transDao.filter(id, fromDate, toDate);
            if (transList.size() == 0) {
                throw new ApiValidateExeption("400", "There Is No Data");
            } else if (!this.checkToken.checkToken(id, token)) {
                throw new ApiValidateExeption("400", "Invalid Token");
            } else {
                return transList;
            }
        }
    }

    @Override
    public List<TransEntity> csvWriterByUserId(int id, UUID token) throws ApiValidateExeption {

        List<TransEntity> transEntity = null;

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String timeCreate = formatter.format(new Date());
        String csv = "src/output/transactions_" + timeCreate + ".csv";

        transEntity = transDao.getAllById(id);

        if (transEntity.size() == 0) {
            throw new ApiValidateExeption("400", "Transaction Is Not Found");
        } else if (!this.checkToken.checkToken(id, token)) {
            throw new ApiValidateExeption("400", "Invalid Token");
        } else {
            try {
                Writer writer = new FileWriter(csv);
                CSVWriter csvWriter = new CSVWriter(writer, CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                        CSVWriter.DEFAULT_LINE_END);
                csvWriter.writeNext(new String[] { "idBank", "status", "tranfferDay", "monneyTranffer", "fee", "id_User_Transfer" });
                for (TransEntity trans : transEntity) {
                    csvWriter.writeNext(new String[] { trans.getIdBank().toString(), Define.defineStatus(trans.getStatus()), trans.getTranfferDay().toString(),
                            trans.getMonneyTranffer().toString(), trans.getFee().toString(), trans.getIdUserTransfer().toString() });
                }
                csvWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return transEntity;

    }

}
