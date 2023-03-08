package com.travel.blog.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "continents")
public class Continents {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String continent;
    private String image;
    private String description;
    @OneToMany(mappedBy = "continent", cascade = CascadeType.ALL)
    private List<Travel> travels;


}
