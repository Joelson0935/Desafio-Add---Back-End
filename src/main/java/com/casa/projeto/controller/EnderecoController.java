package com.casa.projeto.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.casa.projeto.model.Endereco;
import com.casa.projeto.service.EnderecoService;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/Enderecos")
public class EnderecoController {

	@Autowired
	private EnderecoService enderecoService;

	@PostMapping("/Salvar")
	public ResponseEntity<Endereco> salvarEndereco(@Valid @RequestBody Endereco endereco) {
		endereco = enderecoService.salvarEndereco(endereco);
		return new ResponseEntity<Endereco>(endereco, HttpStatus.CREATED);
	}

	@GetMapping("/Buscar/{id}")
	public ResponseEntity<Endereco> buscarEnderecoPorId(@PathVariable Integer id) {
		Endereco endereco = enderecoService.buscarEnderecoPorId(id);
		if (endereco != null) {
			return ResponseEntity.ok(endereco);
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/BuscarTodosEnderecos")
	public ResponseEntity<List<Endereco>> buscarListaEnderecos() {
		List<Endereco> enderecos = enderecoService.buscarListaEnderecos();
		return ResponseEntity.ok(enderecos);
	}

	@PutMapping("/Atualizar/{enderecoId}")
	public ResponseEntity<Endereco> atualizarEnderecoPorId(@Valid @PathVariable Integer enderecoId,
			@RequestBody Endereco endereco) {
		Endereco endereco1 = enderecoService.buscarEnderecoPorId(enderecoId);
		if (enderecoId != null) {
			endereco1.setId(enderecoId);
			endereco1 = enderecoService.atualizarEndereco(endereco1, enderecoId);
			return ResponseEntity.ok(endereco1);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/DeleteEndereco")
	public ResponseEntity<Void> deletarEnderecoPorId(@RequestParam(name = "deleteId") Integer deleteId) {
		Endereco endereco = enderecoService.buscarEnderecoPorId(deleteId);
		if (endereco == null) {
			return ResponseEntity.notFound().build();
		}
		enderecoService.deletarEndereco(deleteId);
		return ResponseEntity.noContent().build();
	}
}
