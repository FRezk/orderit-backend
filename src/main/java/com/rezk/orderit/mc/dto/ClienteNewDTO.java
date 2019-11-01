package com.rezk.orderit.mc.dto;

import java.io.Serializable;

import com.rezk.orderit.mc.domain.Cidade;
import com.rezk.orderit.mc.domain.Cliente;
import com.rezk.orderit.mc.domain.Endereco;
import com.rezk.orderit.mc.domain.enums.TipoCliente;

public class ClienteNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;

		// Cliente
	private String nome;
	private String email;
	private String cpfOuCnpj;
	private Integer tipo;
	
		// Endereco
	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private String cep;
		// Cidade
	private Integer cidadeId;
	
	private String telefone1;
	private String telefone2;
	private String telefone3;
	
	public ClienteNewDTO() {}

	public ClienteNewDTO(String nome, String email, String cpfOuCnpj, Integer tipo, String logradouro, String numero,
			String complemento, String bairro, String cep, Integer cidadeId, String telefone1, String telefone2,
			String telefone3) {
		super();
		this.nome = nome;
		this.email = email;
		this.cpfOuCnpj = cpfOuCnpj;
		this.tipo = tipo;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cep = cep;
		this.cidadeId = cidadeId;
		this.telefone1 = telefone1;
		this.telefone2 = telefone2;
		this.telefone3 = telefone3;
	}
	
	public Cliente fromDTO() {
		Cliente cliente = new Cliente(null, this.nome, this.email, this.cpfOuCnpj, TipoCliente.toEnum(this.tipo));
		Endereco endereco = new Endereco(null, this.logradouro, this.numero, this.complemento, this.bairro, this.cep, cliente, new Cidade(this.cidadeId, null, null));
		cliente.getEnderecos().add(endereco);
		cliente.getTelefones().add(this.getTelefone1());
		if(this.telefone2 != null) {
			cliente.getTelefones().add(this.getTelefone2());
		}
		if(this.telefone3 != null) {
			cliente.getTelefones().add(this.getTelefone3());
		}
		return cliente;
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

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Integer getCidadeId() {
		return cidadeId;
	}

	public void setCidadeId(Integer cidadeId) {
		this.cidadeId = cidadeId;
	}

	public String getTelefone1() {
		return telefone1;
	}

	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}

	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}

	public String getTelefone3() {
		return telefone3;
	}

	public void setTelefone3(String telefone3) {
		this.telefone3 = telefone3;
	}
	
}
