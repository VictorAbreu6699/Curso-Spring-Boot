package com.victor.resources;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.victor.domain.Pedido;
import com.victor.services.PedidoService;
/**
 * Classe responsavel pelos Métodos GET, PUSH e etc da classe Pedido.
 * @author User
 *
 */
@RestController
@RequestMapping(value="/pedidos")
public class PedidoResource {
	
	@Autowired
	private PedidoService service;
	
	/**
	 * Retorna um objeto com o ID solicitado.
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Pedido> find(@PathVariable Integer id)
	{
		Pedido obj = service.find(id);
		
		return ResponseEntity.ok().body(obj);
		}		
}
