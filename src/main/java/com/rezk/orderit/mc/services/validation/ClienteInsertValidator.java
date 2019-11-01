package com.rezk.orderit.mc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.rezk.orderit.mc.domain.enums.TipoCliente;
import com.rezk.orderit.mc.dto.ClienteNewDTO;
import com.rezk.orderit.mc.resources.exceptions.FieldMessage;
import com.rezk.orderit.mc.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO dto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		if(dto.getTipo() == TipoCliente.PESSOA_FISICA.getId() && !BR.isValidCPF(dto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCNPJ", "invalid CPF!"));
		}
		if(dto.getTipo() == TipoCliente.PESSOA_JURIDICA.getId() && !BR.isValidCNPJ(dto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCNPJ", "invalid CNPJ!"));
		}

		// Include custom tests here

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
