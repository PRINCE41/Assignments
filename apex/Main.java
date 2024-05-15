package apex;


public class Main {
    public static void main(String[] args) {
        A a1 = new A();
        A b1 = new B();
        //B a2 = new A();
        B b2 = new B();
        a1.run();
        //a2.run();
        b1.run();
        b2.run();
    }
}
class A {
    public void run() {
        System.out.println("A");
    }
}
class B extends A {

    @Override
    public void run() {
        System.out.println("B");
    }
}

// Output:
// A
// A
// B
// B 
