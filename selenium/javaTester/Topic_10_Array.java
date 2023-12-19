package javaTester;

public class Topic_10_Array {
    static int[] studentAge = {5,10,15,20};
    static String[] studentName = {"Khoa", "Lan", "An", "Lam"};

    public static void main(String[] args) {
        String[] studentAddress = new String[5];
        studentAddress[0] = "Hoang Van Thu";
        studentAddress[1] = "Cua Dai";
        studentAddress[2] = "Tran Phu";
        studentAddress[3] = "Le Loi";
        studentAddress[4] = "Bach Dang";

        System.out.println(studentName[1]);
        System.out.println(studentAge[3]);

        for (int i = 0; i < studentAddress.length; i++) {
            System.out.println(studentAddress[i]);
        }
    }
}
