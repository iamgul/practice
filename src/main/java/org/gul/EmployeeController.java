//package org.gul;
//
//
//import java.util.List;
//
//@RestController
//@RequestMapping("org/employee")
//
//// org/employee/{123}
//// org/employee/124
//
//public class EmployeeController {
//
//
//    @Autowired
//    private EmployeeService employeeService;
//
//
//    @GetMapping("/{id}")
//    public Employee getEmployee(@PathParam("id") int id ) {
//
//        Employee employee = employeeService.getById(id);
//        return employee;
//    }
//
//    @PutMapping("/{id}")
//    public Employee updateEmployee(@RequestBody Employee request ) {
//
//        Employee employee = employeeService.updateEmployee(request);
//        return employee;
//    }
//
//
//
//}
//
