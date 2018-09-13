package br.com.cursoudemy.sbmongodb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cursoudemy.sbmongodb.domain.User;
import br.com.cursoudemy.sbmongodb.dto.UserDTO;
import br.com.cursoudemy.sbmongodb.repository.UserRepository;
import br.com.cursoudemy.sbmongodb.services.exception.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repo;
	
	public List<User> findAll() {
		return repo.findAll();
	}
	
	public User findById(String id) {
		Optional<User> user = repo.findById(id);
		return user.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public User insert(User user) {
		return repo.insert(user);
	}
	
	public void delete(String id) {
		findById(id);
		repo.deleteById(id);
	}
	
	public User fromDTO(UserDTO userDto) {
		return new User(userDto.getId(), userDto.getName(), userDto.getEmail());
	}
}
