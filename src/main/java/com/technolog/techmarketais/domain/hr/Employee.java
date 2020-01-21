package com.technolog.techmarketais.domain.hr;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Entity
public class Employee extends Person
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "position_id", referencedColumnName = "name")
    private Position position;

    private Boolean isActive;
    private Date hireDate;

    private Integer bonus;

    private Short vacationDaysLeft;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "pending_vacations", referencedColumnName = "id")
    private Set<Vacation> pendingVacations;

}
