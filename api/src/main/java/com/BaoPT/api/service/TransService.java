/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.BaoPT.api.service;

import java.util.List;

import com.BaoPT.api.bean.TransEntity;
import com.BaoPT.api.model.PaginationResponse;
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

    /**
     * create
     * @author: (VNEXT) BaoPT
     * @param json
     * @return List TransEntity
     * @throws ApiValidateExeption
     */
    public List<TransEntity> create(String json) throws ApiValidateExeption;

    /**
     * getAllById
     * @author: (VNEXT) BaoPT
     * @param id
     * @return List TransEntity
     * @throws ApiValidateExeption
     */
    public List<TransEntity> getAllById(int id) throws ApiValidateExeption;

    /**
     * filter
     * @author: (VNEXT) BaoPT
     * @param id
     * @param json
     * @return List TransEntity
     * @throws ApiValidateExeption
     */
    public List<TransEntity> filter(int id, String json) throws ApiValidateExeption;

    /**
     * csvWriterByUserId
     * @author: (VNEXT) BaoPT
     * @param id
     * @return List TransEntity
     * @throws ApiValidateExeption
     */
    public List<TransEntity> csvWriterByUserId(int id) throws ApiValidateExeption;
    
    /**
     * 
     * @author: (VNEXT) BaoPT
     * @param page
     * @param limit
     * @return PaginationResponse
     * @throws ApiValidateExeption
     */
    public PaginationResponse<TransEntity> paginationTransaction(int id, int page, int limit, String fromDate, String toDate) throws ApiValidateExeption;
}
