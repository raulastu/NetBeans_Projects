package admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rC
 */
public class Concurso {

    String id;
    private String nombre;
    long leftTime;
    long totalTime;
    Connection con;
    String estado;

    public Concurso(String id, String nombre, String totalTime, String leftTime, Connection con) {
        this.id = id;
        this.nombre = nombre;
        this.leftTime = RCUtil.timeToLong(leftTime);
        this.totalTime = RCUtil.timeToLong(totalTime);
        this.con = con;
    }

    public long getLeftTime() {
        return leftTime;
    }

    public long getTotalTime() {
        return totalTime;
    }

    public void updateTimes() {
        try {            
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE concurso set total_time = ?, left_time = ? WHERE id_concurso = ?");
            ps.setString(1, RCUtil.longToTime(totalTime));
            ps.setString(2, RCUtil.longToTime(leftTime));
            ps.setString(3, id);
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Concurso.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    static public int getSecs(long time) {
        int mins = (int) (((time / 1000))) % 60;
        return mins;
    }

    static public int getMins(long time) {
        int mins = (int) (((time / 1000) / 60)) % 60;
        return mins;
    }

    static public int getHours(long time) {
        int h = (int) (((time / 1000) / 60) / 60);
        return h;
    }

    static public long timeToMillis(int h, int m, int s) {
        return h * 3600000 + m * 60000 + s * 1000;
    }

    public String getNombre() {
        return nombre;
    }
}
