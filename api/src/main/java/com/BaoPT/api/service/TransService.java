package com.BaoPT.api.service;

import com.BaoPT.api.bean.TransEntity;
import com.BaoPT.api.utils.ApiValidateExeption;

public interface TransService {

	public TransEntity create(String json) throws ApiValidateExeption;
	
}
