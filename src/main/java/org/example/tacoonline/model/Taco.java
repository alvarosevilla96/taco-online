package org.example.tacoonline.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;


import java.util.Date;
import java.util.List;

@Data
@Entity
public class Taco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date createdAt;
    @Size(min = 5, max = 50, message="El nombre debe tener al menos 5 caracteres")
    private String name;
    @ManyToMany(targetEntity = Ingredient.class)
    private List<Ingredient> ingredients;

    @PrePersist
    void createdAt(){
        this.createdAt = new Date();
    }
}
