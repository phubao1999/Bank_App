/**
 * 
 */
package com.BaoPT.api.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author BaoPT
 *
 */

@Entity
@Table(name = "user_bank")
public class UserBankEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "id_user")
	private Integer idUser;
	
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

	@Column(name = "id_bank")
	private Integer idBank;
}
