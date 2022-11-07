package com.jomario.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jomario.model.Course;
import com.jomario.repository.CourseRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/courses")
@AllArgsConstructor
public class CourseController {
    
    //@Autowired
    private final CourseRepository courseRepository;

    //@RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public @ResponseBody List<Course> list(){
        
        return courseRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> finfById(@PathVariable Long id){
        
        return courseRepository.findById(id)
            .map(course -> ResponseEntity.ok().body(course))
            .orElse(ResponseEntity.notFound().build());
    }


    
    //@RequestMapping(method = RequestMethod.POST)
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Course create(@PathVariable Long id ){ 
        return courseRepository.save(course);
        //return ResponseEntity.status(HttpStatus.CREATED)
        //  .body(courseRepository.save(course));

    }

    
    @PutMapping
    @ResponseStatus(code = HttpStatus.OK)
    public Course update(@RequestBody Course course ){ 
        return   courseRepository.save(course);
        //return ResponseEntity.status(HttpStatus.CREATED)
        //  .body(courseRepository.save(course));

    }
   
    
}
