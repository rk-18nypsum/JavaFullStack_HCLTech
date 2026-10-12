package Assignments;
import java.util.*;
public class AssnClass {

  public static class emp {
        String name;
        int empId;
        int age;
        int salary;
       public emp(String name, int empId, int age, int salary){
            this.name = name;
            this .empId =empId;
            this.age = age;
            this.salary= salary;
        }
    }
    public static void main (String args[]){
        ArrayList<emp> ar= new ArrayList<>();
        //create
            ar.add(new emp("rahul",01,18,20000));
            ar.add(new emp("sanjeev",02, 22,30000));
            ar.add(new emp("alok",03,19,23000));
            ar.add(new emp("krish",04, 22,24000));
            ar.add(new emp("trisha",05,21,20000));
            ar.add(new emp("naman",06,24,25000));
        for (emp e : ar) {
            System.out.println(e.name + " " + e.empId + " "
                    + e.age + " " + e.salary);
        }
        //delete
        Iterator<emp> it = ar.iterator();

        while (it.hasNext()) {
            emp e = it.next();

            if (e.empId == 3) {
                it.remove();
                break;
            }
        }
//        for (emp e : ar) {
//            System.out.println(e.name + " " + e.empId + " "
//                    + e.age + " " + e.salary);
//        }
        //update
        for (emp e : ar) {
            if (e.empId == 3) {
                e.name = "Amit";
                e.age = 20;
                e.salary = 28000;
                break;
            }
        }
        System.out.println();
        for (emp e : ar) {
            System.out.println(e.name + " " + e.empId + " "
                    + e.age + " " + e.salary);
        }
    }

}
