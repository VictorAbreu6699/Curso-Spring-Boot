package com.victor.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.victor.domain.Cliente;
import com.victor.domain.enums.TipoCliente;
import com.victor.dto.ClienteNewDTO;
import com.victor.repositories.ClienteRepository;
import com.victor.resources.exception.FieldMessage;
import com.victor.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	 @Override public void initialize(ClienteInsert ann) {
	 }
	 
	 @Autowired 
	 private ClienteRepository clienteRepository;
	 
	 @Override
	 public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
	 List<FieldMessage> list = new ArrayList<>();
	 
	 if (objDto.getTipoCliente().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
		list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
	 }
	 
	 if (objDto.getTipoCliente().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
	 }	 
	 
	 Cliente aux = clienteRepository.findByEmail(objDto.getEmail());
	 
	 if (aux != null) {
		list.add(new FieldMessage("email", "E-mail já existente"));
	}
	 
	 for (FieldMessage e : list) {
		 context.disableDefaultConstraintViolation();
		 context.buildConstraintViolationWithTemplate(e.getMessage())
		 .addPropertyNode(e.getFieldName()).addConstraintViolation();
	 }
	 return list.isEmpty();
	 }
}