package com.jomario.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jomario.model.Course;
import com.jomario.repository.CourseRepository;

import lombok.AllArgsConstructor;

@Validated
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
    public ResponseEntity<Course> finfById(@PathVariable("id") @NotNull @Positive Long id){
        
        return courseRepository.findById(id)
            .map(course -> ResponseEntity.ok().body(course))
            .orElse(ResponseEntity.notFound().build());
    }


    
    //@RequestMapping(method = RequestMethod.POST)
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Course create(@RequestBody  @Valid Course course ){ 
        return courseRepository.save(course);
        //return ResponseEntity.status(HttpStatus.CREATED)
        //  .body(courseRepository.save(course));

    }

    
    @PutMapping("/{id}")
   // @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<Course>  update(@PathVariable Long id,
            @RequestBody @Valid Course course){ 
        return courseRepository.findById(id)
        .map(courseFounded -> {
            courseFounded.setName(course.getName());
            courseFounded.setCategory(course.getCategory());
            Course updated = courseRepository.save(courseFounded);
            return ResponseEntity.ok().body(updated);
        })
        .orElse(ResponseEntity.notFound().build());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @NotNull @Positive Long id){
        
        return courseRepository.findById(id)
        .map(courseFounded -> {
            courseRepository.deleteById(id);
            return ResponseEntity.noContent().<Void>build();
        })
        .orElse(ResponseEntity.notFound().build()); 
    }
   
    
}
