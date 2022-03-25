package com.casa.projeto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casa.projeto.model.Aluno;
import com.casa.projeto.repository.AlunoRepository;

@Service
public class AlunoService {

	@Autowired
	private AlunoRepository alunoRepository;

	public Aluno salvarAluno(Aluno aluno) {
		alunoRepository.save(aluno);
		return aluno;
	}

	public Aluno buscarAlunoPorId(Integer id) {
		Aluno aluno = alunoRepository.findById(id).orElseThrow(() -> new RuntimeException(id + " Não encontrado."));
		return aluno;
	}

	public List<Aluno> buscarListaAlunos() {
		List<Aluno> alunos = alunoRepository.findAll();
		return alunos;
	}

	public void deletarAluno(Integer id) {
		buscarAlunoPorId(id);
		alunoRepository.deleteById(id);
	}

}
