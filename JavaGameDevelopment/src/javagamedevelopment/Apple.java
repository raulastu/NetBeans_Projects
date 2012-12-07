package javagamedevelopment;

import java.util.Random;

public class Apple implements Runnable {

    String name;
    Random r = new Random();
    int time;

    public Apple(String n) {
        name = n;
        time = r.nextInt(999);
    }

    public void run() {
        try {
            System.out.printf("%s is running at %d\n", name, time);
            System.out.println(name + " s" + time);
            Thread.sleep(time);
            System.out.printf("%s is done\n", name);
        } catch (Exception e) {
        }
    }
}
