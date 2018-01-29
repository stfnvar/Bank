package com.tim2.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tim2.bank.model.RezultatTransakcije;

@Repository
public interface RezultatTransakcijeRepository extends JpaRepository<RezultatTransakcije, Long> {
	public RezultatTransakcije findRezultatTransakcijeByUplataId(Long uplataId);
}
