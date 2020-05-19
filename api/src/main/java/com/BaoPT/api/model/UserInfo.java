/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.BaoPT.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * [OVERVIEW] UserInfo.
 *
 * @author: (VNEXT) BaoPT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2020/04/14      (VNEXT) BaoPT       Create new
*/

public class UserInfo {

    @JsonProperty("id_user")
    private Integer idUser;

    @JsonProperty("name")
    private String name;

    @JsonProperty("sdt")
    private String sdt;

    @JsonProperty("id_bank")
    private Integer idBank;

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    /**
     * @param idUser
     * @param name
     * @param sdt
     * @param idBank
     * @param monney
     * @param bankName
     * @param dob
     * @param statusUser
     * @param email
     */
    public UserInfo(Integer idUser, String name, String sdt, Integer idBank, Integer monney, String bankName, String dob, Integer statusUser, String email) {
        super();
        this.idUser = idUser;
        this.name = name;
        this.sdt = sdt;
        this.idBank = idBank;
        this.monney = monney;
        this.bankName = bankName;
        this.dob = dob;
        this.statusUser = statusUser;
        this.email = email;
    }

    public Integer getIdBank() {
        return idBank;
    }

    public void setIdBank(Integer idBank) {
        this.idBank = idBank;
    }

    public Integer getMonney() {
        return monney;
    }

    public void setMonney(Integer monney) {
        this.monney = monney;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Integer getStatusUser() {
        return statusUser;
    }

    public void setStatusUser(Integer statusUser) {
        this.statusUser = statusUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("monney")
    private Integer monney;

    @JsonProperty("bank_name")
    private String bankName;

    @JsonProperty("day_of_birth")
    private String dob;
    
    @JsonProperty("status_user")
    private Integer statusUser;
    
    @JsonProperty("email")
    private String email;
}
