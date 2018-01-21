package com.tim2.bank.serviceimpl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tim2.bank.model.Klijent;
import com.tim2.bank.model.Racun;
import com.tim2.bank.model.RezultatTransakcije;
import com.tim2.bank.model.Transakcija;
import com.tim2.bank.model.Uplata;
import com.tim2.bank.repository.KlijentRepository;
import com.tim2.bank.repository.RacunRepository;
import com.tim2.bank.repository.TransakcijaRepository;
import com.tim2.bank.service.PlacanjeService;

@Service
public class PlacanjeServiceImpl implements PlacanjeService {

	@Autowired
	private KlijentRepository klijentRepository;

	@Autowired
	private RacunRepository racunRepository;

	@Autowired
	private TransakcijaRepository transakcijaRepository;
	
	@Override
	public String acquirerProveriZahtev(Uplata uplata) {

		Klijent klijent = klijentRepository.findKlijentByMerchantId(uplata.getTrgovacId());
		if (klijent.getMerchantPassword().equals(uplata.getLozinkaTrgovca())) {
			// gadjaj link na frontu za unos podataka
			return "localhost:2100/plati-uslugu/AJDIGENERISATI";
		} else {
			return uplata.getErrorUrl();
		}
	}

	@Override
	public RezultatTransakcije issuerProveriZahtev(Transakcija transakcija) {
		Transakcija tran = transakcijaRepository.save(transakcija);
		Racun racun = racunRepository.findRacunByBrojRacuna(transakcija.getPan());
		String issuerTimestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		RezultatTransakcije rz = new RezultatTransakcije(transakcija.getPan(), transakcija.getSigurnosniKod(),
				transakcija.getNazivVlasnikaKartice(), transakcija.getDatumVazenja(), transakcija.getIznos(),
				transakcija.getAcquirerOrderId(), transakcija.getAcquirerTimestamp(), tran.getId().toString(), issuerTimestamp, false);
		if (Double.parseDouble(racun.getStanjeRacuna()) - Double.parseDouble(transakcija.getIznos()) > 0) {
			rz.setRezultat(true);
			racun.setStanjeRacuna( Double.toString(Double.parseDouble(racun.getStanjeRacuna()) - Double.parseDouble(transakcija.getIznos())) );
			racunRepository.save(racun);
		}

		return rz;
	}

}
