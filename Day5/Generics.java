
package Day5;

public class Generics<T, M> {

    // Generic class
    T a;
    M b;

    Generics(T a, M b) {
        this.a = a;
        this.b = b;
    }

    // Generic method
    public <E> void m1(E a, E b) {
        System.out.println(a);
        System.out.println(b);
    }

    public static void main(String[] args) {

        Generics<Integer, String> obj =
                new Generics<>(10, "Hello");

        System.out.println(obj.a);
        System.out.println(obj.b);

        obj.m1(100, 200);
        obj.m1("Java", "Generics");
    }
}
