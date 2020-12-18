package com.br.nlw.domain.lesson;

import javax.persistence.EntityNotFoundException;
import javax.persistence.PrePersist;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.br.nlw.domain.user.AppUser;
import com.br.nlw.domain.user.AppUserRepository;

@Component
public class LessonListener {

	private final AppUserRepository appUserRepository;
	
	public LessonListener(AppUserRepository appUserRepository) {
		this.appUserRepository = appUserRepository;
	}

	@PrePersist
	public void onPrePersistHandler(Lesson lesson) {
		if(lesson.getAppUser() == null) {
			String userName = SecurityContextHolder.getContext().getAuthentication().getName();
			AppUser appUser = appUserRepository.findByEmail(userName);
			
			if(appUser == null) {
				throw new EntityNotFoundException("Usuário " + userName + " não foi encontrado");
			}
			
			lesson.setAppUser(appUser);
		}
	}
	
//	@Autowired
//	public void init(AppUserRepository appUserRepository) {
//		LessonListener.appUserRepository = appUserRepository;
//	}
}
