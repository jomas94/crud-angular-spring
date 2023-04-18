package com.jomario.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import com.jomario.exception.RecordNotFoundeException;
import com.jomario.model.Course;
import com.jomario.repository.CourseRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;
import java.util.Optional;
@Validated
@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

     public List<Course> list(){
        
        return courseRepository.findAll();
    }
    

    public Course findById(@PathVariable @NotNull @Positive  Long id){
        
        return courseRepository.findById(id).orElseThrow(() ->  new RecordNotFoundeException(id) );
    }

    public Course create( @Valid Course course ){ 
        return courseRepository.save(course);
        //return ResponseEntity.status(HttpStatus.CREATED)
        //  .body(courseRepository.save(course));

    }

    public Optional<Course> update( @NotNull @Positive Long id, @Valid Course course){ 
        return courseRepository.findById(id)
        .map(courseFounded -> {
            courseFounded.setName(course.getName());
            courseFounded.setCategory(course.getCategory());
            return courseRepository.save(courseFounded);
        });
       

    }

    public boolean delete(@PathVariable @NotNull @Positive Long id){
        
        return courseRepository.findById(id)
        .map(courseFounded -> {
            courseRepository.deleteById(id);
            return true;
        })
        .orElse(false); 
    }
    
}
