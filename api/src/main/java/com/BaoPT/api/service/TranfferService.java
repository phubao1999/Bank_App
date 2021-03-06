/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.BaoPT.api.service;

import java.util.List;

import com.BaoPT.api.model.TransfferMoney;
import com.BaoPT.api.utils.ApiValidateExeption;

/**
 * [OVERVIEW] TranfferService.
 *
 * @author: (VNEXT) BaoPT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2020/04/15      (VNEXT) BaoPT       Create new
*/

public interface TranfferService {

    /**
     * addMonney
     * @author: (VNEXT) BaoPT
     * @param id
     * @param json
     * @return List TransfferMoney
     * @throws ApiValidateExeption
     */
    public List<TransfferMoney> addMonney(int id, String json) throws ApiValidateExeption;

    /**
     * tranfferMonney
     * @author: (VNEXT) BaoPT
     * @param id
     * @param json
     * @return List TransfferMonney
     * @throws ApiValidateExeption
     */
    public List<TransfferMoney> tranfferMonney(int id, String json) throws ApiValidateExeption;

    /**
     * sendMonney
     * @author: (VNEXT) BaoPT
     * @param id
     * @param json
     * @return List TransfferMoney
     * @throws ApiValidateExeption
     */
    public List<TransfferMoney> sendMonney(int id, String json) throws ApiValidateExeption;

}
