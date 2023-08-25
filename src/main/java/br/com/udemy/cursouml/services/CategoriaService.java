package br.com.udemy.cursouml.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.udemy.cursouml.domain.Categoria;
import br.com.udemy.cursouml.repositories.CategoriaRepository;
import br.com.udemy.cursouml.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria buscar(Integer id) {						
		Optional<Categoria> obj = repo.findById(id);
		
		return obj.orElseThrow(()-> new ObjectNotFoundException("Não foi possível encontrar o objeto ID: "
				+ id + ", tipo: "+ Categoria.class.getName()));
	}

}
