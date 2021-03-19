package com.cursomc.domain.enuns;

public enum EstadoPagamento {

	PENDENTE(1, "Pendente"),
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Cancelado");
	
	private int cod;
	private String estado;
	
	private EstadoPagamento(int cod, String estado) {
		this.cod = cod;
		this.estado = estado;
	}

	public int getCod() {
		return cod;
	}
	public String getEstado() {
		return estado;
	}
	public static EstadoPagamento converteEstadoPagamento(int cod) {
		for(EstadoPagamento x : EstadoPagamento.values()) {
			if(x.getCod() == cod) {
				return x;
			}
		}
		throw new IllegalArgumentException("Codigo invalido: "+cod);
	}

	
}
