package br.com.cursoudemy.sbmongodb.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.cursoudemy.sbmongodb.domain.Post;
import br.com.cursoudemy.sbmongodb.services.PostService;

@RestController
@RequestMapping(value="/posts")
public class PostResource {

	@Autowired
	private PostService service;
	
	@RequestMapping(value = "/{postId}", method = RequestMethod.GET)
	public ResponseEntity<Post> findById(@PathVariable String postId) {
		Post post = service.findById(postId);
		return ResponseEntity.ok().body(post);
	}
}
