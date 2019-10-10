package com.rezk.orderit.mc.domain.enums;

public enum EstadoPagamento {
	
	PENDENTE(1, "Pendente"),
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Cancelado");
	
	private int id;
	private String desc;
	
	private EstadoPagamento(int id, String desc) {
		this.id = id;
		this.desc = desc;
	}

	public int getId() {
		return id;
	}

	public String getDesc() {
		return desc;
	}
	
	public static EstadoPagamento toEnum(Integer id) {
		if(id == null ) { return null; }
		
		for(EstadoPagamento x : EstadoPagamento.values()) {
			if(id.equals(x.getId())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Invalid ID : " + id);
	}

}
