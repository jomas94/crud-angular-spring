package com.jomario;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.jomario.enums.Category;
import com.jomario.enums.Status;
import com.jomario.model.Course;
import com.jomario.model.Lesson;
import com.jomario.repository.CourseRepository;

@SpringBootApplication
public class CrudSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringApplication.class, args);
	}

	@Bean
	CommandLineRunner initDataBase(CourseRepository courseRepository){

		return args -> {
			courseRepository.deleteAll();

			Course c = new Course();

			c.setName("Angular with Spring");
			c.setCategory(Category.FRONTEND);
			c.setStatus(Status.ACTIVE);

			Lesson l = new Lesson();
			l.setName("Introduccion");
			l.setUrlLesson("asda");
			l.setCourse(c);
			c.getLessons().add(l);

			Lesson l2 = new Lesson();
			l2.setName("RDD");
			l2.setUrlLesson("asda");
			l2.setCourse(c);
			c.getLessons().add(l2);

			courseRepository.save(c);

		};

	}

}
