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
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BaoPT.api.bean.TransEntity;
import com.BaoPT.api.common.Define;
import com.BaoPT.api.dao.TransDao;
import com.BaoPT.api.model.PageInfo;
import com.BaoPT.api.model.PaginationResponse;
import com.BaoPT.api.service.TransService;
import com.BaoPT.api.utils.ApiValidateExeption;
import com.BaoPT.api.utils.Constant;
import com.BaoPT.api.utils.ReadProperties;
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

    private static final Log log = LogFactory.getLog(UserServiceImpl.class);

    @Autowired
    private TransDao transDao;

    @Autowired
    private Define define;

    /**
     * @author (VNEXT) BaoPT
     * @param json
     * @return Create New Transaction
     */
    @Override
    public List<TransEntity> create(String json) throws ApiValidateExeption {
        log.debug("### Create Transaction Start ###");
        JSONObject transJson = new JSONObject(json);
        Timestamp tranfferDay = new Timestamp(System.currentTimeMillis());
        List<TransEntity> transEntityList = new ArrayList<TransEntity>();
        if (transJson.isEmpty()) {
            throw new ApiValidateExeption(Constant.BAD_REQUEST, "Please Enter All Field");
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
            log.debug("### Create Transaction End ###");
            return transEntityList;
        }
    }

    /**
     * @author (VNEXT) BaoPT
     * @param id
     * @param Token (UUID)
     * @return Get All Transtion By Id
     */
    @Override
    public List<TransEntity> getAllById(int id) throws ApiValidateExeption {
        log.debug("### Get Transaction List Start ###");
        List<TransEntity> transList = (List<TransEntity>) transDao.getAllById(id);
        if (transList.size() == 0) {
            throw new ApiValidateExeption(Constant.BAD_REQUEST, "No Data");
        } else {
            log.debug("### Get Transaction List End ###");
            return transList;
        }
    }

    /**
     * @author (VNEXT) BaoPT
     * @param id
     * @param json
     * @param Token (UUID)
     * @return Filtel All Transaction By date and Id
     */
    @Override
    public List<TransEntity> filter(int id, String json) throws ApiValidateExeption {
        log.debug("### Get Transaction List By Id and date Start ###");
        JSONObject transJson = new JSONObject(json);
        List<TransEntity> transList = null;
        if (transJson.isEmpty()) {
            throw new ApiValidateExeption(Constant.BAD_REQUEST, "Please Enter All Field");
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
            if (fromDate.compareTo(toDate) > 0) {
                throw new ApiValidateExeption(Constant.BAD_REQUEST, "Date From Must Be Before Date To");
            }
            if (transList.size() == 0) {
                throw new ApiValidateExeption(Constant.BAD_REQUEST, "There Is No Data");
            }
            log.debug("### Get Transaction List By Id and date End ###");
            return transList;
        }
    }

    /**
     * @author (VNEXT) BaoPT
     * @param id
     * @param Token (UUID)
     * @return Export To File .csv in src/output
     */
    @Override
    public List<TransEntity> csvWriterByUserId(int id) throws ApiValidateExeption {
        log.debug("### Export Transaction List By Id Start ###");

        List<TransEntity> transEntity = null;

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String timeCreate = formatter.format(new Date());
        Properties pro = ReadProperties.readProperties("project.properties");
        String path = pro.getProperty("pathCsv");
        String csv = path + timeCreate + ".csv";

        transEntity = transDao.getAllById(id);

        if (transEntity.size() == 0) {
            throw new ApiValidateExeption(Constant.BAD_REQUEST, "Transaction Is Not Found");
        } else {
            try {
                Writer writer = new FileWriter(csv);
                CSVWriter csvWriter = new CSVWriter(writer, CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                        CSVWriter.DEFAULT_LINE_END);
                csvWriter.writeNext(new String[] { "idBank", "status", "tranfferDay", "monneyTranffer", "fee", "id_User_Transfer" });
                for (TransEntity trans : transEntity) {
                    csvWriter.writeNext(
                            new String[] { this.define.defineBank(trans.getIdBank()), Define.defineStatus(trans.getStatus()), trans.getTranfferDay().toString(),
                                    trans.getMonneyTranffer().toString(), trans.getFee().toString(), trans.getIdUserTransfer().toString() });
                }
                csvWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        log.debug("### Export Transaction List By Id End ###");
        return transEntity;

    }

    /**
     * @author (VNEXT) BaoPT
     * @return PaginationResponse
     */
    @Override
    public PaginationResponse<TransEntity> paginationTransaction(int id, int page, int limit, String fromDate, String toDate) throws ApiValidateExeption {
        List<TransEntity> listTrans = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Date from = null;
        Date to = null;
        if (fromDate != null && toDate != null) {
            try {
                from = formatter.parse(fromDate + " 00:00");
                to = formatter.parse(toDate + " 23:59");
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        int totalRecords = Integer.parseInt(this.transDao.countRecord(id, from, to));
        int index = (page - 1) * limit;
        int totalPages = (int) Math.ceil((float) totalRecords / limit);
        boolean isNoRecords = false;
        if (page > totalPages) {
            throw new ApiValidateExeption(Constant.BAD_REQUEST, "Page Request Must Be Not More Than Total Pages");
        } else if (totalRecords == 0) {
            isNoRecords = true;
        } else if (page < 1 || limit < 1) {
            throw new ApiValidateExeption(Constant.BAD_REQUEST, "Page or limit request must more than 0");
        }
        try {
            listTrans = this.transDao.paginationTranstion(id, index, limit, from, to);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (listTrans.size() == 0) {
            throw new ApiValidateExeption(Constant.BAD_REQUEST, "There Is Not Data");
        }
        PageInfo pageInfo = new PageInfo(totalPages, page, totalRecords, limit, isNoRecords);
        PaginationResponse<TransEntity> response = new PaginationResponse<TransEntity>(pageInfo, listTrans);
        return response;
    }

}
