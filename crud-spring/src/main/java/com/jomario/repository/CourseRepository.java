package com.jomario.repository;

import com.jomario.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;




public interface CourseRepository extends JpaRepository<Course, Long> {


    
}
