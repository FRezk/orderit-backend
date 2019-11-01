package com.rezk.orderit.mc.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.rezk.orderit.mc.domain.Cliente;
import com.rezk.orderit.mc.services.validation.ClienteUpdate;

@ClienteUpdate
public class ClienteDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message = "Cliente needs a name!")
	@Length(min = 3, max = 120, message = "Cliente name needs to be between 3 and 120 characters.")
	private String nome;
	
	@NotEmpty(message = "Cliente needs an email!")
	@Email(message = "Invalid e-mail!")
	private String email;
	
	
	public ClienteDTO() {}
	
	public ClienteDTO(Cliente cliente) {
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.email = cliente.getEmail();
	}
	
	public Cliente fromDTO() {
		return new Cliente(this.id, this.nome, this.email, null, null);
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

}
