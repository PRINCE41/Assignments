-- There is a table “Employee” having two columns – Emp_Name and Office_Locations. 
-- Write a query that will display all office locations and the number of 
-- employees in each of these locations.

-- select emp.officeLocation (select count(emp.Emp_Name) from Employee emp) from Employee emp;

select emp.officeLocation, COUNT(emp.Emp_Name) AS numEmployees from Employee emp GROUP BY emp.officeLocation;