package com.luv2code.springboot.cruddemo.dao;

import com.luv2code.springboot.cruddemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {

    // Define field for entitymanager
    private EntityManager entityManager;

    // Set up constructor injection
    @Autowired
    public EmployeeDAOJpaImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    public List<Employee> findAll() {
        // Create a query
        TypedQuery<Employee> theQuery = entityManager.createQuery("from Employee", Employee.class);

        // Execute query and get result list
        List<Employee> employees = theQuery.getResultList();
        // Return the results
        return employees;
    }

    @Override
    public Employee findById(int theId) {
        // Get employee
        Employee employee = entityManager.find(Employee.class, theId);
        // Return employee
        return employee;
    }

    @Override
    public Employee save(Employee theEmployee) {
        // Save employee
        Employee dbEmployee = entityManager.merge(theEmployee);

        // Return employee
        return dbEmployee;
    }

    @Override
    public void deleteById(int theId) {
        // Get employee
        Employee employee = entityManager.find(Employee.class, theId);

        // Remove employee
        entityManager.remove(employee);
    }
}
