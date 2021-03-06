package com.tim2.bank.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Uplata {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	private String trgovacId;
	
	@Column
	private String lozinkaTrgovca;
	
	@Column
	private double iznos;
	
	@Column
	private Date datumUplate;
	
	@Column
	private String errorUrl;
	
	@Column
	private Long osiguranjeId;
	
	@Column
	private StatusUplate status;
	
	@Enumerated(EnumType.STRING)
	private TipUplate tipUplate;
	
	@Column
	private String uplataLink;
	
	@Column
	private boolean aktivanLink;

	@Column
	private Long uplataIdDatabase;
	
	public boolean isAktivanLink() {
		return aktivanLink;
	}
	public void setAktivanLink(boolean aktivanLink) {
		this.aktivanLink = aktivanLink;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTrgovacId() {
		return trgovacId;
	}
	public void setTrgovacId(String trgovacId) {
		this.trgovacId = trgovacId;
	}
	public String getLozinkaTrgovca() {
		return lozinkaTrgovca;
	}
	public void setLozinkaTrgovca(String lozinkaTrgovca) {
		this.lozinkaTrgovca = lozinkaTrgovca;
	}
	public double getIznos() {
		return iznos;
	}
	public void setIznos(double iznos) {
		this.iznos = iznos;
	}
	public Date getDatumUplate() {
		return datumUplate;
	}
	public void setDatumUplate(Date datumUplate) {
		this.datumUplate = datumUplate;
	}
	public String getErrorUrl() {
		return errorUrl;
	}
	public void setErrorUrl(String errorUrl) {
		this.errorUrl = errorUrl;
	}

	public Long getOsiguranjeId() {
		return osiguranjeId;
	}
	public void setOsiguranjeId(Long osiguranjeId) {
		this.osiguranjeId = osiguranjeId;
	}
	public StatusUplate getStatus() {
		return status;
	}
	public void setStatus(StatusUplate status) {
		this.status = status;
	}
	public TipUplate getTipUplate() {
		return tipUplate;
	}
	public void setTipUplate(TipUplate tipUplate) {
		this.tipUplate = tipUplate;
	}
	public String getUplataLink() {
		return uplataLink;
	}
	public void setUplataLink(String uplataLink) {
		this.uplataLink = uplataLink;
	}
	public Long getUplataIdDatabase() {
		return uplataIdDatabase;
	}
	public void setUplataIdDatabase(Long uplataIdDatabase) {
		this.uplataIdDatabase = uplataIdDatabase;
	}
	
}
