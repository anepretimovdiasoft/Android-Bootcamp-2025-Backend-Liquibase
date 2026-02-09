package com.example.edu.entity;

import lombok.Data;
import lombok.ToString;

import jakarta.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ToString.Exclude
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<Person> people;
}
