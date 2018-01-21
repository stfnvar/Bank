package com.tim2.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tim2.bank.model.Transakcija;

@Repository
public interface TransakcijaRepository extends JpaRepository<Transakcija, Long>{

}
