package com.cursomc.domain.enuns;

public enum TipoCliente {

	PESSOAFISICA("Pessoa Fisica",1),
	PESSOAJURIDICA("Pessoa Juridica",2);
	
	private String descricao;
	private int cod;
	
	private TipoCliente(String descricao,int cod) {
		this.descricao = descricao;
		this.cod = cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public int getCod() {
		return cod;
	}
	
	public static TipoCliente ConverteEnun(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(TipoCliente x: TipoCliente.values()) {
			if(x.getCod() == cod) {
				return x;
			}
		}
		throw new IllegalArgumentException("Código invalido: "+ cod);
	}

	
	
}
