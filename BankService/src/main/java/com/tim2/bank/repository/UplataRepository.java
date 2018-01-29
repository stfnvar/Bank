package com.tim2.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tim2.bank.model.Uplata;

public interface UplataRepository extends JpaRepository<Uplata, Long>{
	
	public Uplata getUplataByUplataLinkContainingAndUplataIdDatabase(String uplataLink, Long uplataId);
	
	public Uplata getUplataByUplataIdDatabase(Long uplataIdDatabase);
}
