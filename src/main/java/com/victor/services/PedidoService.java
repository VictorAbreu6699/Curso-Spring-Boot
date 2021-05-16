 package com.victor.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victor.domain.Pedido;
import com.victor.repositories.PedidoRepository;
import com.victor.services.exceptions.ObjectNotFoundException;
/**
 * Classe responsavel por implementar serviços para a classe "Pedido"
 * @author Victor 
 *
 */
@Service 
public class PedidoService {

	@Autowired 
	private PedidoRepository repo;
	
	/**
	 * Busca objetos do tipo PedidoRepository por id, caso não seja encontrado ele retorna uma excessão de "ObjectNotFoundException".
	 * 
	 * @param id
	 * @return
	 */
	public Pedido find(Integer id)
	{
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException( 
				 "Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName())); 	
	}

}