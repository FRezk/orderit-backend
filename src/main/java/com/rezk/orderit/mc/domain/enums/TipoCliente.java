package com.rezk.orderit.mc.domain.enums;

public enum TipoCliente {
	PESSOA_FISICA(1, "Pessoa Fisica"),
	PESSOA_JURIDICA(2, "Pessoa Juridica");

	private int id;
	private String desc;
	
	private TipoCliente(int id, String desc) {
		this.id = id;
		this.desc = desc;
	}

	public int getId() {
		return id;
	}

	public String getDesc() {
		return desc;
	}
	
	public static TipoCliente toEnum(Integer id) {
		if(id == null ) { return null; }
		
		for(TipoCliente x : TipoCliente.values()) {
			if(id.equals(x.getId())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Invalid ID : " + id);
	}
	
}
