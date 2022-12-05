package com.generation.lojagames_backend.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.generation.lojagames_backend.model.Produto;
import com.generation.lojagames_backend.repository.CategoriaRepository;
import com.generation.lojagames_backend.repository.ProdutoRepository;

@RestController //Indica que é uma classe controladora
@RequestMapping("/jogos") //Identifica o endereço para realizar a busca
@CrossOrigin(origins= "*", allowedHeaders="*") //Permiti que o frontend converse com a API - Frontend pode está num servidor e o backend em outro
public class ProdutoController {
	@Autowired //Injeção de Dependencias
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping //Consulta
	public ResponseEntity<List<Produto>> getAll(){
		
		return ResponseEntity.ok(produtoRepository.findAll());
		// SELECT * FROM tb_produtos;
		//ResponseEntity = Resposta HTTP (Deu certo - HTTP 200 / Deu errado - HTTP 500)
		//Traga pra mim uma resposta HTTP contendo no corpo da resposta uma estrutura do tipo list contendo todos os produtos encontrados
	} 
	
	@GetMapping ("/{id}") //Variável de Caminho --- http://localhost:8080/jogos/1
	public ResponseEntity<Produto> getById(@PathVariable Long id){
//		Optional <Produto> buscaProduto = produtoRepository.findById(id);
//		
//		if (buscaProduto.isPresent())
//			return ResponseEntity.ok(buscaProduto.get());
//		else
//			return ResponseEntity.notFound().build();
		return produtoRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());
		// SELECT * FROM tb_produtos WHERE id=1;
	} 
	
	@GetMapping ("/nome/{nome}")
	public ResponseEntity<List<Produto>> getByNome(@PathVariable String nome){
		
		return ResponseEntity.ok(produtoRepository.findAllByNomeContainingIgnoreCase(nome));
		
		// --- SELECT * FROM tb_produtos WHERE nome LIKE "%nome%";
	}
	
	@PostMapping
	public ResponseEntity <Produto> postProduto (@Valid @RequestBody  Produto produto){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(produtoRepository.save(produto));
		
	}
	
	@PutMapping
	public ResponseEntity<Produto> putProduto(@Valid @RequestBody Produto produto) {
					
		if (produtoRepository.existsById(produto.getId())){

			return categoriaRepository.findById(produto.getCategoria().getId())
					.map(resposta -> ResponseEntity.status(HttpStatus.OK).body(produtoRepository.save(produto)))
					.orElse(ResponseEntity.badRequest().build());
		}		
		
		return ResponseEntity.notFound().build();

	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProduto(@PathVariable Long id) {
		
		return produtoRepository.findById(id)
				.map(resposta -> {
					produtoRepository.deleteById(id);
					return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
				})
				.orElse(ResponseEntity.notFound().build()); 
	}
	
	
		@GetMapping("/preco_maior/{preco}")
		public ResponseEntity <List<Produto>> getPrecoMaiorQue(@PathVariable float preco){ 
			return ResponseEntity.ok(produtoRepository.findByPrecoGreaterThanOrderByPreco(preco));
		}
		
		
		@GetMapping("/preco_menor/{preco}")
		public ResponseEntity <List<Produto>> getPrecoMenorQue(@PathVariable float preco){ 
			return ResponseEntity.ok(produtoRepository.findByPrecoLessThanOrderByPrecoDesc(preco));
		}
}
//Passo: 3