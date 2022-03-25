package com.casa.projeto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casa.projeto.model.Endereco;
import com.casa.projeto.model.Escola;
import com.casa.projeto.model.Turma;
import com.casa.projeto.repository.EnderecoRepository;
import com.casa.projeto.repository.EscolaRepository;
import com.casa.projeto.repository.TurmaRepository;

@Service
public class EscolaService {

	@Autowired
	private EscolaRepository escolaRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private TurmaRepository turmaRepository;

	public Escola salvarEscola(Escola escola) {
		Endereco endereco = enderecoRepository.findById(escola.getEndereco().getId()).orElseThrow(() -> new RuntimeException("Objeto Não Encontrado"));
		Turma turma = turmaRepository.findById(escola.getTurma().getId()).orElseThrow(() -> new RuntimeException("Objeto Não Encontrado"));
		Escola escola1 = new Escola(escola.getId(), escola.getNome(), endereco, turma);
		escola1 = escolaRepository.save(escola1);
		return escola1;
	}

	public Escola atualizarEscola(Escola escola, Integer id) {
		buscarEscolaPorId(id);
		escola.setId(id);
		escola = escolaRepository.save(escola);
		return escola;
	}

	public Escola buscarEscolaPorId(Integer id) {
		Escola escola = escolaRepository.findById(id).orElseThrow(() -> new RuntimeException(id + " Não encontrado."));
		return escola;
	}

	public List<Escola> buscarListaEscolas() {
		List<Escola> escolas = escolaRepository.findAll();
		return escolas;
	}

	public void deletarEscola(Integer id) {
		buscarEscolaPorId(id);
		escolaRepository.deleteById(id);
	}

}
