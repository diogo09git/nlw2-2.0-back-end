package com.br.nlw.domain.user;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(collectionResourceRel = "user", path = "user")
public interface AppUserRepository extends CrudRepository<AppUser, Integer>{
	
	AppUser findByEmail(String email);
	
//	@RestResource(exported = false)
//	Iterable<AppUser> findAll();
//	
//	@RestResource(exported = false)
//	Optional<AppUser> findById(Integer id);
	
}
