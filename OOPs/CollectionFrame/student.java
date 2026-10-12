
package OOPs.CollectionFrame;

import java.util.*;

public class student implements Comparable<student> {

    String name;
    int age;
    int roll_no;
    double marks;

    student(String name, int age, int roll_no, double marks) {
        this.name = name;
        this.age = age;
        this.roll_no = roll_no;
        this.marks = marks;
    }

    void display() {
        System.out.println("Name: " + name + ", age:" + age +"," + "Roll no:" + roll_no +", marks:" + marks );
        System.out.println("----------------------");
    }

    // Comparator: sort by roll number
    static class MyComp implements Comparator<student> {
        @Override
        public int compare(student s1, student s2) {
            return Integer.compare(s1.roll_no, s2.roll_no);
        }
    }

    // Comparable: sort by age
    @Override
    public int compareTo(student a) {
        return Integer.compare(this.age, a.age);
    }

    public static void main(String[] args) {

        ArrayList<student> ar = new ArrayList<>();

        ar.add(new student("rajeev", 22, 45, 98));
        ar.add(new student("rahul", 21, 48, 99));
        ar.add(new student("ansh", 18, 23, 88));
        ar.add(new student("sanchit", 23, 10, 49));
        ar.add(new student("kusum", 13, 34, 78));

        // Sort by age using Comparable
        Collections.sort(ar);

        System.out.println("Sorted by age:");
        for (student s : ar) {
            s.display();
        }

        // Sort by roll number using Comparator
        Collections.sort(ar, new MyComp());

        System.out.println("Sorted by roll number:");
        for (student s : ar) {
            s.display();
        }
    }
}
