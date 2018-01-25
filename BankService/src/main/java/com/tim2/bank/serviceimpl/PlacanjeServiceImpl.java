package com.tim2.bank.serviceimpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

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
import com.tim2.bank.repository.UplataRepository;
import com.tim2.bank.service.PlacanjeService;

@Service
public class PlacanjeServiceImpl implements PlacanjeService {

	@Autowired
	private KlijentRepository klijentRepository;

	@Autowired
	private RacunRepository racunRepository;

	@Autowired
	private TransakcijaRepository transakcijaRepository;
	
	@Autowired
	private UplataRepository uplataRepository;
	
	@Override
	public String acquirerProveriZahtev(Uplata uplata) {
		Klijent klijent = klijentRepository.findKlijentByMerchantId(uplata.getTrgovacId());
		if (klijent.getMerchantPassword().equals(uplata.getLozinkaTrgovca())) {
			// gadjaj link na frontu za unos podataka
			String url = generatePaymentUrl(uplata.getId());
			
			uplata.setUplataLink(url);
			uplata.setUplataIdDatabase(uplata.getId());
			uplata.setId(null);
			
			uplataRepository.save(uplata);
			return url;
		} else {
			return uplata.getErrorUrl();
		}
	}
	
	private String generatePaymentUrl(Long id){		
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = upper.toLowerCase();
        String numbers = "1234567890";
        
        String characters = upper + lower + numbers;
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        int length = random.nextInt(100);
        
        while(builder.length() < length){
        	int index = (int) (random.nextFloat() * characters.length());
        	builder.append(characters.charAt(index));
        }
        
        String ret = "http://localhost:2100/plati-uslugu/" + builder.toString() + "/" + id;
		
		return ret;
	}
	
	@Override
	public Uplata proveriUrl(String uplataLink, Long uplataId) {
		Uplata uplata = uplataRepository.getUplataByUplataLinkContainingAndUplataIdDatabase(uplataLink, uplataId);
		if(uplata != null) {
			System.out.println("UPLATAAA: " + uplata.getIznos());
			return uplata;
			//return uplata.getIznos();
		}
		return null;
	}

	@Override
	public RezultatTransakcije issuerProveriZahtev(Transakcija transakcija) {
		Transakcija tran = transakcijaRepository.save(transakcija);
		Racun racun = racunRepository.findRacunByBrojRacuna(transakcija.getPan());
		String issuerTimestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		RezultatTransakcije rz = new RezultatTransakcije(transakcija.getPan(), transakcija.getSigurnosniKod(),
				transakcija.getNazivVlasnikaKartice(), transakcija.getDatumVazenja(), transakcija.getIznos(),
				transakcija.getAcquirerOrderId(), transakcija.getAcquirerTimestamp(), transakcija.getAcquirerSwiftCode(),tran.getId().toString(), issuerTimestamp, false, transakcija.getUplataId());
		//if(racun.isAktivan() && racun.getSigurnosniKod() == transakcija.getSigurnosniKod()){
			System.out.println(racun.getDatumVazenja().after(transakcija.getDatumVazenja()));
			System.out.println(racun.getDatumVazenja());
			System.out.println(transakcija.getDatumVazenja());
			if (Double.parseDouble(racun.getStanjeRacuna()) - Double.parseDouble(transakcija.getIznos()) > 0) {
				rz.setRezultat(true);
				racun.setStanjeRacuna( Double.toString(Double.parseDouble(racun.getStanjeRacuna()) - Double.parseDouble(transakcija.getIznos())) );
				racunRepository.save(racun);
			}
		//}

		return rz;
	}

	@Override
	public Transakcija setAcquirerSwiftCode(Transakcija transakcija) {
		transakcija.setAcquirerSwiftCode("789");
		return transakcija;
	}
}
