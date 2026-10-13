package Day5;
import java.util.*;

public class Streams {
    static void main() {
        List<Integer>ls = new ArrayList<>();
        for(int i =0; i<15; i++){
            ls.add(i*3);
        }
        //stream(Java 8)  is like buffer used to process a collection of data, such as a List or Set, in a simple and efficient way.
       // it ends after terminal operation .
        ls.stream().filter(e -> e % 2 == 0).forEach(System.out::println);
        long count = ls.stream().filter(e->e%2 ==0).count();
//        System.out.println(count);
//        System.out.println(ls);

        //
        List<String> lt = new ArrayList<>();
        lt.add("Rajeev");
        lt.add("Rahul");
        lt.add("Sahil");
        lt.add("Kushagra");
        lt.add("Nidhi");lt.add("Shreya");
        lt.add("sachin");
        lt.stream().filter(e->e.startsWith("R")).sorted().forEach(System.out::println);
       long c =  lt.stream().filter(e->e.endsWith("l")).count();
        System.out.println(c);

    }
}
