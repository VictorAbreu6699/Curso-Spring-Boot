package com.victor.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.victor.domain.enums.TipoCliente;
@Entity
public class Cliente {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String email;
	private String CPFouCNPJ;
	private Integer tipoCliente;	
	
	@OneToMany(mappedBy="cliente")
	private List<Endereco> enderecos;
	
	@ElementCollection
	@CollectionTable(name="TELEFONE")
	private Set<String> telefones;
	
	public Cliente()
	{
		this.id = null;
		this.nome = null;
		this.email = null;
		CPFouCNPJ = null;
		this.tipoCliente = null;
		telefones = new HashSet<>();
		enderecos = new ArrayList<>();
	}
	public Cliente(Integer id, String nome, String email, String cPFouCNPJ, TipoCliente tipoCliente) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		CPFouCNPJ = cPFouCNPJ;
		this.tipoCliente = tipoCliente.getCod();
		telefones = new HashSet<>();
		enderecos = new ArrayList<>();
	}
	public Integer getId() {
		return id;
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
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getCPFouCNPJ() {
		return CPFouCNPJ;
	}
	
	public void setCPFouCNPJ(String cPFouCNPJ) {
		CPFouCNPJ = cPFouCNPJ;
	}
	
	public TipoCliente getTipoCliente() {
		return TipoCliente.toEnum(tipoCliente);
	}
	
	public void setTipoCliente(TipoCliente tipoCliente) {
		this.tipoCliente = tipoCliente.getCod();
	}
	
	public Set<String> getTelefones() {
		return telefones;
	}
	
	public void setTelefones(Set<String> telefones) {
		this.telefones = telefones;
	}
	
	public List<Endereco> getEnderecos() {
		return enderecos;
	}
	
	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}
	
		
}
