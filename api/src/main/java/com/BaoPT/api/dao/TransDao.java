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

    /**
     * createTrans
     * @author: (VNEXT) BaoPT
     * @param trans
     */
    public void createTrans(TransEntity trans);

    /**
     * getAllById
     * @author: (VNEXT) BaoPT
     * @param id
     * @return List TransEntity
     */
    public List<TransEntity> getAllById(int id);

    /**
     * filter
     * @author: (VNEXT) BaoPT
     * @param id
     * @param fromDate
     * @param toDate
     * @return List TransEntity
     */
    public List<TransEntity> filter(int id, Date fromDate, Date toDate);
    
    /**
     * 
     * @author: (VNEXT) BaoPT
     * @param index
     * @param limit
     * @return Pagination Of List Transaction
     */
    public List<TransEntity> paginationTranstion(int id, int index, int limit, Date from, Date to);

    /**
     * 
     * @author: (VNEXT) BaoPT
     * @return Get TotalCount
     */
    public String countRecord(int id, Date from, Date to);

}
