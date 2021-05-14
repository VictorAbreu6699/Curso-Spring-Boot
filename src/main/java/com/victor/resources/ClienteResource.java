package com.victor.resources;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.victor.domain.Cliente;
import com.victor.services.ClienteService;
/**
 * Classe responsavel pelos Métodos GET, PUSH e etc da classe Cliente.
 * @author User
 *
 */
@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService service;
	
	/**
	 * Retorna um objeto com o ID solicitado.
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id)
	{
		Cliente obj = service.find(id);
		
		return ResponseEntity.ok().body(obj);
		}		
}
