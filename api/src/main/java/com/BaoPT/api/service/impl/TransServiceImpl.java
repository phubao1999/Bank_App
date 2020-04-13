/**
 * 
 */
package com.BaoPT.api.service.impl;

import java.sql.Timestamp;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BaoPT.api.bean.TransEntity;
import com.BaoPT.api.dao.TransDao;
import com.BaoPT.api.service.TransService;
import com.BaoPT.api.utils.ApiValidateExeption;

/**
 * @author BaoPT
 *
 */

@Service
public class TransServiceImpl implements TransService {

	@Autowired
	private TransDao transDao;
	
	@Override
	public TransEntity create(String json) throws ApiValidateExeption {
		JSONObject transJson = new JSONObject(json);
		if (transJson.isEmpty()) {
			throw new ApiValidateExeption("400", "Please Enter All Field");
		} else {
	        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			TransEntity transEntity = new TransEntity();
			transEntity.setIdUser(transJson.getInt("id_user"));
			transEntity.setIdBank(transJson.getInt("id_bank"));
			transEntity.setStatus(transJson.getInt("status"));
			transEntity.setMonneyTranffer(transJson.getInt("monney_tranffer"));;
			transEntity.setFee(transJson.getInt("fee"));
			transEntity.setTranfferDay(timestamp);
			transDao.createTrans(transEntity);
			return transEntity;
		}
	}

}
