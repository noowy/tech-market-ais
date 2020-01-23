package com.technolog.techmarketais.domain.hr;

import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class Position
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NaturalId
    @NotBlank
    @NotNull
    private String name;

    private String description;

    @NotNull
    private Integer salary;

    @JsonIgnore
    @OneToOne(mappedBy = "position")
    private Employee emp;
}
