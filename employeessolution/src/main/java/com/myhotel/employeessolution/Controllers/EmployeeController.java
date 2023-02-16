package com.myhotel.employeessolution.Controllers;

import com.myhotel.employeessolution.Dao.EmployeeDao;
import com.myhotel.employeessolution.Models.Employee;
import com.myhotel.employeessolution.Models.EmployeeCountryData;
import com.myhotel.employeessolution.Models.EmployeeDataDepartment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/employee")
public class EmployeeController {

    @Autowired
    private EmployeeDao employeeDao;

    @GetMapping(value = "/query_i_1", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> employeesUnder3500Salary(){
        Map<String, Object> data = new HashMap<>();
        try{
            Integer employee_cant = employeeDao.employeesUnder3500Salary();
            data.put("result",employee_cant);
            return  ResponseEntity.ok(data);
        }catch (Exception e){
            data.put("error",e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(data);
        }
    }
    @GetMapping(value = "/query_i_2", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> employeesBetween3500And8000Salary(){
        Map<String, Object> data = new HashMap<>();
        try{
            Integer employee_cant = employeeDao.employeesBetween3500And8000Salary();
            data.put("result",employee_cant);
            return  ResponseEntity.ok(data);
        }catch (Exception e){
            data.put("error",e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(data);
        }
    }
    @GetMapping(value = "/query_i_3", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> employeesAbove8000Salary(){
        Map<String, Object> data = new HashMap<>();
        try{
            Integer employee_cant = employeeDao.employeesAbove8000Salary();
            data.put("result",employee_cant);
            return  ResponseEntity.ok(data);
        }catch (Exception e){
            data.put("error",e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(data);
        }
    }

    @GetMapping(value = "/query_ii_1", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> employeesUnder3500SalaryForDepartment(){
        Map<String, Object> data = new HashMap<>();
        try{
            List<EmployeeDataDepartment> employeeDataDepartments = employeeDao.employeesUnder3500SalaryForDepartment();
            data.put("result",employeeDataDepartments);
            return  ResponseEntity.ok(data);
        }catch (Exception e){
            data.put("error",e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(data);
        }
    }
    @GetMapping(value = "/query_ii_2", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> employeesBetween3500And8000SalaryForDepartment(){
        Map<String, Object> data = new HashMap<>();
        try{
            List<EmployeeDataDepartment> employeeDataDepartments = employeeDao.employeesBetween3500And8000SalaryForDepartment();
            data.put("result",employeeDataDepartments);
            return  ResponseEntity.ok(data);
        }catch (Exception e){
            data.put("error",e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(data);
        }
    }

    @GetMapping(value = "/query_ii_3", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> employeesAbove8000SalaryForDepartment(){
        Map<String, Object> data = new HashMap<>();
        try{
            List<EmployeeDataDepartment> employeeDataDepartments = employeeDao.employeesAbove8000SalaryForDepartment();
            data.put("result",employeeDataDepartments);
            return  ResponseEntity.ok(data);
        }catch (Exception e){
            data.put("error",e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(data);
        }
    }

    @GetMapping(value = "/query_iii", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> maxSalaryForDepartmentEmployee(){
        Map<String, Object> data = new HashMap<>();
        try{
            List<Employee> employees = employeeDao.maxSalaryForDepartmentEmployee();
            data.put("result",employees);
            return  ResponseEntity.ok(data);
        }catch (Exception e){
            data.put("error",e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(data);
        }
    }

    @GetMapping(value = "/query_iv", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> managerMoreThan15YearData(){
        Map<String, Object> data = new HashMap<>();
        try{
            List<Employee> employees = employeeDao.managerMoreThan15YearData();
            data.put("result",employees);
            return  ResponseEntity.ok(data);
        }catch (Exception e){
            data.put("error",e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(data);
        }
    }
    @GetMapping(value = "/query_v", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> avgSalaryForDerpartmentWithMoreThan15Employees(){
        Map<String, Object> data = new HashMap<>();
        try{
            List<Double> avg_salary = employeeDao.avgSalaryForDerpartmentWithMoreThan15Employees();
            data.put("result",avg_salary);
            return  ResponseEntity.ok(data);
        }catch (Exception e){
            data.put("error",e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(data);
        }
    }
    @GetMapping(value = "/query_vi", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> dataInformationGroupByCountry(){
        Map<String, Object> data = new HashMap<>();
        try{
            List<EmployeeCountryData> employeeCountryData = employeeDao.dataInformationGroupByCountry();
            data.put("result",employeeCountryData);
            return  ResponseEntity.ok(data);
        }catch (Exception e){
            data.put("error",e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(data);
        }
    }


}
