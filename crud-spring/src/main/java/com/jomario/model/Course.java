package com.jomario.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;


//@Getter
//@Setter
@Data
@Entity

@SQLDelete(sql = "UPDATE Course SET deleted = true WHERE id = ?")
@Where(clause ="deleted = false ")
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

    @NotBlank
    @NotNull
    @Length(max=10)
    @Pattern(regexp = "Back-end|Front-end")
    @Column(length = 10 , nullable = false )
    private String category;

    @NotNull
    private boolean deleted = false;
 



    
}
