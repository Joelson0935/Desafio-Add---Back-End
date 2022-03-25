package com.casa.projeto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casa.projeto.model.Endereco;
import com.casa.projeto.repository.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;

	public Endereco salvarEndereco(Endereco endereco) {
		endereco = enderecoRepository.save(endereco);
		return endereco;
	}

	public Endereco atualizarEndereco(Endereco endereco, Integer id) {
		buscarEnderecoPorId(id);
		endereco.setId(id);
		endereco = enderecoRepository.save(endereco);
		return endereco;
	}

	public Endereco buscarEnderecoPorId(Integer id) {
		Endereco endereco = enderecoRepository.findById(id).orElseThrow(() -> new RuntimeException(id + " Não encontrado."));
		return endereco;
	}

	public List<Endereco> buscarListaEnderecos() {
		List<Endereco> enderecos = enderecoRepository.findAll();
		return enderecos;
	}

	public void deletarEndereco(Integer id) {
		buscarEnderecoPorId(id);
		enderecoRepository.deleteById(id);
	}

}
