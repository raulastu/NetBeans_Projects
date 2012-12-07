
public class RCUtil {

    /**
     * 
     * @param millis time in milliseconds
     * @return time given from millis in time format (hh:mm:ss) zero leading.
     */
    public static String longToTime(long millis) {
        long secs = (long) Math.abs((millis / 1000) % (60));
        long mins = (long) Math.abs(((millis / 1000) / 60) % 60);
        long hours = Math.abs((millis / 1000) / 60 / 60);
        return (hours < 10 ? "0" + hours : hours) + ":" +
                (mins < 10 ? "0" + mins : mins) + ":" +
                (secs < 10 ? "0" + secs : secs);
    }

    public static int binaryToDecimal(String binaryNumber) {
        int r = 0;
        for (int i = 0; i < binaryNumber.length(); i++) {
            r += Character.getNumericValue(binaryNumber.charAt(i)) *
                    Math.pow(2, binaryNumber.length() - 1 - i);
        }
        return r;
    }

    public static void main(String[] args) {
        System.out.println(binaryToDecimal("11111111"));
    }
}
