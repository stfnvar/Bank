package com.tim2.bank.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Osiguranje implements Serializable {

	private static final long serialVersionUID = -3614942056393380322L;

	private Long id;
	
	private Date datumSklapanja;
	
	private double iznos;
	
	private List<Uplata> uplate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDatumSklapanja() {
		return datumSklapanja;
	}

	public void setDatumSklapanja(Date datumSklapanja) {
		this.datumSklapanja = datumSklapanja;
	}

	public double getIznos() {
		return iznos;
	}

	public void setIznos(double iznos) {
		this.iznos = iznos;
	}

	public List<Uplata> getUplate() {
		return uplate;
	}

	public void setUplate(List<Uplata> uplate) {
		this.uplate = uplate;
	}
	
}
