package com.generation.lojagames_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.generation.lojagames_backend.model.Produto;

@Repository //Indicar que esta INterface é do tipo repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{ //Produto é a classe model, Long = Attributo da chave primario
	
	public List <Produto> findAllByNomeContainingIgnoreCase(@Param("nome") String nome);

	public List <Produto> findByPrecoGreaterThanOrderByPreco(float preco);
	 
	public List <Produto> findByPrecoLessThanOrderByPrecoDesc(float preco);
}
//Passo 2 (Criar métodos para interagir  com o Banco de Dados)
