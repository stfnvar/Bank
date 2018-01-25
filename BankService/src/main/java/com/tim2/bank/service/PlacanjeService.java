package com.tim2.bank.service;

import com.tim2.bank.model.RezultatTransakcije;
import com.tim2.bank.model.Transakcija;
import com.tim2.bank.model.Uplata;

public interface PlacanjeService {
	public String acquirerProveriZahtev(Uplata uplata);
	public RezultatTransakcije issuerProveriZahtev(Transakcija transakcija);
	public Uplata proveriUrl(String uplataLink, Long uplataId);
	public Transakcija setAcquirerSwiftCode(Transakcija transakcija);
}
