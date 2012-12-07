package gcj;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class MakingCases {

    public static void main(String[] args) throws FileNotFoundException {

        PrintWriter pw = new PrintWriter(new File("C:/rcIn.txt"));
        for (int i = 0; i < 5000; i++) {
            pw.println("abcdedasdocoxou");
        }
        for (int i = 0; i < 500; i++) {
            pw.println("(ghijklmnoprstuvwxyzabcdef)(efgijklmnoqrstuvxyabcd)(vwxyabcdeghijklmnopqrtu)(cdefhijklmnopqrtuvwyzab)(eghjklmnprstuvwxyzabc)(suwxyzabefghijklmnpqr)(orstuwxyzacdfghijlmn)(ijklmnopqrstuvwxyzabcdefg)(nopqrstuvwxyzabcdefgklm)(yzacdefgijklpqrsuvw)");
        }
        pw.close();
    }
}
