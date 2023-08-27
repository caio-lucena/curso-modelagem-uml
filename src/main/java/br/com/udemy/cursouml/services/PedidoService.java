package br.com.udemy.cursouml.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.udemy.cursouml.domain.Pedido;
import br.com.udemy.cursouml.repositories.PedidoRepository;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	
	public Pedido buscar(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new IllegalArgumentException("Não foi possível encontrar o ID:"
				+ id + ", tipo: " + Pedido.class.getName()));  
		
	}

}
