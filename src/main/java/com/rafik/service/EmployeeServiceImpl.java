package com.rafik.service;

import com.rafik.repo.Employee;
import com.rafik.repo.OfflineRepository;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
@Component
public class EmployeeServiceImpl {
    List<Employee> list0fEmployees = OfflineRepository.getEmployees();
public Employee getEmployee(int employeeId) {
        Optional<Employee> employee = list0fEmployees.stream().
                filter(emp -> emp.getEmployeeId() == employeeId)
                .findFirst();
        return employee.get();
    }
    public List<Employee> getAlLEmployees (){
        return list0fEmployees;
    }
    public Employee RemoveEmployee (){

    return null;
    }

    public boolean removeEmployee(int employeeId) {
        Iterator<Employee> iterator=list0fEmployees.iterator ();
        while (iterator.hasNext()){
            Employee emp=iterator.next();
            if(employeeId == emp.getEmployeeId()){
                iterator.remove();
                return true;
    }
        }
        return false;
    }
}
