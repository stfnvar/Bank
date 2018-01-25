package com.tim2.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	@PostMapping("/generisiPlacanjeLink/{osiguranjeId}")
	@ResponseBody
	public String generisiPlacanjeLink(@RequestBody Uplata uplata,@PathVariable("osiguranjeId") Long osiguranjeId){
		uplata.setOsiguranjeId(osiguranjeId);
		String ret = placanjeServiceImpl.acquirerProveriZahtev(uplata);
		//vracati na insurance web app() vs direktno front odmah(+2)
		//poslati na front wrapper koji sadrzi uplatu i link!
		return ret;
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/proveriUrl", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public double proveriUrl(@RequestParam("paymentUrl") String paymentUrl, @RequestParam("paymentId") Long paymentId){
		return placanjeServiceImpl.proveriUrl(paymentUrl, paymentId);
	}
	
	//acquirer salje podatke pcc-u
	@PostMapping("/unesiPodatke")
	@ResponseBody
	public Transakcija unesiPodatke(@RequestBody Transakcija transakcija){
		placanjeServiceImpl.setAcquirerSwiftCode(transakcija);
		restTemplate.postForObject(uri.getPccUri()+"/transakcija/proslediZahtev", transakcija, Void.class);
		return transakcija;
	}
	
	//issuer prima transakciju od pcc-a(koji ga je nasao), proverava da li je u redu i vraca rezultat transakcije pcc-u
	@PostMapping("/proveriZahtev")
	public void proveriZahtev(@RequestBody Transakcija transakcija){
		System.out.println("[ISSUER] PROVERIZAHTEV USAO");
		RezultatTransakcije rz = placanjeServiceImpl.issuerProveriZahtev(transakcija);
		restTemplate.postForObject(uri.getPccUri() + "/transakcija/proslediOdgovor", rz, Void.class);
	}
	
	@PostMapping("/zabeleziPodatke")
	@ResponseBody
	public void zabeleziPodatke(@RequestBody RezultatTransakcije rezultatTransakcije){
		System.out.println("OK");
		//return "ok";
	}

}
