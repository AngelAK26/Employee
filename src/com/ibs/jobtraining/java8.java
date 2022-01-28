package com.ibs.jobtraining;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

class Java8 {
public static void main(String[] args) {

List<Employee> empList= Arrays.asList(

new Employee("1001", "Alice",102,"active","30000"),
new Employee("1002", "Raj",101,"inactive","25000"),
new Employee("1003", "Jewel",102,"active","10000"),
new Employee("1004", "Mary",103,"inactive","12000"),
new Employee("1005", "Rose",104,"inactive","22000")

);

//program to print employee details in each department

Map<Integer, List<Employee>>empListBasedOnDept = empList.stream().collect(Collectors.groupingBy(Employee::getDepId));
empListBasedOnDept.entrySet().forEach( entry ->{
System.out.println(entry.getKey() +"---" + entry.getValue());
});

//program to print employee count working in each department

Map<Object, Long>empCountDept =empList.stream().collect(Collectors.groupingBy(Employee::getDepId, Collectors.counting()));
empCountDept.entrySet().forEach( entry -> {
System.out.println("dep" +entry.getKey() + "--" + entry.getValue());
});

//program to print active and inactive employees in the given collection

long activeEmpCount = empList.stream().filter(e -> "active".equals(e.getStatus())).count();
long inactiveEmpCount = empList.stream().filter(e -> "inactive".equals(e.getStatus())).count();

//program to print Max/Min salary from the given collection

System.out.println("active emp count" + activeEmpCount);
System.out.println("inactive emp count" + inactiveEmpCount);

//program to print max salary of an employee from each department

Optional<Employee> emp1= empList.stream().max(Comparator.comparing(Employee::getSalary));
Optional<Employee> emp2= empList.stream().min(Comparator.comparing(Employee::getSalary));
System.out.println(emp1);
System.out.println(emp2);


Map<Object, Optional<Employee>> topSalaryEmpDepWise=empList.stream().collect(Collectors.groupingBy(Employee::getDepId,Collectors.reducing(BinaryOperator.maxBy(Comparator.comparing(Employee::getSalary)))));

topSalaryEmpDepWise.entrySet().forEach(entry ->{
System.out.println("dept" + entry.getKey() + "top emp" + entry.getValue());
}
);
}
}

