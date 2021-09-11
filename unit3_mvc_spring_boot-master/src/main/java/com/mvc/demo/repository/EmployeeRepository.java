package com.mvc.demo.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import com.mvc.demo.model.EmployeeEntity;



@Repository
public interface EmployeeRepository extends CrudRepository<EmployeeEntity, Long>{

}
