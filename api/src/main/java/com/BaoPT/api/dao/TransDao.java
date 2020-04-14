package com.BaoPT.api.dao;

import java.util.Date;
import java.util.List;

import com.BaoPT.api.bean.TransEntity;

public interface TransDao {
	
	public void createTrans(TransEntity trans);
	
	public List<TransEntity> getAllById(int id);
	
	public List<TransEntity> filter(int id, Date fromDate, Date toDate);
		
}
