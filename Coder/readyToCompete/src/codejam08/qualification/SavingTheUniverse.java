package codejam08.qualification;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;

public class SavingTheUniverse {
    static String lastEngine;
    public static void main(String[] args) throws IOException {
        
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("E:/codejam/A-large.in")));       
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("E:/codejam/A-large.out")));
        int nroCasos = Integer.parseInt(in.readLine());

        for (int i = 1; i <= nroCasos; i++) {
            int nroSearchEngines = Integer.parseInt(in.readLine());
            String[] searchEngines = new String[nroSearchEngines];
            for (int j = 0; j < searchEngines.length; j++) {
                searchEngines[j] = in.readLine();
            }
            int nroQueries = Integer.parseInt(in.readLine());
            String[] queries = new String[nroQueries];
            for (int k = 0; k < queries.length; k++) {
                queries[k] = in.readLine();
            }

            int nroSwitches = 0;            
            int lastIndex = 0;
            String[] copyQueries = queries;
            while (lastIndex < queries.length) {
                lastEngine = null;
                lastIndex = getUltimoEnOcurrir(searchEngines, copyQueries);
                copyQueries = getCustomArray(lastIndex, copyQueries);                
                if (lastEngine == null) {                    
                    break;
                } else {
                    nroSwitches++;
                }
            }
            out.write("Case #" + i + ": " + nroSwitches + "\n");
            out.newLine();
        }
        out.close();
        in.close();
    }

    public static String[] getCustomArray(int offset, String[] queries) {
        String[] newQueries = new String[queries.length - offset];
        int x = 0;
        for (int i = offset; i < queries.length; i++, x++) {
            newQueries[x] = queries[i];
        }
        return newQueries;
    }

    public static int getUltimoEnOcurrir(String[] sEngines, String[] queries) {
        HashSet<String> set = new HashSet<String>();
        for (String x : sEngines) {
            set.add(x);
        }
        HashSet<String> newSet = new HashSet<String>();

        int lastIndex = -1;
        for (int i = 0; i < queries.length; i++) {
            if (!newSet.contains(queries[i])) {
                newSet.add(queries[i]);
                lastIndex = i;
            }
            if (newSet.equals(set)) {
                lastEngine = queries[i];
                break;
            }
        }
        return lastIndex;
    }
}
