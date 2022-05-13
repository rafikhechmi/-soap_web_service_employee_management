package com.rafik.config;

import com.rafik.hrms.*;
import com.rafik.repo.Employee;
import com.rafik.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.xml.bind.JAXBElement;
import java.util.List;
import java.util.stream.Collectors;

@Endpoint
public class EndPointConfiguration {
    @Autowired
    EmployeeServiceImpl services;

    @PayloadRoot(namespace = "http://rafik.com/hrms", localPart = "GetEmployeeRequest")
    @ResponsePayload
    public JAXBElement<GetEmployeeResponse> getEmployee(@RequestPayload GetEmployeeRequest request) {
        GetEmployeeResponse response = new GetEmployeeResponse();
//        EmployeeDetails employeeDetails = new EmployeeDetails();
//        employeeDetails.setEmployeeId(request.getEmployeeId());
//        employeeDetails.setEmployeeName("Nancy");
//        employeeDetails.setLocation("tunis");
//        employeeDetails.setPincode(12345);
//        response.setEmployeeDetails(employeeDetails);
        Employee employee = services.getEmployee(request.getEmployeeId());
        response.setEmployeeDetails(mapEmployee(employee));
        return new ObjectFactory().createGetEmployeeResponse(response);
    }
    @PayloadRoot(namespace = "http://rafik.com/hrms", localPart = "GetAllEmployeeRequest")
    @ResponsePayload
    public JAXBElement<GetAllEmployeeResponse> getAlLEmployees(@RequestPayload GetAllEmployeeRequest request){
        GetAllEmployeeResponse response = new GetAllEmployeeResponse();
        List<EmployeeDetails> allEmployeeDetails = services.getAlLEmployees()
                .stream().map(emp -> mapEmployee(emp))
                .collect(Collectors.toList());
        response.getEmployeeDetails().addAll(allEmployeeDetails);

        return new ObjectFactory().createGetAllEmployeeResponse(response);

    }

    @PayloadRoot(namespace = "http://rafik.com/hrms", localPart = "RemoveEmployeeRequest")
    @ResponsePayload
    public JAXBElement<RemoveEmployeeResponse> removeEmployee(@RequestPayload RemoveEmployeeRequest request){
        RemoveEmployeeResponse response = new RemoveEmployeeResponse();
        boolean status=services.removeEmployee (request.getEmployeeId());
        response.setStatus (status);
        return new ObjectFactory().createRemoveEmployeeResponse(response);

    }

    private EmployeeDetails mapEmployee( Employee employee) {
        EmployeeDetails employeeDetails= new EmployeeDetails();
        employeeDetails.setEmployeeId(employee.getEmployeeId());
        employeeDetails.setEmployeeName(employee.getEmployeeName());
        employeeDetails.setLocation(employeeDetails.getLocation());
        employeeDetails.setPincode(employeeDetails.getPincode());
       return employeeDetails ;
    }
}