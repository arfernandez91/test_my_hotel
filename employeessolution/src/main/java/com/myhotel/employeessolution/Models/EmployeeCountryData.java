package com.myhotel.employeessolution.Models;

import lombok.Data;

@Data
public class EmployeeCountryData {
    private String COUNTRY_NAME;
    private Integer CANT_EMPL;
    private Double PROM_SALARY;
    private Double MAX_SALARY;
    private Double MIN_SALARY;
    private Integer PROM_YEARS;
}
