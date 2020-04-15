/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.BaoPT.api.dao;

import java.util.Date;
import java.util.List;

import com.BaoPT.api.bean.TransEntity;

/**
 * [OVERVIEW] TransDao.
 *
 * @author: (VNEXT) BaoPT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2020/04/14      (VNEXT) BaoPT       Create new
*/

public interface TransDao {

    public void createTrans(TransEntity trans);

    public List<TransEntity> getAllById(int id);

    public List<TransEntity> filter(int id, Date fromDate, Date toDate);

}
