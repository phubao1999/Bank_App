/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.BaoPT.api.model;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * [OVERVIEW] TransfferMoney.
 *
 * @author: (VNEXT) BaoPT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2020/04/15      (VNEXT) BaoPT       Create new
*/
public class TransfferMoney {

    @JsonProperty("id_user")
    private Integer idUser;

    @JsonProperty("id_bank")
    private Integer idBank;

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getIdBank() {
        return idBank;
    }

    public void setIdBank(Integer idBank) {
        this.idBank = idBank;
    }

    public Timestamp getTranfferDay() {
        return tranfferDay;
    }

    public void setTranfferDay(Timestamp tranfferDay) {
        this.tranfferDay = tranfferDay;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getMonneyTranffer() {
        return monneyTranffer;
    }

    public void setMonneyTranffer(Integer monneyTranffer) {
        this.monneyTranffer = monneyTranffer;
    }

    public Integer getFee() {
        return fee;
    }

    public void setFee(Integer fee) {
        this.fee = fee;
    }

    public Integer getMonney() {
        return monney;
    }

    public void setMonney(Integer monney) {
        this.monney = monney;
    }

    @JsonProperty("tranffer_day")
    private Timestamp tranfferDay;

    @JsonProperty("status")
    private Integer status;

    public Integer getIdUserTrans() {
        return idUserTrans;
    }

    public void setIdUserTrans(Integer idUserTrans) {
        this.idUserTrans = idUserTrans;
    }

    public Integer getIdBankTrans() {
        return idBankTrans;
    }

    public void setIdBankTrans(Integer idBankTrans) {
        this.idBankTrans = idBankTrans;
    }

    /**
     * @param idUser
     * @param idBank
     * @param tranfferDay
     * @param status
     * @param monneyTranffer
     * @param fee
     * @param idUserTrans
     * @param idBankTrans
     * @param monney
     */
    public TransfferMoney(Integer idUser, Integer idBank, Timestamp tranfferDay, Integer status, Integer monneyTranffer, Integer fee, Integer idUserTrans,
            Integer idBankTrans, Integer monney) {
        super();
        this.idUser = idUser;
        this.idBank = idBank;
        this.tranfferDay = tranfferDay;
        this.status = status;
        this.monneyTranffer = monneyTranffer;
        this.fee = fee;
        this.idUserTrans = idUserTrans;
        this.idBankTrans = idBankTrans;
        this.monney = monney;
    }

    @JsonProperty("monney_tranffer")
    private Integer monneyTranffer;

    @JsonProperty("fee")
    private Integer fee;

    @JsonProperty("monney")
    private Integer monney;

    @JsonProperty("id_user_trans")
    private Integer idUserTrans;

    @JsonProperty("id_bank_trans")
    private Integer idBankTrans;

}
