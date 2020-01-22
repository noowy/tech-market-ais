package com.technolog.techmarketais.domain.hr;

import lombok.Data;

import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
@Data
public class Person
{

    protected String firstName;

    protected String lastName;

    protected Date birthDate;

}
