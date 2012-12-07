package PaqueteLogicaDelNegocio;
import java.util.Map;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.*;

public class GestiónDeReporte 
    {        
        public void ejecutarReporte(String master)
            {  
                    {try //                try
            {
                JasperReport masterReport = null;
                try {
                    masterReport = (JasperReport) JRLoader.loadObject(master);
                } catch (JRException e) {
                    System.out.println("Error cargando el reporte maestro: " + e.getMessage());
                }
                Map parametro = new HashMap();
                //Reporte diseñado y compilado con iReport
                JasperPrint jasperPrint = JasperFillManager.fillReport(masterReport, parametro, ConexionConBaseDeDatos.obtenerConexion());
                //Se lanza el Viewer de Jasper, no termina aplicación al salir
                JasperViewer jviewer = new JasperViewer(jasperPrint, false);
                jviewer.setTitle("Reporte de alumnos");
                jviewer.setVisible(true);
            }
            //                catch (Exception e)
            //                    {
            //                        System.out.println("Mensaje de Error:"+e.getMessage());
            //                }
            catch (JRException ex) {
                Logger.getLogger(GestiónDeReporte.class.getName()).log(Level.SEVERE, null, ex);
            }
                    }
           }
    }
