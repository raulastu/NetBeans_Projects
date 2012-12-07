package encuestaaeiinf;

import java.sql.*;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.JasperReport;
import java.io.OutputStream;

public class Report01 {

    public Report01() {
        try {
            JasperDesign jDesign =
                    JRXmlLoader.load("E:/rAuL!/Projects/EncuestaAEIINF/peru01.jrxml");
            JasperReport jReport = JasperCompileManager.compileReport(jDesign);
            Connection cn = new DB().getConnection();
            JasperPrint jPrint = JasperFillManager.fillReport(jReport, null, cn);
            cn.close();
            JasperViewer.viewReport(jPrint);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new Report01();
    }
}
