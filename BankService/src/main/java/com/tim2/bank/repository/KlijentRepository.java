package com.tim2.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tim2.bank.model.Klijent;

@Repository
public interface KlijentRepository extends JpaRepository<Klijent, Long>{

	public Klijent findKlijentByMerchantId(String merchantId);
}
