package com.victor.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.victor.domain.Cliente;
import com.victor.dto.ClienteDTO;
import com.victor.repositories.ClienteRepository;
import com.victor.resources.exception.FieldMessage;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {
	 @Override public void initialize(ClienteUpdate ann) {
	 }
	 
	 @Autowired
	 private HttpServletRequest request;
	 
	 @Autowired 
	 private ClienteRepository clienteRepository;
	 
	 @Override
	 public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {
	 List<FieldMessage> list = new ArrayList<>();
	 
	 Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
	 Integer uriId = Integer.parseInt(map.get("id"));
	 Cliente aux = clienteRepository.findByEmail(objDto.getEmail());
	 
	 if (aux != null && !aux.getId().equals(uriId)) {
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