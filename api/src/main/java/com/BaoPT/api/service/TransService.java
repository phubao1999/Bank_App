/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.BaoPT.api.service;

import java.util.List;

import com.BaoPT.api.bean.TransEntity;
import com.BaoPT.api.utils.ApiValidateExeption;

/**
 * [OVERVIEW] TransService.
 *
 * @author: (VNEXT) BaoPT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2020/04/14      (VNEXT) BaoPT       Create new
*/

public interface TransService {

    public List<TransEntity> create(String json) throws ApiValidateExeption;

    public List<TransEntity> getAllById(int id) throws ApiValidateExeption;

    public List<TransEntity> filter(int id, String json) throws ApiValidateExeption;
}
