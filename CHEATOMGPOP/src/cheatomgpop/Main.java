package cheatomgpop;

import java.util.ArrayList;

public class Main {


    int genSubSets(ArrayList al) {
        int c = 0;
        int len = al.size();
//        int n = ;
        for (int i = 0; i < (1 << al.size()); i++) {
            ArrayList subset = new ArrayList();
            for (int j = 0; j < al.size(); j++) {
                if ((i & (1 << j)) > 0) {
                    subset.add(al.get(j));
                }
            }
            c++;
        }
        return c;
    }

    public static void main(String[] args) {
    }
}
