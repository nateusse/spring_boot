package com.mvc.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.mvc.demo.model.EmployeeEntity;
import com.mvc.demo.repository.*;
import com.mvc.exception.RecordNotFoundException;
 
@Service
public class EmployeeService {
     
    @Autowired
    EmployeeRepository repository;
     
    public List<EmployeeEntity> getAllEmployees()
    {
        List<EmployeeEntity> result = (List<EmployeeEntity>) repository.findAll();
         
        if(result.size() > 0) {
            return result;
        } else {
            return new ArrayList<EmployeeEntity>();
        }
    }
     
    public EmployeeEntity getEmployeeById(Long id) throws RecordNotFoundException 
    {
        Optional<EmployeeEntity> employee = repository.findById(id);
         
        if(employee.isPresent()) {
            return employee.get();
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
    }
     
    public EmployeeEntity createOrUpdateEmployee(EmployeeEntity entity) 
    {
        if(entity.getId()  == null) 
        {
            entity = repository.save(entity);
             
            return entity;
        } 
        else
        {
            Optional<EmployeeEntity> employee = repository.findById(entity.getId());
             
            if(employee.isPresent()) 
            {
                EmployeeEntity newEntity = employee.get();
                newEntity.setEmail(entity.getEmail());
                newEntity.setFirstname(entity.getFirstname());
                newEntity.setFirstname(entity.getFirstname());
 
                newEntity = repository.save(newEntity);
                 
                return newEntity;
            } else {
                entity = repository.save(entity);
                 
                return entity;
            }
        }
    } 
     
    public void deleteEmployeeById(Long id) throws RecordNotFoundException 
    {
        Optional<EmployeeEntity> employee = repository.findById(id);
         
        if(employee.isPresent()) 
        {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
    } 
}