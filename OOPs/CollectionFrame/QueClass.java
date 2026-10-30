package OOPs.CollectionFrame;
import java.util.*;

public class QueClass {


    public static void main(String[] args) {
        Queue<Integer> q = new LinkedList();
        q.offer(1);
        q.offer(3);
        q.offer(4);
        q.offer(5);

        q.offer(8);
        System.out.println(q.poll());
    }


}

class Quee{
    static void main() {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(25);
        q.offer(26);
        q.offer(65);
        q.offer(34);
        q.offer(29);
        q.offer(16);

        System.out.println(q);
        System.out.println(q.poll());
        System.out.println(q.isEmpty());
        System.out.println(q.size());
        System.out.println(q.contains(29));
        System.out.println(q.contains(33));
        System.out.println(q.iterator());
    }
}
