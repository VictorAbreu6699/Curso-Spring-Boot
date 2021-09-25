 package com.victor.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.victor.domain.Categoria;
import com.victor.domain.Produto;
import com.victor.repositories.CategoriaRepository;
import com.victor.repositories.ProdutoRepository;
import com.victor.services.exceptions.ObjectNotFoundException;


/**
 * Classe responsavel por implementar serviços para a classe "Produto"
 * @author Victor 
 *
 */
@Service 
public class ProdutoService {

	@Autowired
	private CategoriaRepository CategoriaRepository;
	
	@Autowired 
	private ProdutoRepository repo;
	
	/**
	 * Busca objetos do tipo PedidoRepository por id, caso não seja encontrado ele retorna uma excessão de "ObjectNotFoundException".
	 * 
	 * @param id
	 * @return
	 */
	public Produto find(Integer id)
	{
		Optional<Produto> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException( 
				 "Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName())); 	
	}
	
	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction) 
	{
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = CategoriaRepository.findAllById(ids);
		return repo.search(nome, categorias, pageRequest);
	}

}