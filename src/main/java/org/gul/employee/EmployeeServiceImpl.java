package org.gul.employee;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee getById(int id) {
        Optional employee = employeeRepository.findById(id);
        return (Employee) employee.get();
    }

    @Override
    public Employee updateEmployee(Employee requestBody) {
        Employee employee = (Employee) employeeRepository.findById(requestBody.getId()).get();
        employee.setName(requestBody.getName());
        employee.setAge(requestBody.getAge());
        return (Employee) employeeRepository.save(employee);
    }
}
