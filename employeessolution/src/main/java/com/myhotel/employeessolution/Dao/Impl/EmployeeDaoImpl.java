package com.myhotel.employeessolution.Dao.Impl;

import com.myhotel.employeessolution.Dao.EmployeeDao;
import com.myhotel.employeessolution.Models.Employee;
import com.myhotel.employeessolution.Models.EmployeeCountryData;
import com.myhotel.employeessolution.Models.EmployeeDataDepartment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeDaoImpl implements EmployeeDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public Integer employeesUnder3500Salary() {
        String sql = "SELECT count(*) as CANT from employees e where e.SALARY < 3500;";
        Integer count = jdbcTemplate.queryForObject(
                sql, new Object[] {}, Integer.class);

        return count;
    }

    @Override
    public Integer employeesBetween3500And8000Salary() {
        String sql = "SELECT count(*) from employees e where e.SALARY >= 3500 and e.SALARY < 8000;";
        Integer count = jdbcTemplate.queryForObject(
                sql,  new Object[] {}, Integer.class);

        return count;
    }

    @Override
    public Integer employeesAbove8000Salary() {
        String sql = "SELECT count(*) from employees e where e.SALARY >= 8000;";
        Integer count =  jdbcTemplate.queryForObject(
                sql, new Object[] {}, Integer.class);

        return count;
    }

    @Override
    public List<EmployeeDataDepartment> employeesUnder3500SalaryForDepartment() {
        String sql = "SELECT count(*) as COUNT,d.DEPARTMENT_NAME from employees e join departments d on e.DEPARTMENT_ID = d.DEPARTMENT_ID where e.SALARY < 3500 group by d.DEPARTMENT_ID;";
        List<EmployeeDataDepartment> employeeDataDepartments = jdbcTemplate.query(
                sql, new BeanPropertyRowMapper(EmployeeDataDepartment.class));

        return employeeDataDepartments;
    }

    @Override
    public List<EmployeeDataDepartment> employeesBetween3500And8000SalaryForDepartment() {
        String sql = "SELECT count(*) as COUNT,d.DEPARTMENT_NAME from employees e join departments d on e.DEPARTMENT_ID = d.DEPARTMENT_ID where e.SALARY >= 3500 and e.SALARY < 8000 group by d.DEPARTMENT_ID;";
        List<EmployeeDataDepartment> employeeDataDepartments = jdbcTemplate.query(
                sql, new BeanPropertyRowMapper(EmployeeDataDepartment.class));

        return employeeDataDepartments;
    }

    @Override
    public List<EmployeeDataDepartment> employeesAbove8000SalaryForDepartment() {
        String sql = "SELECT count(*) as COUNT,d.DEPARTMENT_NAME from employees e join departments d on e.DEPARTMENT_ID = d.DEPARTMENT_ID where e.SALARY >= 8000 group by d.DEPARTMENT_ID;";
        List<EmployeeDataDepartment> employeeDataDepartments = jdbcTemplate.query(
                sql, new BeanPropertyRowMapper(EmployeeDataDepartment.class));

        return employeeDataDepartments;
    }

    @Override
    public List<Employee> maxSalaryForDepartmentEmployee() {

        String sql = "SELECT e.* from employees e join departments d on e.DEPARTMENT_ID = d.DEPARTMENT_ID where e.SALARY = (select max(em.SALARY) from employees em where e.DEPARTMENT_ID = em.DEPARTMENT_ID group by e.DEPARTMENT_ID) group by d.DEPARTMENT_ID order by e.SALARY desc;";
        List<Employee> employees = jdbcTemplate.query(
                sql, new BeanPropertyRowMapper(Employee.class));

        return employees;
    }

    @Override
    public List<Employee> managerMoreThan15YearData() {
        String sql = "SELECT e.* from employees e join departments d on e.EMPLOYEE_ID = d.MANAGER_ID and e.EMPLOYEE_ID not in (SELECT EMPLOYEE_ID FROM job_history) AND (SELECT timestampdiff(year,  em.HIRE_DATE, now()) from employees em where e.EMPLOYEE_ID = em.EMPLOYEE_ID) > 15;";
        List<Employee> employees = jdbcTemplate.query(
                sql, new BeanPropertyRowMapper(Employee.class));

        return employees;
    }

    @Override
    public List<Double> avgSalaryForDerpartmentWithMoreThan15Employees() {
        String sql = "SELECT avg(e.SALARY) from employees e join departments d on e.DEPARTMENT_ID = d.DEPARTMENT_ID group by e.DEPARTMENT_ID having count(*) > 10;";
        List<Double> avgs = jdbcTemplate.queryForList(
                sql, Double.class);

        return avgs;
    }

    @Override
    public List<EmployeeCountryData> dataInformationGroupByCountry() {
        String sql = "SELECT c.COUNTRY_NAME, count(e.EMPLOYEE_ID) as CANT_EMPL, avg(e.SALARY) as PROM_SALARY, max(e.SALARY) as MAX_SALARY, min(e.SALARY) as MIN_SALARY, cast(avg(timestampdiff(year,  e.HIRE_DATE, now())) as signed) PROM_YEARS from employees e join departments d on e.DEPARTMENT_ID = d.DEPARTMENT_ID join locations l on d.LOCATION_ID = l.LOCATION_ID join countries c on c.COUNTRY_ID = l.COUNTRY_ID  group by c.COUNTRY_ID;";
        List<EmployeeCountryData> employeeCountryData = jdbcTemplate.query(
                sql, new BeanPropertyRowMapper(EmployeeCountryData.class));

        return employeeCountryData;
    }
}
