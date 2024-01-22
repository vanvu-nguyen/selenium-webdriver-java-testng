package javaTester;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class Topic_12_Java_Generic {
    private String fullName;

    public static void main(String[] agrs) {
        // Generic
        // E T V K L: type of elements in this list
        // Prefer to user
        List<String> students = new ArrayList<String>();
        students.add("Vu");
        students.add("Huy");
        students.add("Phong");

        // Non generic
        // Not prefer
        List addresses = new ArrayList<>();
        addresses.add("123 tran phu");
        addresses.add(456);
        addresses.add(true);
        addresses.add(15.4);
    }
    }