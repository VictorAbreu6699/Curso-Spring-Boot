 package com.victor.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.victor.domain.Cidade;
import com.victor.domain.Cliente;
import com.victor.domain.Endereco;
import com.victor.domain.enums.TipoCliente;
import com.victor.dto.ClienteDTO;
import com.victor.dto.ClienteNewDTO;
import com.victor.repositories.ClienteRepository;
import com.victor.repositories.EnderecoRepository;
import com.victor.services.exceptions.DataIntegrityException;
import com.victor.services.exceptions.ObjectNotFoundException;
/**
 * Classe responsavel por implementar serviços para a classe "Cliente"
 * @author Victor 
 *
 */
@Service 
public class ClienteService {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired 
	private ClienteRepository repo;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
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
	
	public Cliente insert(Cliente obj)
	{
		/*Garantia que o método save vai salvar um novo objeto, e não atualizar*/
		obj.setId(null);		
		obj = repo.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}
	
	public Cliente update(Cliente obj) 
	{
		Cliente newObj = find(obj.getId());		
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer id)
	{
		find(id);
		try {
		repo.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque há pedidos relacionados ao cliente.");
		}
	}
	
	public List<Cliente> findAll()
	{
		return repo.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction)
	{
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO objDTO)
	{
		return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null, null);
	}
	
	public Cliente fromDTO(ClienteNewDTO objDTO)
	{
		Cliente cli = new Cliente(null, objDTO.getNome(), objDTO.getEmail(), objDTO.getCpfOuCnpj(),TipoCliente.toEnum(objDTO.getTipoCliente()), bCryptPasswordEncoder.encode(objDTO.getSenha()));
		Cidade cid = new Cidade(objDTO.getCidadeId(), null, null);
		Endereco end = new Endereco(null, objDTO.getLogradouro(), objDTO.getNumero(), objDTO.getComplemento(), objDTO.getBairro(), objDTO.getCep(), cli, cid);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDTO.getTelefone1());
		if (objDTO.getTelefone2()!= null) {
			cli.getTelefones().add(objDTO.getTelefone2());
		}
		if (objDTO.getTelefone3()!= null) {
			cli.getTelefones().add(objDTO.getTelefone3());
		}
		return cli;
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}


}