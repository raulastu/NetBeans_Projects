package inexistentes;

import cecocadb.DB;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.*;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import rclib.RCContainer;
import static java.util.Arrays.*;

public class PanInexistencia extends JPanel {

    DB database;
    ArrayList<Inexistente> inexs;
    ArrayList<Real> reales;
    private int idConcurso;
    private IRGrupo[] grupos;
    GUIReal[][] realesGUI;
    GUIRelacion[][] relacionesAula;
    JPanel panInex;
    JPanel panRelacion;
    JPanel panReales;
    private int selectedRealGrupo = 0;
    private int selectedIndex = 0;
    Color colorRealResaltado = Color.red;
    InexistenciaMainPanel main;
    Inexistencia ine;

    public PanInexistencia(DB database, int idConcurso, InexistenciaMainPanel main) throws SQLException {
        this.main = main;
        this.idConcurso = idConcurso;
        this.database = database;
        ine = new Inexistencia(database);
        ine.testRandomCase(10, 2);
        grupos = ine.getArrayGrupos();
        initMyComponents();
    }

    public void addReal(String codiPostulante, String aula, String nombreCompleto) {
        Real real = new Real(codiPostulante, aula, nombreCompleto);
        grupos[selectedRealGrupo].addReal(selectedIndex, real);
        grupos[selectedRealGrupo].cerrarGrupo();
        grupos[selectedRealGrupo].refrescarRelaciones();
        initMyComponents();
    }

    void initMyComponents() {
        removeAll();
        int r = 0;
        for (IRGrupo iRGrupo : grupos) {
            r += iRGrupo.getReales().size();
        }
        realesGUI = new GUIReal[getGrupos().length][];
        relacionesAula = new GUIRelacion[getGrupos().length][];
        panInex = new JPanel(new GridLayout(r, 1));
        panRelacion = new JPanel(new GridLayout(r, 1));
        panReales = new JPanel(new GridLayout(r, 1));
        cargarInexistentes();
        cargarReales();
        cargarRelaciones();
    }

    public void select(int grupo, int indice) {
        selectedRealGrupo = grupo;
        selectedIndex = indice;
        realesGUI[grupo][indice].select();
    }

    public void deresaltarReales() {
        for (int i = 0; i<realesGUI.length; i++) {
            for (int j = 0; j<realesGUI[i].length; j++) {
                realesGUI[i][j].deselect();
            }
        }
    }

    void cargarReales() {
        panReales.removeAll();
        for (int i = 0; i<getGrupos().length; i++) {
            ArrayList<Real> myReales = grupos[i].getReales();
            realesGUI[i] = new GUIReal[myReales.size()];
            for (int j = 0; j<myReales.size(); j++) {
                realesGUI[i][j] = new GUIReal(myReales.get(j).getCodiPostulante(),
                        myReales.get(j).getAula(), myReales.get(j).getNombres());
                final int x = i,  y = j;
                MouseAdapter mouseAdapter = new MouseAdapter() {

                    @Override
                    public void mousePressed(MouseEvent e) {
                        deresaltarReales();
                        select(x, y);
                    }
                };
                realesGUI[i][j].addMouseAdapter(mouseAdapter);
                panReales.add(realesGUI[i][j]);
            }
        }
        System.out.println(deepToString(realesGUI));
    }

    void cargarRelaciones() {
        for (int i = 0; i<getGrupos().length; i++) {
            ArrayList<Boolean> myRelaciones = grupos[i].getRelacionAula();
            for (int j = 0; j<myRelaciones.size(); j++) {
                if (myRelaciones.get(j)) {
                    relacionesAula[i][j].check();
                } else {
                    relacionesAula[i][j].uncheck();
                }
            }
        }
    }

    void cargarInexistentes() {
        for (int i = 0; i<getGrupos().length; i++) {
            ArrayList<Inexistente> myInex = getGrupos()[i].getInexistentes();
            for (Inexistente inex : myInex) {
                if (inex.getEstadoEspecial()==null) {
                    panInex.add(new GUIInexistente(inex.getCorrelativoLectura(),
                            inex.getCodiPostErrado(), inex.getAnteriorAula(), inex.getSiguienteAula()));
                } else {
                    panInex.add(new JComboBox(EstadoEspecial.values()));
                }
            }
            relacionesAula[i] = new GUIRelacion[myInex.size()];
            for (int j = 0; j<myInex.size(); j++) {
                relacionesAula[i][j] = new GUIRelacion();
//                relacionesAula[i][j].setPreferredSize(new Dimension(200,200));
                int top = j==0 ? 1 : 0;
                int bottom = j==myInex.size()-1 ? 1 : 0;
                relacionesAula[i][j].setBorder(BorderFactory.createMatteBorder(top, 0, bottom, 0, Color.black));
                panRelacion.add(relacionesAula[i][j]);
            }
        }
        setLayout(new BorderLayout(1, 1));
        add(panInex, BorderLayout.WEST);
        add(panRelacion, BorderLayout.CENTER);
        add(panReales, BorderLayout.EAST);
    }

    /**
     * @return the realGrupo
     */
    public int getSelectedRealGrupo() {
        return selectedRealGrupo;
    }

    /**
     * @param realGrupo the realGrupo to set
     */
    public void setRealGrupo(int realGrupo) {
        this.selectedRealGrupo = realGrupo;
    }

    /**
     * @return the realIndex
     */
    public int getSelectedRealIndex() {
        return selectedIndex;
    }

    /**
     * @param realIndex the realIndex to set
     */
    public void setRealIndex(int realIndex) {
        this.selectedIndex = realIndex;
    }

    /**
     * @return the grupos
     */
    public IRGrupo[] getGrupos() {
        return grupos;
    }

    /**
     * @param grupos the grupos to set
     */
    public void setGrupos(IRGrupo[] grupos) {
        this.grupos = grupos;
    }

    public static void main(String[] args) throws Exception {
        DB database = new DB();
        RCContainer.showPanel(new PanInexistencia(database, 1, null));
        database.closeDB();
    }
}
