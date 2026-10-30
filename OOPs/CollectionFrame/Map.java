package OOPs.CollectionFrame;
import java.util.*;

public class Map {
    static void main(String args[]) {
        HashMap<Integer,String> mp = new HashMap<>();
        mp.put(1,"India");
        mp.put(2,"china");
        mp.put(3,"lanka");
        mp.put(4,"nepal");
        mp.put(5,"us");
        mp.put(1,"uk");
        mp.put(9,"france");
        System.out.println(mp);
        System.out.println(mp.containsKey(0));
    }
}
class LinkedMap{
    static void main (String args[]){
        LinkedHashMap<Integer,String> lp = new LinkedHashMap<>();
        lp.put(1,"India");
        lp.put(2,"china");
        lp.put(3,"lanka");
        lp.put(4,"nepal");
        lp.put(5,"us");
        lp.put(1,"uk");
        lp.put(9,"france");
        System.out.println(lp);
        System.out.println(lp.containsKey(0));
    }
}
