/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.BaoPT.api.bean;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * [OVERVIEW] TransEntity.
 *
 * @author: (VNEXT) BaoPT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2020/04/14      (VNEXT) BaoPT       Create new
*/

@Entity
@Table(name = "transtion")
public class TransEntity {

    @Override
    public String toString() {
        return "TransEntity [id=" + id + ", idUser=" + idUser + ", idBank=" + idBank + ", tranfferDay=" + tranfferDay + ", status=" + status
                + ", monneyTranffer=" + monneyTranffer + ", fee=" + fee + ", idUserTransfer=" + idUserTransfer + ", idBankTransfer=" + idBankTransfer + "]";
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    @Column(name = "id_user")
    private Integer idUser;

    @Column(name = "id_bank")
    private Integer idBank;

    @Column(name = "tranffer_day")
    private Timestamp tranfferDay;

    public Integer getIdUserTransfer() {
        return idUserTransfer;
    }

    public void setIdUserTransfer(Integer idUserTransfer) {
        this.idUserTransfer = idUserTransfer;
    }

    public Integer getIdBankTransfer() {
        return idBankTransfer;
    }

    public void setIdBankTransfer(Integer idBankTransfer) {
        this.idBankTransfer = idBankTransfer;
    }

    @Column(name = "status")
    private Integer status;

    @Column(name = "monney_tranffer")
    private Integer monneyTranffer;

    @Column(name = "fee")
    private Integer fee;

    @Column(name = "id_user_transfer")
    private Integer idUserTransfer;

    @Column(name = "id_bank_transfer")
    private Integer idBankTransfer;
}
