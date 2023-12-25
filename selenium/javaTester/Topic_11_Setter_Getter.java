package javaTester;

import org.testng.annotations.Test;

public class Topic_11_Setter_Getter {
    private String fullName;

    @Test
    public void TC_01_Setter_Getter () {
        setFullName("vuNguyen");
        System.out.println(getFullName());

        setFullName("miaHoang");
        System.out.println(getFullName());
    }

    public void setFullName (String name) {
        fullName = name;
    }

    public String getFullName () {
        return fullName;
    }
}
