package inexistentes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.*;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanVerInexReales extends JPanel {

    private ArrayList<Inexistente> inexistentes;
    private ArrayList<Real> reales;
    private JPanel panInex;
    private JPanel panReales;
    private Color markColor = Color.red;

    PanVerInexReales(ArrayList<Inexistente> totalInexs, ArrayList<Real> totalReales,
            ArrayList<Inexistente> errInex, ArrayList<Real> errReal) {
        this.inexistentes = totalInexs;
        this.reales = totalReales;
        panInex = new JPanel(new GridLayout(totalInexs.size() + 1, 1));
        panReales = new JPanel(new GridLayout(totalReales.size() + 1, 1));
        panInex.add(new JLabel("Inexistentes"));
        for (Inexistente inex : inexistentes) {
            GUIInexistente x = new GUIInexistente(inex.getCorrelativoLectura(),
                    inex.getCodiPostErrado(), inex.getAnteriorAula(), inex.getSiguienteAula());
            for (Inexistente inexistente1 : errInex) {
                if (inex == inexistente1) {
                    x.mark(markColor);
                    break;
                }
            }
            panInex.add(x);
        }
        panReales.add(new JLabel("Reales"));
        for (Real real : reales) {
            GUIReal x = new GUIReal(real.getCodiPostulante(), real.getAula(), real.getNombres());
            for (Real real1 : errReal) {
                if (real1 == real) {
                    x.mark(markColor);
                    break;
                }
            }
            panReales.add(x);
        }

        setLayout(new BorderLayout());
        add(panInex, BorderLayout.WEST);
        add(panReales, BorderLayout.EAST);
    }
}
