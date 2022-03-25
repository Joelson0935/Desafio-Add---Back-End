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

import com.casa.projeto.model.Turma;
import com.casa.projeto.service.TurmaService;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/Turmas")
public class TurmaController {

	@Autowired
	private TurmaService turmaService;

	@PostMapping("/Salvar")
	public ResponseEntity<Turma> salvarTurma(@Valid @RequestBody Turma turma) {
		turma = turmaService.salvarTurma(turma);
		return new ResponseEntity<Turma>(turma, HttpStatus.CREATED);
	}

	@GetMapping("/Buscar/{id}")
	public ResponseEntity<Turma> buscarTurmaPorId(@PathVariable Integer id) {
		Turma turma = turmaService.buscarTurmaPorId(id);
		if (turma != null) {
			return ResponseEntity.ok(turma);
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/BuscarTodosTurmas")
	public ResponseEntity<List<Turma>> buscarListaTurmas() {
		List<Turma> turmas = turmaService.buscarListaTurmas();
		return ResponseEntity.ok(turmas);
	}

	@PutMapping("/Atualizar/{turmaId}")
	public ResponseEntity<Turma> atualizarTurmaPorId(@Valid @PathVariable Integer turmaId, @RequestBody Turma turma) {
		Turma turma1 = turmaService.buscarTurmaPorId(turmaId);
		if (turmaId != null) {
			turma1.setId(turmaId);
			turma1 = turmaService.atualizarTurma(turma1, turmaId);
			return ResponseEntity.ok(turma1);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/DeleteTurma")
	public ResponseEntity<Void> deletarTurmaPorId(@RequestParam(name = "deleteId") Integer deleteId) {
		Turma turma = turmaService.buscarTurmaPorId(deleteId);
		if (turma == null) {
			return ResponseEntity.notFound().build();
		}
		turmaService.deletarTurma(deleteId);
		return ResponseEntity.noContent().build();
	}
}
