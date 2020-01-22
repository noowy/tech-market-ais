package com.technolog.techmarketais.domain.hr;

import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
public class Vacation
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private Date startDate;
    @NotNull
    private Date endDate;

    private Float vacationPayAmount;

    @JsonIgnore
    @ManyToOne
    private Employee emp;
}
