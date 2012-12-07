package admin.live;

import admin.ConexionDB;
import java.awt.Dimension;
import javax.swing.JApplet;

public class rCApplet extends JApplet {

    ConexionDB con;
    LiveMonitor lm;

    @Override
    public void init() {
        super.init();
        String s = this.getParameter("idConcurso");
        con = new ConexionDB();
        lm = new LiveMonitor(con.getConnection(), "3", 1);
        setSize(new Dimension(600, 400));
        getContentPane().add(lm);
    }
}
