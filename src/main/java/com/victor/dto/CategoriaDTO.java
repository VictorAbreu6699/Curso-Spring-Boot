package com.victor.dto;

import java.io.Serializable;

import com.victor.domain.Categoria;

public class CategoriaDTO implements Serializable{	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	
	public CategoriaDTO()
	{
		id = null;
		nome = null;
	}
	
	public CategoriaDTO(Categoria obj)
	{
		id = obj.getId();
		nome = obj.getNome();
	}
	
	public Integer getId()
	{
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
			
}
