package com.jomario;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.jomario.model.Course;
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
			c.setCategory("front-end");

			courseRepository.save(c);

		};

	}

}
