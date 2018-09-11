package br.com.cursoudemy.sbmongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.cursoudemy.sbmongodb.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
