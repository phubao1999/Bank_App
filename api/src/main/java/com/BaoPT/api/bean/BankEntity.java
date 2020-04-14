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
