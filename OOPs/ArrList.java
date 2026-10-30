package OOPs;
import java.util.*;
// Arraylist:- improved version of arraylist, continuos memory allocation , no constraint fixed size,
public class ArrList {

    public static  void main(String args[]){
        System.out.println("program started");
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<Integer> list1 = new ArrayList<>();
        for ( int i =0; i<10; i++){
            list.add(i+1);
        }
        for( int i =0; i<20; i++){
            list.add(2*i);
        }
        list.indexOf(3);
        list.set(3,66);
        list.get(8);
        list.addAll(list1);
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }



    }
}
