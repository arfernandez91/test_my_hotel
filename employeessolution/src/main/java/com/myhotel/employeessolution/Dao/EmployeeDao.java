package com.myhotel.employeessolution.Dao;

import com.myhotel.employeessolution.Models.Employee;
import com.myhotel.employeessolution.Models.EmployeeCountryData;
import com.myhotel.employeessolution.Models.EmployeeDataDepartment;

import java.util.List;

public interface EmployeeDao {
    Integer employeesUnder3500Salary();
    Integer employeesBetween3500And8000Salary();
    Integer employeesAbove8000Salary();
    List<EmployeeDataDepartment> employeesUnder3500SalaryForDepartment();
    List<EmployeeDataDepartment> employeesBetween3500And8000SalaryForDepartment();
    List<EmployeeDataDepartment> employeesAbove8000SalaryForDepartment();
    List<Employee> maxSalaryForDepartmentEmployee();
    List<Employee> managerMoreThan15YearData();
    List<Double> avgSalaryForDerpartmentWithMoreThan15Employees();
    List<EmployeeCountryData> dataInformationGroupByCountry();

}
