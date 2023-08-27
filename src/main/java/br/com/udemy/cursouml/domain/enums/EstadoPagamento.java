package br.com.udemy.cursouml.domain.enums;

public enum EstadoPagamento {
	
	CANCELADO (1, "Cancelado"),
	PENDENTE (2, "Pendente"),
	QUITADO (3, "Quitado");	
	
	private Integer codigo;
	private String status;
	
	private EstadoPagamento(Integer codigo, String status) {
		this.codigo = codigo;
		this.status = status;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getStatus() {
		return status;
	}
	
	public static EstadoPagamento toEnum(Integer codigo) {
		
		if(codigo == null) {
			return null;
		}
		
		for(EstadoPagamento x: EstadoPagamento.values()) {
			if(x.getCodigo().equals(codigo)) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Código inválido: " + codigo);
	}

}
