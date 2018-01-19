package com.tim2.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tim2.bank.model.Racun;

@Repository
public interface RacunRepository extends JpaRepository<Racun, Long> {
	
	public Racun findRacunByBrojRacuna(String brojRacuna);
}
