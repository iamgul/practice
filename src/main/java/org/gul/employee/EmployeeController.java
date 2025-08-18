package org.gul.employee;


import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("org/employee")

// org/employee/{123}
// org/employee/124

public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    @GetMapping("/{id}")
    public Employee getEmployee(@PathParam("id") int id) {
        Employee employee = employeeService.getById(id);
        return employee;
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@RequestBody Employee request) {
        Employee employee = employeeService.updateEmployee(request);
        return employee;
    }


}

