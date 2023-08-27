package br.com.udemy.cursouml.domain.enums;

public enum TipoCliente {
	
	PESSOA_FISICA (1, "Pessoa Física"),
	PESSOA_JURIDICA(2, "Pessoa Jurídica");
	
	private Integer id;
	private String descricao;
	
	private TipoCliente(Integer id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}
	
	
	public static TipoCliente toEnum(Integer codigo) {
		
		if(codigo == null) {
			return null;			
		}
		
		for(TipoCliente x: TipoCliente.values()) {
			if(codigo.equals(x.getId())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + codigo);		
	}
	
}
