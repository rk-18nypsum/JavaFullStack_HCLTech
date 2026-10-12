package Day4;
interface calculator{
    public int calculate( int a, int b);
}

public class lamda {

    static void main() {
        calculator c = (a,b) -> a+b;
        System.out.println(c.calculate(10,5));
        calculator e = ( int a, int b) -> a-b;
        System.out.println(e.calculate(23,18));
        calculator d = ( int a, int b) -> a*b;
        System.out.println(d.calculate(3,4));
        calculator m = ( int a , int b)-> (a/b);
        System.out.println(m.calculate(12,4));
        calculator g = (int a  , int b)->a%b;
        System.out.println(g.calculate(14 ,3));


    }

}
