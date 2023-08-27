package br.com.udemy.cursouml.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.udemy.cursouml.domain.Cliente;
import br.com.udemy.cursouml.repositories.ClienteRepository;
import br.com.udemy.cursouml.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	public Cliente buscar(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		
		return obj.orElseThrow(()-> new ObjectNotFoundException("Não foi possível encontrar o objeto ID: "
				+ id + ", tipo: "
				+ Cliente.class.getName()));
	}

}
