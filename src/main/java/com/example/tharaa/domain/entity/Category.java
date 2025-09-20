package com.example.tharaa.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String name;

    @OneToMany(mappedBy = "parentCategory", fetch = FetchType.EAGER)
    @ToString.Exclude
    @JsonManagedReference
    private Set<Category> subCategories = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "parent_id")
    @JsonBackReference
    private Category parentCategory;

}
