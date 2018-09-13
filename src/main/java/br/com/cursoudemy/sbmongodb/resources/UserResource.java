package br.com.cursoudemy.sbmongodb.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.cursoudemy.sbmongodb.domain.User;
import br.com.cursoudemy.sbmongodb.dto.UserDTO;
import br.com.cursoudemy.sbmongodb.services.UserService;

@RestController
@RequestMapping(value="/users")
public class UserResource {

	@Autowired
	private UserService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> findAll() {
		List<User> users = service.findAll();
		List<UserDTO> usersDto = users.stream()
				.map( user -> new UserDTO(user) ).collect(Collectors.toList());
		return ResponseEntity.ok().body(usersDto);
	}
	
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public ResponseEntity<UserDTO> findById(@PathVariable String userId) {
		User user = service.findById(userId);
		return ResponseEntity.ok().body(new UserDTO(user));
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody UserDTO userDto) {
		User user = service.fromDTO(userDto);
		user = service.insert(user);
		// coloca no response um cabeçalho com a url do novo recurso criado
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{userId}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).build(); // response vazio com código 201 - created
	}
	
	@RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable String userId) {
		service.delete(userId);
		return ResponseEntity.noContent().build(); // retorna código 204
	}
	
	@RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable String userId, @RequestBody UserDTO userDto) {
		User user = service.fromDTO(userDto);
		user.setId(userId);
		service.update(user);
		return ResponseEntity.noContent().build();
	}}
