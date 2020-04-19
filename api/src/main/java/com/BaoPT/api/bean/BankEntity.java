/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.BaoPT.api.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * [OVERVIEW] BankEntity.
 *
 * @author: (VNEXT) BaoPT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2020/04/14      (VNEXT) BaoPT       Create new
*/

@Entity
@Table(name = "bank")
public class BankEntity {

    @Id
    @Column(name = "id_bank")
    private Integer idBank;

    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "fee_first")
    private double feeFirst;

    @Column(name = "fee_second")
    private double feeSecond;

    @Column(name = "fee_third")
    private double feeThird;

    @Column(name = "fee_transfer")
    private double feeTransfer;

    public double getFeeTransfer() {
        return feeTransfer;
    }

    public void setFeeTransfer(double feeTransfer) {
        this.feeTransfer = feeTransfer;
    }

    public double getFeeFirst() {
        return feeFirst;
    }

    public void setFeeFirst(double feeFirst) {
        this.feeFirst = feeFirst;
    }

    public double getFeeSecond() {
        return feeSecond;
    }

    public void setFeeSecond(double feeSecond) {
        this.feeSecond = feeSecond;
    }

    public double getFeeThird() {
        return feeThird;
    }

    public void setFeeThird(double feeThird) {
        this.feeThird = feeThird;
    }

    public Integer getIdBank() {
        return idBank;
    }

    public void setIdBank(Integer idBank) {
        this.idBank = idBank;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}
