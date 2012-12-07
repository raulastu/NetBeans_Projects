package noutilYet;

import problems.OpticFiber;
import util.startTemplate;

public class MathTest {

    public static void main(String[] args) {
        System.out.println(Math.round(123.3434));
        System.out.println(Math.IEEEremainder(12, 13));
        System.out.println(Math.copySign(131, -1));
        System.out.println(Math.ceil(1231.123));
        System.out.println(Math.cbrt(27));
        System.out.println(Math.floor(1231.123));
        System.out.println(Math.ulp(123.4124));
        System.out.println(Math.sqrt(33));
        System.out.println((33));
        System.out.println(Math.toRadians(53));
        System.out.println(Math.toDegrees(0.92));
        System.out.println(3 * (Math.sin(Math.toRadians(53)) / Math.cos(Math.toRadians(53))));        
        
        /*System.out.println(OpticFiber.toPrecision(922.4999, 0));
        System.out.println(OpticFiber.toPrecision(922.4999, 1));
        System.out.println(OpticFiber.toPrecision(922.4999, 2));
        System.out.println(OpticFiber.toPrecision(922.4999, 3));
        System.out.println(OpticFiber.toPrecision(922.4999, 4));
        System.out.println(OpticFiber.toPrecision(7.620099999999995 , 3));
        System.out.println(OpticFiber.toPrecision(7.6220099999999995, 6));*/
        System.out.println(startTemplate.parsePrecision(335.6054125195361, 5));        
        System.out.println((float)(7.0) + (float)(0.62201));
    }
}
