package com.technolog.techmarketais.repositories;

import com.technolog.techmarketais.domain.hr.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long>
{
    Employee findEmployeeByName(String name);
}
