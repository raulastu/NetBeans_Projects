
import java.math.BigInteger;

/**
 *
 * @author rC
 */
public class FacebookIssues {

    static String facebookEmail() {
        BigInteger s = new BigInteger("FACEB00C", 16);
        long s2 = s.longValue();
        String email = (s2 >> 2) + "@facebook.com";
        return email;
    }

    public static void main(String[] args) {
        System.err.println(facebookEmail());
    }
}
