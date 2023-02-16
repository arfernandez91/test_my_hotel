package com.myhotel.employeessolution.Models;

import lombok.Data;

import java.util.Date;

@Data
public class Employee {
    private Integer EMPLOYEE_ID;
    private String FIRST_NAME;
    private String LAST_NAME;
    private String EMAIL;
    private String PHONE_NUMBER;
    private Date HIRE_DATE;
    private String JOB_ID;
    private Double SALARY;
    private Double COMMISSION_PCT;
    private Integer MANAGER_ID;
    private Integer DEPARTMENT_ID;

}
