/**
 * 
 */
package com.BaoPT.api.bean;

import java.sql.Timestamp;

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
@Table(name = "transtion")
public class TransEntity {
	
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
	
	@Column(name = "status")
	private Integer status;
	
	@Column(name = "monney_tranffer")
	private Integer monneyTranffer;
	
	@Column(name = "fee")
	private Integer fee;
}
