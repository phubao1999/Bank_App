/**
 * 
 */
package com.BaoPT.api.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author BaoPT
 *
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
