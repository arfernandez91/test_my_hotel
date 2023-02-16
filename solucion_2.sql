-- i.
SELECT count(*) from employees e where e.SALARY < 3500;
SELECT count(*) from employees e where e.SALARY >= 3500 and e.SALARY < 8000; 
SELECT count(*) from employees e where e.SALARY >= 8000;
-- ii.
SELECT count(*),d.DEPARTMENT_NAME from employees e join departments d on e.DEPARTMENT_ID = d.DEPARTMENT_ID where e.SALARY < 3500 group by d.DEPARTMENT_ID;
SELECT count(*),d.DEPARTMENT_NAME from employees e join departments d on e.DEPARTMENT_ID = d.DEPARTMENT_ID where e.SALARY >= 3500 and e.SALARY < 8000 group by d.DEPARTMENT_ID; 
SELECT count(*),d.DEPARTMENT_NAME from employees e join departments d on e.DEPARTMENT_ID = d.DEPARTMENT_ID where e.SALARY >= 8000 group by d.DEPARTMENT_ID;
-- iii.
SELECT e.* from employees e join departments d on e.DEPARTMENT_ID = d.DEPARTMENT_ID where e.SALARY = (select max(em.SALARY) from employees em where e.DEPARTMENT_ID = em.DEPARTMENT_ID group by e.DEPARTMENT_ID) group by d.DEPARTMENT_ID order by e.SALARY desc;
-- iv.
SELECT e.* from employees e join departments d on e.EMPLOYEE_ID = d.MANAGER_ID and e.EMPLOYEE_ID not in (SELECT EMPLOYEE_ID FROM job_history) AND (SELECT timestampdiff(year,  em.HIRE_DATE, now()) from employees em where e.EMPLOYEE_ID = em.EMPLOYEE_ID) > 15;
-- v.
SELECT avg(e.SALARY) from employees e join departments d on e.DEPARTMENT_ID = d.DEPARTMENT_ID group by e.DEPARTMENT_ID having count(*) > 10;
-- vi.
SELECT c.COUNTRY_NAME, count(e.EMPLOYEE_ID) as cant_empl, avg(e.SALARY) as prom_salary, max(e.SALARY) as max_salary, min(e.SALARY) as min_salary, cast(avg(timestampdiff(year,  e.HIRE_DATE, now())) as signed) prom_years from employees e join departments d on e.DEPARTMENT_ID = d.DEPARTMENT_ID join locations l on d.LOCATION_ID = l.LOCATION_ID join countries c on c.COUNTRY_ID = l.COUNTRY_ID  group by c.COUNTRY_ID;
