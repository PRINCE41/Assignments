
import java.util.*;

public class CountEmp{
    public static void main(String[] args) {
        List<Employee> list = new ArrayList<>();
        Employee employee1 = new Employee();
        employee1.setName("Abc def");
        employee1.setSalary(20_000);
        Employee employee2 = new Employee();
        employee2.setName("Xyz mno");
        employee2.setSalary(10_000);
        Employee employee3 = new Employee();
        employee3.setName("Pqr mno");
        employee3.setSalary(5_000);
        list.add(employee1);
        list.add(employee2);
        list.add(employee3);

        list.stream().filter(x -> x.getSalary() < 20_000).forEach(m -> {
            System.out.println(m.toString());
        });


        
    }

    private static class Employee{
        private String name;
        private long salary;

        public void setName(String name){
            this.name = name;
        }
        public String getName(){
            return this.name;
        }
        public void setSalary(long salary){
            this.salary = salary;
        }
        public long getSalary(){
            return this.salary;
        }
        @Override
        public String toString(){
            return "Name:" + this.getName() + " | Salary:" + this.getSalary();
        }
    }
}