package com.casa.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.casa.projeto.model.Turma;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Integer> {

}
