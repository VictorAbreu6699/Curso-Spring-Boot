 package com.victor.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victor.domain.Categoria;
import com.victor.repositories.CategoriaRepository;
import com.victor.services.exceptions.ObjectNotFoundException;
/**
 * Classe responsavel por implementar serviços para a classe "Categoria"
 * @author Victor 
 *
 */
@Service 
public class CategoriaService {

	@Autowired 
	private CategoriaRepository repo;
	
	/**
	 * Busca objetos do tipo CategoriaRepository por id, caso não seja encontrado ele retorna uma excessão de "ObjectNotFoundException".
	 * 
	 * @param id
	 * @return
	 */
	public Categoria find(Integer id)
	{
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException( 
				 "Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName())); 	
	}

}