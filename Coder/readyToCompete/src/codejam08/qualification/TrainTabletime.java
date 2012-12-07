package codejam08.qualification;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class TrainTabletime {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        String fileNameIn = "B-large.in";
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("E:/codejam/train timetable/" + fileNameIn)));
        String fileNameOut= fileNameIn.substring(0,fileNameIn.indexOf(".in"))+".out";
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("E:/codejam/train timetable/" + fileNameOut)));
        int nroCasos = Integer.parseInt(in.readLine());
        for (int i = 1; i <= nroCasos; i++) {
            double turnaroundTime = Double.parseDouble(in.readLine()) / 60;
            System.out.println(turnaroundTime);
            String Nline = in.readLine();
            int NA = getNA_NB(Nline)[0];
            int NB = getNA_NB(Nline)[1];
            double[][] Alines = new double[NA][5];
            for (int j = 0; j < NA; j++) {
                String line = in.readLine();
                double depart = parseTime(line.substring(0, line.indexOf(" ")));
                double arrival = parseTime(line.substring(line.indexOf(" ") + 1, line.length()));
                Alines[j][0] = depart;
                Alines[j][1] = arrival;
            }
            double[][] Blines = new double[NB][5];
            for (int j = 0; j < NB; j++) {
                String line = in.readLine();
                double depart = parseTime(line.substring(0, line.indexOf(" ")));
                double arrival = parseTime(line.substring(line.indexOf(" ") + 1, line.length()));
                Blines[j][0] = depart;
                Blines[j][1] = arrival;
            }
            fillValues(Alines);
            fillValues(Blines);
            processMarks(Alines, Blines, turnaroundTime);
            int Atrains = getTrains(Alines);
            int Btrains = getTrains(Blines);
            out.write("Case #" + i + ": " + Atrains + " " + Btrains);
            out.newLine();
        }
        in.close();
        out.close();
    }

    private static int getTrains(double[][] array) {
        int trains = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i][2] == -1) {
                trains++;
            }
        }
        return trains;
    }

    private static void processMarks(double[][] Alines, double[][] Blines, double turnaroundTime) {
        sortByX(Alines, 1);
        sortByX(Blines, 0);
        markNroTrains(Blines, Alines, turnaroundTime);

        sortByX(Alines, 0);
        sortByX(Blines, 1);
        markNroTrains(Alines, Blines, turnaroundTime);
    }

    private static int markNroTrains(double[][] origenes, double[][] destinos, double turnAroundTime) {
        int nroTrains = 0;
        for (int i = 0; i < destinos.length; i++) {
            double destinoI = destinos[i][1];
            for (int j = 0; j < origenes.length; j++) {
                if (destinoI + turnAroundTime <= origenes[j][0]) {
                    if (origenes[j][2] == -1) {
                        destinos[i][3] = origenes[j][4];
                        origenes[j][2] = destinos[i][4];
                        break;
                    }
                }
            }
        }
        return nroTrains;
    }

    private static void fillValues(double[][] array) {
        for (int i = 0; i < array.length; i++) {
            array[i][4] = i;
            array[i][2] = -1;
            array[i][3] = -1;
        }
    }

    private static void showArr(double[][] arr) {
        for (double[] d : arr) {
            System.out.println(d[0] + " " + d[1] + " " + d[2] + " " + d[3] + " " + d[4]);
        }
    }

    private static void sortByX(double[][] arr, int X) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[i][X] < arr[j][X]) {
                    double tempDep = arr[i][0];
                    arr[i][0] = arr[j][0];
                    arr[j][0] = tempDep;
                    double tempArrival = arr[i][1];
                    arr[i][1] = arr[j][1];
                    arr[j][1] = tempArrival;
                    double tempMarkOrigin = arr[i][2];
                    arr[i][2] = arr[j][2];
                    arr[j][2] = tempMarkOrigin;
                    double tempMarkDestiny = arr[i][3];
                    arr[i][3] = arr[j][3];
                    arr[j][3] = tempMarkDestiny;

                    double tempId = arr[i][4];
                    arr[i][4] = arr[j][4];
                    arr[j][4] = tempId;
                }
            }
        }
    }

    private static double parseTime(String time) {
        double hours = Double.parseDouble(time.substring(0, time.indexOf(":")));
        double minutes = Double.parseDouble(time.substring(time.indexOf(":") + 1, time.length()));
        return hours + minutes / 60;
    }

    private static int[] getNA_NB(String line) {
        int[] NANB = new int[2];
        NANB[0] = Integer.parseInt(line.substring(0, line.indexOf(" ")));
        NANB[1] = Integer.parseInt(line.substring(line.indexOf(" ") + 1, line.length()));
        return NANB;
    }
}
