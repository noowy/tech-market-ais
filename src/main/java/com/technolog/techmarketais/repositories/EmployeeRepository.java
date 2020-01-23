package com.technolog.techmarketais.repositories;

import com.technolog.techmarketais.domain.hr.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long>
{
    List<Employee> findEmployeeByLastName(String lastName);
}
