
package Day4;

import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.Consumer;
import java.util.function.Function;

interface calculator {
    public int calculate(int a, int b);
}

public class lamda {

    public static void main(String[] args) {

        // Addition
        calculator c = (a, b) -> a + b;
        System.out.println("Addition: " + c.calculate(10, 5));

        // Subtraction
        calculator e = (a, b) -> a - b;
        System.out.println("Subtraction: " + e.calculate(23, 18));

        // Multiplication
        calculator d = (a, b) -> a * b;
        System.out.println("Multiplication: " + d.calculate(3, 4));

        // Division
        calculator m = (a, b) -> a / b;
        System.out.println("Division: " + m.calculate(12, 4));

        // Modulus
        calculator g = (a, b) -> a % b;
        System.out.println("Modulus: " + g.calculate(14, 3));



        Predicate<Integer> p = n -> n % 2 == 0;

        System.out.println("Is 10 even? " + p.test(10));
        System.out.println("Is 7 even? " + p.test(7));



        Supplier<String> s = () -> "Hello Java";

        System.out.println("Supplier: " + s.get());


        // 3. Consumer: Accepts input and performs an action
        Consumer<String> con =
                name -> System.out.println("Hello " + name);

        con.accept("Rahul");
        Function<Integer, Integer> f = n -> n * n;

        System.out.println("Square: " + f.apply(5));
    }
}
