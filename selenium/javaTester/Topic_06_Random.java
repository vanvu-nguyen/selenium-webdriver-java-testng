package javaTester;

import java.util.Random;

public class Topic_06_Random {

    public String getEmailAddress() {
        Random randNum = new Random();
        return "vunguyen" + randNum.nextInt(99999) + "@gmail.com";
    }

}
