/**
 * 
 */
package com.BaoPT.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author BaoPT
 *
 */


public class UserInfo {
	
	@JsonProperty("id_user")
	private Integer idUser;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("sdt")
	private String sdt;

	@JsonProperty("password")
	private String password;
	
	@JsonProperty("id_bank")
	private Integer idBank;
	
	/**
	 * @param idUser
	 * @param name
	 * @param sdt
	 * @param password
	 * @param idBank
	 * @param monney
	 * @param bankName
	 */
	public UserInfo(Integer idUser, String name, String sdt, String password, Integer idBank, Integer monney,
			String bankName) {
		super();
		this.idUser = idUser;
		this.name = name;
		this.sdt = sdt;
		this.password = password;
		this.idBank = idBank;
		this.monney = monney;
		this.bankName = bankName;
	}

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	@JsonProperty("monney")
	private Integer monney;
	
	@JsonProperty("bank_name")
	private String bankName;
	
}