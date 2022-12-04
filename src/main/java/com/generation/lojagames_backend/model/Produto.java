package com.generation.lojagames_backend.model;

import java.time.LocalDate;
import javax.persistence.Entity; //@Entity
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity //Gerar uma entidade para o banco de banco de dados (Equivale ao Create Database)
@Table(name="tb_produtos") //Criar tabela produtos (Equivale ao Create Table)
public class Produto {
	//Configurar os atributos.
	
	@Id //Identifica no banco de dados com o Identificador Primário Único da Tabela.
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Referente ao Autoincremento
	private Long id;
	
	@NotBlank (message = "Nome obrigatório!") //Não pode ser nulo e não pode ser espaço em branco (vazio).
	@Size (min=3 , max= 30, message="Titulo deve conter no mínimo 3 e no máximo 30 caracteres")
	private String nome;
	
	@NotBlank (message = "Descrição obrigatório!") //NotBlank só funciona com String
	@Size (min=10 , max= 100, message="Descrição do Produto!")
	private String descricao;
	
	@NotNull
	private float preco;
	
	@NotNull
	private int quantidade;
	
	@UpdateTimestamp
	private LocalDate data; //Pega a data automático do servidor
	
	@ManyToOne
	@JsonIgnoreProperties("produto")
	private Categoria categoria;
	
	//Constructor Getters and Setters
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	
	
	
//Passo 1!
}
