
package OOPs;

public class demo {

    String name;
    int age;

    // Constructor overloading
    demo(String name) {
        this.name = name;
    }

    demo(int age) {
        this.age = age;
        System.out.println("This is parameterized constructor");
    }

    demo(String name, int age) {
        this.name = name;
        this.age = age;
    }

    demo() {
        System.out.println("This is no parameterized constructor");
    }

    // Parent class
    static class animal {
        String name = "animal";

        void sound() {
            System.out.println("animal is moving");
        }
    }

    // Child class
    static class dog extends animal {
        String name = "dog";

        @Override
        void sound() {
            System.out.println("dog is barking");
        }
    }

    public static void main(String[] args) {

        // Constructor overloading
        demo d = new demo(27);
        demo h = new demo("rahul");
        demo a = new demo();

        // Inheritance
        animal x = new animal();
        x.sound();

        animal y = new dog();
        y.sound();

        dog dg = new dog();
        dg.sound();
    }
}
