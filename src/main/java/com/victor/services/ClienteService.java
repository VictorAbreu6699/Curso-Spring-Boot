 package com.victor.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victor.domain.Cliente;
import com.victor.repositories.ClienteRepository;
import com.victor.services.exceptions.ObjectNotFoundException;
/**
 * Classe responsavel por implementar serviços para a classe "Cliente"
 * @author Victor 
 *
 */
@Service 
public class ClienteService {

	@Autowired 
	private ClienteRepository repo;
	
	/**
	 * Busca objetos do tipo ClienteRepository por id, caso não seja encontrado ele retorna uma excessão de "ObjectNotFoundException".
	 * 
	 * @param id
	 * @return
	 */
	public Cliente find(Integer id)
	{
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException( 
				 "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName())); 	
	}

}