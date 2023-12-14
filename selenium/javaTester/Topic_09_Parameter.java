package javaTester;

public class Topic_09_Parameter {
    static String fullNameGlobal = "Automation Tesing";
    public static void main (String[] args) {
        System.out.println(getFullName());
        setFullName("Manual Tesing");
        System.out.println(getFullName());
    }
    public static void setFullName(String fullName) {
        fullNameGlobal = fullName;
    }
    public static String getFullName () {
        return fullNameGlobal;
    }

/*
    The 'public static void main' function used keyword 'main' for Java Application recognize, just like '@Test' keyword used for TestNG recognize.
    A static function only call the static variable or other static function. Or use 'topic' keyword if the called variable/functon is not a 'static' one (see topic 26).
    A static function can be called from another class (very opened), so just sometimes it has low security for system for set static, only set static for necessary cases.
*/

}
