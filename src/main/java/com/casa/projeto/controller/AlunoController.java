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

import com.casa.projeto.model.Aluno;
import com.casa.projeto.service.AlunoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/Alunos")
@Api(value = "API REST de cadastro de alunos.")
@CrossOrigin(origins = "*")
public class AlunoController {

	@Autowired
	private AlunoService alunoService;

	@PostMapping("/Salvar")
	@ApiOperation(value = "Guarda os dados no banco de dados MySql.")
	public ResponseEntity<Aluno> salvarAluno(@Valid @RequestBody Aluno aluno) {
		aluno = alunoService.salvarAluno(aluno);
		return new ResponseEntity<Aluno>(aluno, HttpStatus.CREATED);
	}

	@GetMapping("/Buscar/{id}")
	@ApiOperation(value = "Busca um aluno cadastrado no banco de dados pelo número do ID.")
	public ResponseEntity<Aluno> buscarAlunoPorId(@PathVariable Integer id) {
		Aluno aluno = alunoService.buscarAlunoPorId(id);
		if (aluno != null) {
			return ResponseEntity.ok(aluno);
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/BuscarTodosAlunos")
	@ApiOperation(value = "Busca a lista completa de alunos cadastrados no banco de dados.")
	public ResponseEntity<List<Aluno>> buscarListaAlunos() {
		List<Aluno> alunos = alunoService.buscarListaAlunos();
		return ResponseEntity.ok(alunos);
	}

	@PutMapping("/Atualizar/{alunoId}")
	@ApiOperation(value = "Atualiza um determinado aluno cadastrado buscando o mesmo pelo seu número de ID.")
	public ResponseEntity<Aluno> atualizarAlunoPorId(@Valid @PathVariable Integer alunoId, @RequestBody Aluno aluno) {
		Aluno aluno1 = alunoService.buscarAlunoPorId(alunoId);
		if (alunoId != null) {
			aluno.setId(alunoId);
			aluno1 = alunoService.salvarAluno(aluno);
			return ResponseEntity.ok(aluno1);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/DeleteAluno")
	@ApiOperation(value = "Deleta do banco de dados um aluno cadastrado pelo seu respectivo ID.")
	public ResponseEntity<Void> deletarAlunoPorId(@RequestParam(name = "deleteId") Integer deleteId) {
		Aluno aluno = alunoService.buscarAlunoPorId(deleteId);
		if (aluno == null) {
			return ResponseEntity.notFound().build();
		}
		alunoService.deletarAluno(deleteId);
		return ResponseEntity.noContent().build();
	}
}
