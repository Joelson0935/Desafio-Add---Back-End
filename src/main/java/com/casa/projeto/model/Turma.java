package com.casa.projeto.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
public class Turma implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nome;

	private Integer capacidade;

	@JsonProperty(access = Access.WRITE_ONLY)
	@ManyToOne
	private Aluno aluno;

	@JsonProperty(access = Access.READ_ONLY)
	@OneToMany
	private List<Escola> escolas = new ArrayList<Escola>();

	public Turma() {
	}

	public Turma(String nome, Integer capacidade, Aluno aluno) {
		super();
		this.nome = nome;
		this.capacidade = capacidade;
		this.aluno = aluno;
	}

	public Turma(Integer id, String nome, Integer capacidade, Aluno aluno) {
		super();
		this.id = id;
		this.nome = nome;
		this.capacidade = capacidade;
		this.aluno = aluno;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(Integer capacidade) {
		this.capacidade = capacidade;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public List<Escola> getEscolas() {
		return escolas;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Turma other = (Turma) obj;
		return Objects.equals(id, other.id);
	}

}
