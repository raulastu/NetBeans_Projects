package vstudionetutil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class PopulatingMatricula {

    public PopulatingMatricula() {
    }

    public String populateTable(Connection cn, String tableName,
            String[] cols, ArrayList<String[]> rows) throws SQLException {
        assert rows.get(0).length == cols.length : rows.get(0).length + " " + cols[0].length();
        String columns = "";
        String sings = "";
        for (int i = 0; i < cols.length; i++) {
            columns += cols[i] + ", ";
            sings += "?, ";
        }
        columns = columns.substring(0, columns.length() - 2);
        sings = sings.substring(0, sings.length() - 2);
        String queryToWriteFile = "USE " + databaseName + " \n" +
                "INSERT INTO " + tableName + "(" + columns + ") VALUES (";
        String query = queryToWriteFile + sings + ")";
//        System.out.println(query);
        String res = "";
        for (int i = 0; i < rows.size(); i++) {
            String lineToWrite = queryToWriteFile;
            PreparedStatement rs = cn.prepareStatement(query);
            for (int j = 0; j < rows.get(i).length; j++) {
                lineToWrite += "'" + rows.get(i)[j] + "', ";
                rs.setString((j + 1), rows.get(i)[j]);
            }
            lineToWrite = lineToWrite.substring(0, lineToWrite.length() - 2);
            res += lineToWrite + "\n";
            rs.execute();
        }
        System.out.println(tableName + " created");
        return res;
    }

    static String populateUniv(Connection cn) throws Exception {
        String[] cols = {"nombre", "direccion"};
        ArrayList<String[]> array = new ArrayList<String[]>();
        array.add(new String[]{"UNJFSC", "Mercedes Indacochea S/N"});
        return new PopulatingMatricula().populateTable(cn, "t_universidad", cols, array);
    }

    static String populateFacultades(Connection cn) throws Exception {
        String[] cols = {"id_universidad", "cod_facultad", "nombre"};
        ArrayList<String[]> a = new ArrayList<String[]>();
        a.add(new String[]{"1", "fac001", "Ingenieria"});
        a.add(new String[]{"1", "fac002", "Contabilidad"});
        a.add(new String[]{"1", "fac003", "Medicina"});
        return new PopulatingMatricula().populateTable(cn, "t_facultad", cols, a);
    }

    static String populateEscuelas(Connection cn) throws Exception {
        String[] cols = {"id_facultad", "cod_escuela", "nombre"};
        ArrayList<String[]> a = new ArrayList<String[]>();
        a.add(new String[]{"1", "es001", "Ingenieria Informática"});
        a.add(new String[]{"1", "es002", "Ingenieria de Sistemas"});
        a.add(new String[]{"1", "es003", "Ingenieria Industrial"});

        a.add(new String[]{"2", "es004", "Ciencias Contables y Financieras"});
        a.add(new String[]{"2", "es005", "Administracion"});

        a.add(new String[]{"3", "es006", "Medicina Humana"});
        a.add(new String[]{"3", "es007", "Enfermería"});
        a.add(new String[]{"3", "es008", "Bromatología y Nutrición"});
        return new PopulatingMatricula().populateTable(cn, "t_escuela", cols, a);
    }

    static String populateTipoSancion(Connection cn) throws Exception {
        String[] cols = {"nombre", "descripcion"};
        ArrayList<String[]> a = new ArrayList<String[]>();
        a.add(new String[]{"Irregular", "Desaprobo la mitad de sus creditos, LLevar solo 16 creditos"});
        a.add(new String[]{"Amonestacion", "Descansa 1 semestre"});
        return new PopulatingMatricula().populateTable(cn, "t_tipo_sancion", cols, a);
    }

    static String populateEstudiantes(Connection cn) throws Exception {
        String[] cols = {"id_escuela", "cod_alumno", "ape_paterno", "ape_materno", "nombres"};
        ArrayList<String[]> a = new ArrayList<String[]>();
        a.add(new String[]{"1", "2005", "Ramirez", "Alvarado", "Raul Felipe"});
        a.add(new String[]{"1", "Descansa 1 semestre"});
        return new PopulatingMatricula().populateTable(cn, "t_tipoSancion", cols, a);
    }

    static String populateCiclo(Connection cn) throws Exception {
        String[] cols = {"nro_ciclo", "nro_romano", "nro_ciclo_text"};
        ArrayList<String[]> a = new ArrayList<String[]>();
        a.add(new String[]{"1", "I", "Primer"});
        a.add(new String[]{"2", "II", "Segundo"});
        a.add(new String[]{"3", "III", "Tercero"});
        a.add(new String[]{"4", "IV", "Cuarto"});
        a.add(new String[]{"5", "IV", "Quinto"});
        a.add(new String[]{"6", "VI", "Sexto"});
        a.add(new String[]{"7", "VII", "Septimo"});
        a.add(new String[]{"8", "VIII", "Octavo"});
        a.add(new String[]{"9", "IX", "Noveno"});
        a.add(new String[]{"10", "X", "Décimo"});
        return new PopulatingMatricula().populateTable(cn, "t_ciclo", cols, a);
    }

    static String populateTipoPago(Connection cn) throws Exception {
        String[] cols = {"descripcion",};
        ArrayList<String[]> a = new ArrayList<String[]>();
        a.add(new String[]{"Caja Central"});
        a.add(new String[]{"Banco de la Nación"});
        return new PopulatingMatricula().populateTable(cn, "t_tipo_pago", cols, a);
    }

    static String populateAlumno(Connection cn) throws Exception {
        String[] cols = {"id_escuela", "cod_alumno", "id_distrito", "ape_paterno", "ape_materno",
            "nombres", "email", "direccion", "id_plan_curricular"};
        ArrayList<String[]> a = new ArrayList<String[]>();
        a.add(new String[]{"1", "123", "1",
                    "Ramirez", "Alvarado", "Raúl Felipe",
                    "raulbrood@hotmail.com", "Jr. Antonio Raimondi 155", "1"});
        a.add(new String[]{"1", "1234", "2",
                    "Carreño", "Valerio", "Wilder Fernando", "wilfer@hotmail.com", " sta rosa Mzb 154583", "1"});
        a.add(new String[]{"1", "12", "3", "" +
                    "Bastidas", "Angeles", "Jonathan", "strike@hotmail.com", "pizarron", "1"});
        a.add(new String[]{"1", "1233", "4", "Pacora", "Changa",
                    "maritin", "yougayster@hotmail.com", "Jr Carquin 157", "1"});
        return new PopulatingMatricula().populateTable(cn, "t_alumno", cols, a);
    }
    String databaseName = "Matricula";
    static String path = "E:/Matricula/DB/INSERTS/allInserts.sql";

    public static void populateALL() throws Exception {
        Connection cn = null;
        cn = MasterConn.getConnection();
        String res = "";
//        res += populateUniv(cn);
//        res += populateFacultades(cn);
//        res += populateEscuelas(cn);
        res += populateTipoSancion(cn);
//        res += populateCiclo(cn);
//        res += populateTipoPago(cn);
//        res += populateAlumno(cn);
        BufferedWriter bf = new BufferedWriter(new FileWriter(new File(path)));
        bf.write(res);
        bf.close();
    }

    public static void main(String[] args) throws Exception {
        populateALL();
    }
}
