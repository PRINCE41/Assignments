import java.util.Set;
import java.util.TreeSet;

public class Program {
    public static void main(String[] args) {
        Set<Person> set = new TreeSet<>();
        Person person1 = new Person("1-Abcdef", 20);
        Person person2 = new Person("2-Abcdef", 21);
        Person person3 = new Person("3-Abcdef", 22);
        Person person4 = new Person("4-Abcdef", 20);

        set.add(person1);
        set.add(person2);
        set.add(person3);
        set.add(person4);
        System.out.println(set);
    }

    private static class Person implements Comparable<Person> {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return this.name;
        }


        public int getAge() {
            return this.age;
        }

        @Override
        public int compareTo(Person other) {
            return Integer.compare(this.age, other.age);
        }

        @Override
        public String toString() {
            return this.getName() + ":" + this.getAge();
        }
    }
}
