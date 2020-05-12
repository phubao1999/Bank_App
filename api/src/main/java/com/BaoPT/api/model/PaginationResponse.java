/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.BaoPT.api.model;

import java.io.Serializable;
import java.util.List;

/**
 * [OVERVIEW] PaginationResponse.
 *
 * @author: (VNEXT) BaoPT
 * @version: 1.0
 * @param <E>
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2020/05/12      (VNEXT) BaoPT       Create new
*/
public class PaginationResponse<E> implements Serializable {

    private static final long serialVersionUID = 1407088116543808907L;
    private PageInfo pageInfo;
    private List<E> result;

    /**
     * @param pageInfo
     * @param result
     */
    public PaginationResponse(PageInfo pageInfo, List<E> result) {
        this.pageInfo = pageInfo;
        this.result = result;
    }

    /**
     * @return the pageInfo
     */
    public PageInfo getPageInfo() {
        return pageInfo;
    }

    /**
     * @param pageInfo the pageInfo to set
     */
    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    /**
     * @return the result
     */
    public List<E> getResult() {
        return result;
    }

    /**
     * @param result the result to set
     */
    public void setResult(List<E> result) {
        this.result = result;
    }

}
