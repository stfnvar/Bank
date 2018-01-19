package com.tim2.bank.model;

import javax.validation.constraints.Size;

public class PlacanjeLink {

	@Size(max = 30)
	private String placanjeUrl;
	
	@Size (max = 10)
	private Long placanjeId;

	public String getPlacanjeUrl() {
		return placanjeUrl;
	}

	public void setPlacanjeUrl(String placanjeUrl) {
		this.placanjeUrl = placanjeUrl;
	}

	public Long getPlacanjeId() {
		return placanjeId;
	}

	public void setPlacanjeId(Long placanjeId) {
		this.placanjeId = placanjeId;
	}
	
}
