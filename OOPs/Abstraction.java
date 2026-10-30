package OOPs;
//Abstraction concept -Hidiing the implementation details and showing he esssential details.

//Abstract class:
// no body and class extending it must provide implementaion of the abstract class methods
    abstract class Ab{
       abstract void m1();
       abstract void m3();

    }
   class Ba extends Ab{
        void m1(){
            System.out.println("implementation of m1");
        }
        void m3(){
            System.out.println("implementation of m3");
        }
       public static void main (String args[]){
            Ba  b = new Ba();
            b.m1();
       }
    }





