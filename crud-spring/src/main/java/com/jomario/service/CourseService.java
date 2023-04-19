package com.jomario.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import com.jomario.dto.CourseDTO;
import com.jomario.dto.mapper.CourseMapper;
import com.jomario.exception.RecordNotFoundeException;
import com.jomario.model.Course;
import com.jomario.repository.CourseRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class CourseService {

    private final CourseMapper courseMapper;
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    public List<CourseDTO> list() {
        return courseRepository.findAll()
                .stream()
                .map(courseMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CourseDTO findById(@PathVariable @NotNull @Positive Long id) {

        return courseRepository.findById(id).map(courseMapper::toDTO)
                .orElseThrow(() -> new RecordNotFoundeException(id));
    }

    public CourseDTO create(@Valid @NotNull CourseDTO course) {
        return courseMapper.toDTO(courseRepository.save(courseMapper.toEntity(course)));

    }

    public CourseDTO update(@NotNull @Positive Long id, @Valid @NotNull CourseDTO course) {
        return courseRepository.findById(id)
                .map(courseFounded -> {
                    courseFounded.setName(course.name());
                    courseFounded.setCategory(course.category());
                    return courseMapper.toDTO(courseRepository.save(courseFounded));
                }).orElseThrow(() -> new RecordNotFoundeException(id));

    }

    public boolean delete(@PathVariable @NotNull @Positive Long id) {

        return courseRepository.findById(id)
                .map(courseFounded -> {
                    courseRepository.deleteById(id);
                    return true;
                })
                .orElse(false);
    }

}
