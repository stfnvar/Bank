package com.tim2.bank.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DatabaseUri {

	@Value("${spring.data.webApp}")
	private String webAppUri;
	
	@Value("${spring.data.pcc}")
	private String pccUri;

	public String getWebAppUri() {
		return webAppUri;
	}

	public void setWebAppUri(String webAppUri) {
		this.webAppUri = webAppUri;
	}

	public String getPccUri() {
		return pccUri;
	}

	public void setPccUri(String pccUri) {
		this.pccUri = pccUri;
	}	
	
}
