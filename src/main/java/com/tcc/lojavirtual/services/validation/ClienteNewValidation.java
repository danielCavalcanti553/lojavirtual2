package com.tcc.lojavirtual.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.tcc.lojavirtual.domain.Cliente;
import com.tcc.lojavirtual.dto.ClienteNewDTO;
import com.tcc.lojavirtual.repository.ClienteRepository;
import com.tcc.lojavirtual.resource.exception.FieldMessage;

public class ClienteNewValidation implements ConstraintValidator<ClienteNew, ClienteNewDTO>{
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public void initialize(ClienteNew ann) {		
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		// incluir validações para @NewCliente
		
		Cliente email = clienteRepository.findByEmail(objDto.getEmail());
		if(email!=null) list.add(new FieldMessage("email","E-mail já existe"));
		
		
		for(FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage())
				.addPropertyNode(e.getFieldName()).addConstraintViolation();
				
		}
		
		return list.isEmpty();
	}
	

}
