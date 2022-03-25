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

import com.casa.projeto.model.Escola;
import com.casa.projeto.service.EscolaService;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/Escolas")
public class EscolaController {

	@Autowired
	private EscolaService escolaService;

	@PostMapping("/Salvar")
	public ResponseEntity<Escola> salvarEscola(@Valid @RequestBody Escola escola) {
		escola = escolaService.salvarEscola(escola);
		return new ResponseEntity<Escola>(escola, HttpStatus.CREATED);
	}

	@GetMapping("/Buscar/{id}")
	public ResponseEntity<Escola> buscarEscolaPorId(@PathVariable Integer id) {
		Escola escola = escolaService.buscarEscolaPorId(id);
		if (escola != null) {
			return ResponseEntity.ok(escola);
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/BuscarTodosEscolas")
	public ResponseEntity<List<Escola>> buscarListaEscolas() {
		List<Escola> escolas = escolaService.buscarListaEscolas();
		return ResponseEntity.ok(escolas);
	}

	@PutMapping("/Atualizar/{escolaId}")
	public ResponseEntity<Escola> atualizarEscolaPorId(@Valid @PathVariable Integer escolaId, @RequestBody Escola escola) {
		Escola escola1 = escolaService.buscarEscolaPorId(escolaId);
		if (escolaId != null) {
			escola1.setId(escolaId);
			escola1 = escolaService.atualizarEscola(escola1, escolaId);
			return ResponseEntity.ok(escola1);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/DeleteEscola")
	public ResponseEntity<Void> deletarEscolaPorId(@RequestParam(name = "deleteId") Integer deleteId) {
		Escola escola = escolaService.buscarEscolaPorId(deleteId);
		if (escola == null) {
			return ResponseEntity.notFound().build();
		}
		escolaService.deletarEscola(deleteId);
		return ResponseEntity.noContent().build();
	}
}
