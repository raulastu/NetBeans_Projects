package last;

import java.util.Scanner;

public class Main2 {

    @SuppressWarnings("static-access")
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        int c = 0;
        while (c++ < n) {
            int nrows = Integer.parseInt(sc.nextLine());
            int[][] grid = new int[nrows][nrows];
            boolean[][] memo = new boolean[nrows][nrows];

            for (int i = 0; i < nrows; i++) {
                String[] s = sc.nextLine().split(" ");
                for (int j = 0; j < s.length; j++) {
                    grid[i][j] = Integer.parseInt(s[j]);
                    if (grid[i][j] == 0) {
                        memo[i][j] = true;
                    }
                }
            }
//            this.grid = grid;
            int max = 0;
            for (int i = 0; i < memo.length; i++) {
                for (int j = 0; j < grid.length; j++) {
                    max = Math.max(max, flood(i, j, memo, grid));
                }
            }
            System.out.println(max);
        }
        sc.close();
    }
    static int grid[][];
    static int[] di = {-1, 0, 1, 0}; //urld
    static int[] dj = {0, 1, 0, -1};

    public static int flood(int x, int y, boolean[][] memo, int grid[][]) {
        int r = 0;
        for (int i = 0; i < 4; i++) {
            int X = x + di[i];
            int Y = y + dj[i];
            if (X >= 0 && X < memo.length && Y >= 0 && Y < memo.length && !memo[X][Y]) {
                memo[X][Y] = true;
                r += grid[X][Y] + flood(X, Y, memo, grid);
            }
        }
        return r;
    }
}
