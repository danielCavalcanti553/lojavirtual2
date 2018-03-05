package com.tcc.lojavirtual.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.tcc.lojavirtual.domain.Cliente;
import com.tcc.lojavirtual.dto.ClienteDTO;
import com.tcc.lojavirtual.repository.ClienteRepository;
import com.tcc.lojavirtual.resource.exception.FieldMessage;

public class ClienteUpdateValidation implements ConstraintValidator<ClienteUpdate, ClienteDTO>{
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public void initialize(ClienteUpdate ann) {		
	}

	
	@Override
	public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		// incluir validações para @NewCliente
		
		@SuppressWarnings("unchecked")
		Map<String,String> map = (Map<String,String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);		
		Integer id = Integer.parseInt(map.get("id"));
		
		Cliente email = clienteRepository.findByEmail(objDto.getEmail());
		if(email!=null && !email.getCodigoCliente().equals(id)) list.add(new FieldMessage("email","E-mail já existe"));
		

		for(FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage())
				.addPropertyNode(e.getFieldName()).addConstraintViolation();
				
		}
		
		return list.isEmpty();
	}
	

}
