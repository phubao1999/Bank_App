/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.BaoPT.api.dao;

import java.util.List;

import com.BaoPT.api.bean.BankEntity;

/**
 * [OVERVIEW] BankDao.
 *
 * @author: (VNEXT) BaoPT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2020/04/18      (VNEXT) BaoPT       Create new
*/
public interface BankDao {

    /**
     * getAll
     * @author: (VNEXT) BaoPT
     */
    public List<BankEntity> getAll();

    /**
     * getBank
     * @author: (VNEXT) BaoPT
     * @param id
     * @return BankEntity
     */
    public BankEntity getBank(int id);

}
