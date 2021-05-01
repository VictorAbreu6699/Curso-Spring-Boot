package com.victor.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.victor.domain.Categoria;
import com.victor.services.CategoriaService;
/**
 * Classe responsavel pelos Métodos GET, PUSH e etc da classe Categoria.
 * @author User
 *
 */
@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;
	
	/**
	 * Retorna um objeto com o ID solicitado.
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id)
	{
		Categoria obj = service.find(id);
		
		return ResponseEntity.ok().body(obj);
		}		
}
