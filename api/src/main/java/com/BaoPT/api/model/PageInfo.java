/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.BaoPT.api.model;

/**
 * [OVERVIEW] PageInfo.
 *
 * @author: (VNEXT) BaoPT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2020/05/12      (VNEXT) BaoPT       Create new
*/
public class PageInfo {

    private int totalPages;
    private int currentPage;
    private int totalRecords;
    private int limit;
    private boolean noRecord;

    /**
     * @param totalPages
     * @param currentPage
     * @param totalRecords
     * @param limit
     * @param noRecord
     */
    public PageInfo(int totalPages, int currentPage, int totalRecords, int limit, boolean noRecord) {
        this.totalPages = totalPages;
        this.currentPage = currentPage;
        this.totalRecords = totalRecords;
        this.limit = limit;
        this.noRecord = noRecord;
    }

    /**
     * @return the totalPages
     */
    public int getTotalPages() {
        return totalPages;
    }

    /**
     * @param totalPages the totalPages to set
     */
    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    /**
     * @return the currentPage
     */
    public int getCurrentPage() {
        return currentPage;
    }

    /**
     * @param currentPage the currentPage to set
     */
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * @return the totalRecords
     */
    public int getTotalRecords() {
        return totalRecords;
    }

    /**
     * @param totalRecords the totalRecords to set
     */
    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    /**
     * @return the limit
     */
    public int getLimit() {
        return limit;
    }

    /**
     * @param limit the limit to set
     */
    public void setLimit(int limit) {
        this.limit = limit;
    }

    /**
     * @return the noRecord
     */
    public boolean isNoRecord() {
        return noRecord;
    }

    /**
     * @param noRecord the noRecord to set
     */
    public void setNoRecord(boolean noRecord) {
        this.noRecord = noRecord;
    }

}
