package com.jomario.controller;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

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
import com.jomario.service.CourseService;



@Validated
@RestController
@RequestMapping("/api/courses")

public class CourseController {
    
    //@Autowired 
    private final CourseService courseService;
    
    public CourseController(CourseRepository courseRepository, CourseService courseService) {

        this.courseService = courseService;
    }

   
    @GetMapping
    public @ResponseBody List<Course> list(){
        
        return courseService.list();
    } 

    @GetMapping("/{id}")
    public Course finfById(@PathVariable("id") @NotNull @Positive Long id){
        
        return courseService.findById(id);
            
    }

    
    //@RequestMapping(method = RequestMethod.POST)
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Course create(@RequestBody  @Valid Course course ){ 
        return courseService.create(course);
        //return ResponseEntity.status(HttpStatus.CREATED)
        //  .body(courseRepository.save(course));

    }
 
    
    @PutMapping("/{id}")
   // @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<Course>  update(@PathVariable Long id,
            @RequestBody @Valid Course course){ 
        return courseService.update(id, course)
        .map(courseFounded -> ResponseEntity.ok().body(courseFounded))
        .orElse(ResponseEntity.notFound().build());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @NotNull @Positive Long id){
        
        if(courseService.delete(id)){
            return ResponseEntity.noContent().<Void>build();
        }
        return ResponseEntity.notFound().build(); 
         
    }
   
    
}
