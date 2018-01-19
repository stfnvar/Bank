package com.tim2.bank.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tim2.bank.model.Klijent;
import com.tim2.bank.model.Racun;
import com.tim2.bank.model.RezultatTransakcije;
import com.tim2.bank.model.Transakcija;
import com.tim2.bank.model.Uplata;
import com.tim2.bank.repository.KlijentRepository;
import com.tim2.bank.repository.RacunRepository;
import com.tim2.bank.service.PlacanjeService;

@Service
public class PlacanjeServiceImpl implements PlacanjeService {

	@Autowired
	private KlijentRepository klijentRepository;

	@Autowired
	private RacunRepository racunRepository;

	@Override
	public String acquirerProveriZahtev(Uplata uplata) {

		Klijent klijent = klijentRepository.findKlijentByMerchantId(uplata.getTrgovacId());
		if (klijent.getMerchantPassword().equals(uplata.getLozinkaTrgovca())) {
			// gadjaj link na frontu za unos podataka
			return "localhost:8300/placanje/unosPodataka/1";
		} else {
			return uplata.getErrorUrl();
		}
	}

	@Override
	public RezultatTransakcije issuerProveriZahtev(Transakcija transakcija) {
		Racun racun = racunRepository.findRacunByBrojRacuna(transakcija.getPan());
		RezultatTransakcije rz = new RezultatTransakcije(transakcija.getPan(), transakcija.getSigurnosniKod(),
				transakcija.getNazivVlasnikaKartice(), transakcija.getDatumVazenja(), transakcija.getIznos(),
				transakcija.getAcquirerOrderId(), transakcija.getAcquirerTimestamp(), "123", "STAGOD", false);
		if (Double.parseDouble(racun.getStanjeRacuna()) - Double.parseDouble(transakcija.getIznos()) > 0) {
			rz.setRezultat(true);
		}

		return rz;
	}

}
