package com.br.nlw.domain.lesson;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(collectionResourceRel = "lesson", path = "lesson")
public interface LessonRepository extends CrudRepository<Lesson, Integer>{

	Lesson findByBio(String bio);
	
}
