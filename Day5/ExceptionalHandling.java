package Day5;

public class ExceptionalHandling {

    public static void main(String[] args) {

        int a = 10;
        int b = 0;

        // Unchecked Exception: ArithmeticException
        try {
            int result = a / b;

            System.out.println("Result: " + result);
        }
        catch (ArithmeticException e) {
            System.out.println("Unchecked Exception: Cannot divide by zero!");
        }
        finally {
            System.out.println("Finally block executed");
        }

        // Unchecked Exception: ArrayIndexOutOfBoundsException
        try {
            int arr[] = {2, 5, 8, 9, 11, 19, 163};

            System.out.println(arr[10]);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Unchecked Exception: Invalid array index! i.e. array is of length 7 and you are asking for 10");
        }

        // Checked Exception: InterruptedException
        try {
            System.out.println("Thread is sleeping...");
            Thread.sleep(1000);
            System.out.println("Thread woke up!");
        }
        catch (InterruptedException e) {
            System.out.println("Checked Exception: Thread was interrupted!");
        }

        System.out.println("Program continues...");
    }

}