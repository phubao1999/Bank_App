package com.BaoPT.api.service;

import java.util.List;

import com.BaoPT.api.bean.TransEntity;
import com.BaoPT.api.utils.ApiValidateExeption;

public interface TransService {

	public TransEntity create(String json) throws ApiValidateExeption;
	
	public List<TransEntity> getAllById(int id) throws ApiValidateExeption;
	
	public List<TransEntity> filter(String json) throws ApiValidateExeption;
}
