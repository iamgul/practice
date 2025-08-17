package org.gul.employee;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {

    Employee getById(int id);
    Employee updateEmployee(Employee employee);
}
