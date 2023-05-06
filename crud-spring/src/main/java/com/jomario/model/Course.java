package com.jomario.model;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jomario.enums.Category;
import com.jomario.enums.Status;
import com.jomario.enums.converters.CategoryConverter;
import com.jomario.enums.converters.StatusConverter;

import lombok.Data;


//@Getter
//@Setter
@Data
@Entity 
@SQLDelete(sql = "UPDATE Course SET status = active WHERE id = ?")
@Where(clause ="status = 'Active' ")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_id")
    private Long id;
    
    @NotBlank
    @NotNull
    @Length(min=1, max=100)
    @Column(length = 100, nullable = false )
    private String name;

    // @NotBlank
    @NotNull
    // @Length(max=10)
    // @Pattern(regexp = "Back-end|Front-end")
    @Column(nullable = false)
    @Convert(converter = CategoryConverter.class)
    private Category category;

    @NotNull
    @Column(length = 10, nullable = false)
    @Convert(converter = StatusConverter.class)
    private Status status = Status.ACTIVE;



    
}
