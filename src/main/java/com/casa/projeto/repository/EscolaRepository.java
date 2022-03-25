package com.casa.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.casa.projeto.model.Escola;

@Repository
public interface EscolaRepository extends JpaRepository<Escola, Integer> {

}
