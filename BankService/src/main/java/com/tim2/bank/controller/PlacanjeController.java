package com.tim2.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.tim2.bank.config.DatabaseUri;
import com.tim2.bank.model.RezultatTransakcije;
import com.tim2.bank.model.Transakcija;
import com.tim2.bank.model.Uplata;
import com.tim2.bank.serviceimpl.PlacanjeServiceImpl;

@RestController
@RequestMapping("/api/placanje")
public class PlacanjeController {
	
	@Autowired
	private DatabaseUri uri;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private PlacanjeServiceImpl placanjeServiceImpl;
	
	//acquirer bankaOsiguravajuceKuce
	
	@PostMapping("/generisiPlacanjeLink")
	@ResponseBody
	public String generisiPlacanjeLink(@RequestBody Uplata uplata){
		String ret = placanjeServiceImpl.acquirerProveriZahtev(uplata);
		//vracati na insurance web app() vs direktno front odmah(+2)
		//poslati na front wrapper koji sadrzi uplatu i link!
		return ret;
	}
	
	@GetMapping("/proveriUrl")
	@ResponseBody
	public boolean proveriUrl(@RequestParam("paymentUrl") String paymentUrl, @RequestParam("paymentId") Long paymentId){
		return placanjeServiceImpl.proveriUrl(paymentUrl, paymentId);
	}
	
	//acquirer salje podatke pcc-u
	@PostMapping("/unesiPodatke")
	@ResponseBody
	public Transakcija unesiPodatke(@RequestBody Transakcija transakcija){
		return restTemplate.postForObject(uri.getPccUri()+"/transakcija/proslediZahtev", transakcija, Transakcija.class);
	}
	
	//issuer prima transakciju od pcc-a(koji ga je nasao), proverava da li je u redu i vraca rezultat transakcije pcc-u
	@PostMapping("/proveriZahtev")
	@ResponseBody
	public Transakcija proveriZahtev(@RequestBody Transakcija transakcija){
		RezultatTransakcije rz = placanjeServiceImpl.issuerProveriZahtev(transakcija);
		return restTemplate.postForObject(uri.getPccUri() + "/", rz, RezultatTransakcije.class);
	}

}
