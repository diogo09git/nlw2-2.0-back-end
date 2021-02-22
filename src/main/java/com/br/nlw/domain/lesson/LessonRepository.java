package com.br.nlw.domain.lesson;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(collectionResourceRel = "lesson", path = "lesson")
public interface LessonRepository extends CrudRepository<Lesson, Integer>{

	Lesson findByBio(String bio);
	
//	@Query("SELECT l, s FROM Lesson l JOIN Schedule s ON l.theme = ?1 AND s.weekDay = ?2")
//	List<Lesson> findByThemeAndWeekDay(String theme, String weekDay);
	
	List<Lesson> findByTheme(String theme);
	
	
//	@Query("SELECT i FROM Lesson i WHERE i.theme = ?1 and i.weekDay = ?2")
//	List<Lesson> findByThemeAndWeekDay(String theme, String day);
	
}
