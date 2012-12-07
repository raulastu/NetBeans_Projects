package admin;

/**
 *
 * @author rC 
 * RCUtil v 1.0
 */
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

    /**
     * 
     * @param time
     * @return
     */
    public static long timeToLong(String time) {
        String[] s = time.split(":");
        long res = Long.parseLong(s[0]) * 60 * 60 * 1000 +
                Long.parseLong(s[1]) * 60 * 1000 +
                Long.parseLong(s[2]) * 1000;
        return res;
    }

    public static String[][] deleteCol(String[][] array, int colToDelete, int newLength) {
        String[][] result = new String[array.length][newLength];
        for (int i = 0; i < array.length; i++) {
            int jCounter = 0;
            for (int j = 0; j < array[i].length; j++) {
                if (j != colToDelete) {
                    result[i][jCounter++] = array[i][j];
                }
            }
        }
        return result;
    }
}
