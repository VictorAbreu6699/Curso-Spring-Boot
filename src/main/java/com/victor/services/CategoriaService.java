 package com.victor.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.victor.domain.Categoria;
import com.victor.repositories.CategoriaRepository;
import com.victor.services.exceptions.DataIntegrityException;
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
	
	public Categoria insert(Categoria obj)
	{
		/*Garantia que o método save vai salvar um novo objeto, e não atualizar*/
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Categoria update(Categoria obj) 
	{
		find(obj.getId());
		return repo.save(obj);
	}
	
	public void delete(Integer id)
	{
		find(id);
		try {
		repo.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos.");
		}
		
		
	}

}