package d5_exceptions_java8;

import java.util.*;

public class Employee {
    final private String name;
    final private Integer salary;

    public Employee(String name, Integer salary) {
        this.name = name;
        this.salary = salary;
    }
    @Override
    public boolean equals(Object obj) { // use default equals, which is referential equality
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Employee emp = (Employee) obj;
        return (name == emp.name) && (salary == emp.salary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.salary);
    }

    public static void main(String[] args){
        List<Employee> employeeList = Arrays.asList(
                new Employee("emp1", 10000),
                new Employee("emp2", 20000),
                new Employee("emp3", 30000),
                new Employee("emp4", 40000),
                new Employee("emp5", 50000),
                new Employee("emp6", 60000),
                new Employee("emp7", 70000),
                new Employee("emp8", 80000),
                new Employee("emp9", 90000),
                new Employee("emp10", 100000)
        );

//        Filter for > 80000 salary, print names
//        List<Employee> richEmployees = employeeList.stream().filter(emp -> emp.salary > 80000).collect(Collectors.toList());
//        richEmployees.forEach(emp -> System.out.printf("Name:%s, Salary: %s%n", emp.name, emp.salary));

//        Get average salary
//        int avgSalary2 = employeeList.stream().map(e -> e.salary).reduce(0, Integer::sum) / employeeList.size();
//        System.out.println(avgSalary2);

//        OptionalDouble avgSalary = employeeList.stream().mapToInt(e -> e.salary).average();
//        System.out.printf("Average salary: %s%n", avgSalary);

//        Map name to employees
//        HashMap<String, Employee> empMap = new HashMap<String, Employee>();
//        employeeList.forEach(e -> empMap.put(e.name, e));
//        System.out.println(empMap);

//        Find employees whose name starts with X, if exists print name else no such employee exists
//        Optional<Employee> empX = employeeList.stream().filter(e -> e.name.startsWith("X")).findAny();
//        if (empX.isPresent()) {
//            Employee emp = empX.get();
//            System.out.println(emp.name);
//        } else {
//            System.out.println("No such employee exists");
//        }


//        Create a String that contains the name of every single employee in the list concatenated together.
//        String allNames = employeeList.stream().map(e -> e.name).reduce(0, (s, e) -> s + e.name);
//        String allNames = employeeList.stream().map(e -> e.name).collect(Collectors.joining(","));
//        System.out.println(allNames);

    }

}
