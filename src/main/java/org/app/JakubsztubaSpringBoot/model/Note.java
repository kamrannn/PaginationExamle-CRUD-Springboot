package org.app.JakubsztubaSpringBoot.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "t_notes")
public class Note implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long id;
    private String text;
}
