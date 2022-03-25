package com.casa.projeto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casa.projeto.model.Aluno;
import com.casa.projeto.model.Turma;
import com.casa.projeto.repository.AlunoRepository;
import com.casa.projeto.repository.TurmaRepository;

@Service
public class TurmaService {

	@Autowired
	private TurmaRepository turmaRepository;

	@Autowired
	private AlunoRepository alunoRepository;

	public Turma salvarTurma(Turma turma) {
		Aluno aluno = alunoRepository.findById(turma.getAluno().getId())
				.orElseThrow(() -> new RuntimeException("Objeto não encontrado"));
		Turma turma1 = new Turma(turma.getId(), turma.getNome(), turma.getCapacidade(), aluno);
		turma1 = turmaRepository.save(turma1);
		return turma1;
	}

	public Turma atualizarTurma(Turma turma, Integer id) {
		buscarTurmaPorId(id);
		turma.setId(id);
		turma = turmaRepository.save(turma);
		return turma;
	}

	public Turma buscarTurmaPorId(Integer id) {
		Turma turma = turmaRepository.findById(id).orElseThrow(() -> new RuntimeException(id + " Não encontrado."));
		return turma;
	}

	public List<Turma> buscarListaTurmas() {
		List<Turma> turmas = turmaRepository.findAll();
		return turmas;
	}

	public void deletarTurma(Integer id) {
		buscarTurmaPorId(id);
		turmaRepository.deleteById(id);
	}

}
