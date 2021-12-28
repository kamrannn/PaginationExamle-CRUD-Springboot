package org.app.JakubsztubaSpringBoot.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "t_categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = false, unique = true)
    private String name;
}
