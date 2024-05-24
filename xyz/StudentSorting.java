// List of students with name, course and age. Sort the list with name, course and age

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StudentSorting{
    public static void main(String[] args) {

        Student s1 = new Student("Abc", "Java", 20);
        Student s2 = new Student("xyz", "BCA", 25);
        Student s3 = new Student("mno", "Java", 30);
        Student s4 = new Student("ijk", "Java", 35);

        List<Student> list = new ArrayList<>();
        list.add(s1);
        list.add(s2);
        list.add(s3);
        list.add(s4);

        // Student by course
        Map<String, List<Student>> studentsByCourse = list.stream()
                .collect(Collectors.groupingBy(Student::getCourse));

                studentsByCourse.forEach((course, students) -> {
                    System.out.println("Course: " + course);
                    students.forEach(System.out::println);
                    System.out.println("+++++++++++++++++++++++++++++++++++");
                });



        // Sort those having course as java   
        List<Student> studentList = list.stream().filter(x -> x.getCourse().equals("Java")).collect(Collectors.toList());
        System.out.println(studentList);

        // // sort by age
       list.stream().sorted(Comparator.comparing(Student::getAge)).toList().forEach(System.out::println);


       // Another way after implementing comparable: Sorting students by age using their natural ordering
       list.sort(Student::compareTo);
       System.out.println("\nSorted students by age using Comparable compareTo over ridded:");
       list.forEach(System.out::println);
        
    }

    private static class Student implements Comparable<Student> {
        String name;
        String course;
        int age;

        public Student(String name, String course, int age){
            this.name = name;
            this.course = course;
            this.age = age;
        }

        public String getName(){
            return this.name;
        }

        public String getCourse(){
            return this.course;
        }

        public int getAge(){
            return this.age;
        }

        @Override
        public String toString(){
            return "Student:[" + name + ":" + age + ":" + course + "]";
        }

        @Override
        public int compareTo(Student other) {
            return Integer.compare(this.age, other.age);
        }


    }


}