package com.casa.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.casa.projeto.model.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Integer> {

}
