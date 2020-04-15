/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.BaoPT.api.service;

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

    public TransfferMoney addMonney(int id, String json) throws ApiValidateExeption;

    public TransfferMoney tranfferMonney(int id, String json) throws ApiValidateExeption;

    public TransfferMoney sendMonney(int id, String json) throws ApiValidateExeption;

}
