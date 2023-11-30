package javaTester;

public class Topic_02_And_Or {
    public static void main(String[] args) {

        // Member 1 declaration
        boolean member1;
        // Member 2 declaration
        boolean member2;
        // Result of 2 member declaration

        // And case: Just return true if all variable has value = true

        member1 = true;
        member2 = true;
        System.out.println("Result is: " + (member1&&member2));

        member1 = true;
        member2 = false;
        System.out.println("Result is: " + (member1&&member2));

        member1 = false;
        member2 = true;
        System.out.println("Result is: " + (member1&&member2));

        member1 = false;
        member2 = false;
        System.out.println("Result is: " + (member1&&member2));

        // Or case: Return true if one of variable has value = true

        member1 = true;
        member2 = true;
        System.out.println("Result is: " + (member1||member2));

        member1 = true;
        member2 = false;
        System.out.println("Result is: " + (member1||member2));

        member1 = false;
        member2 = true;
        System.out.println("Result is: " + (member1||member2));

        member1 = false;
        member2 = false;
        System.out.println("Result is: " + (member1||member2));
    };
}
