package com.tim2.bank.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "RezultatTransakcije")
public class RezultatTransakcije extends Transakcija {
	
	private static final long serialVersionUID = 8839516770841134432L;
	
	@Column
	private String issuerOrderId;
	@Column
	private String issuerTimestamp;
	@Column
	private boolean rezultat;
	
	public RezultatTransakcije(){
		
	}
	
	public RezultatTransakcije(String pan, String sigurnosniKod, String nazivVlasnikaKartice, Date datumVazenja,
			String iznos, String acquirerOrderId, String acquirerTimestamp, String acquirerSwiftCode,String issuerOrderId, String issuerTimestamp, boolean rezultat, Long uplataId) {
		super(pan, sigurnosniKod, nazivVlasnikaKartice, datumVazenja, iznos, acquirerOrderId, acquirerTimestamp, acquirerSwiftCode, uplataId);
		this.issuerOrderId = issuerOrderId;
		this.issuerTimestamp = issuerOrderId;
		this.rezultat = rezultat;
	}

	public String getIssuerOrderId() {
		return issuerOrderId;
	}
	public void setIssuerOrderId(String issuerOrderId) {
		this.issuerOrderId = issuerOrderId;
	}
	public String getIssuerTimestamp() {
		return issuerTimestamp;
	}
	public void setIssuerTimestamp(String issuerTimestamp) {
		this.issuerTimestamp = issuerTimestamp;
	}
	public boolean isRezultat() {
		return rezultat;
	}
	public void setRezultat(boolean rezultat) {
		this.rezultat = rezultat;
	}

	@Override
	public String toString() {
		return "RezultatTransakcije [issuerOrderId=" + issuerOrderId + ", issuerTimestamp=" + issuerTimestamp
				+ ", rezultat=" + rezultat + ", pan=" + pan + ", sigurnosniKod=" + sigurnosniKod
				+ ", nazivVlasnikaKartice=" + nazivVlasnikaKartice + ", datumVazenja=" + datumVazenja + ", iznos="
				+ iznos + ", acquirerOrderId=" + acquirerOrderId + ", acquirerTimestamp=" + acquirerTimestamp
				+ ", acquirerSwiftCode=" + acquirerSwiftCode + ", uplataId=" + uplataId + "]";
	}
	
}
