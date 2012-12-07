/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.*;
import java.util.regex.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;

public class Main {

    Main() throws Exception {
    }

    public static void main(String[] args) throws Exception {
        DB database = new DB();
        Concurso concurso = new Concurso(database);
        concurso.ingresarSedes(3);
        concurso.ingresarModalidades(3);
        concurso.ingresarProcesos(1);
        concurso.crearConcurso(1, 1, 1, 1);//n,id_proceso,id_sede,id_modalidad
        concurso.crearConcurso(1, 1, 1, 2);
        Escuela escuela = new Escuela(database);
        escuela.ingresarFacultades(3);
        escuela.ingresarEscuelas(10);

        int idConcurso=1;
        Postulante p = new Postulante(database);
        p.ingresar(idConcurso,5000);
        p.insertarPabellones(idConcurso);
        p.setAulas(26, 1, 1); //enviar alumnos por aula
        p.shufflePostulantes();

        //hojas
        Hojas hojas = new Hojas(database);
        hojas.ingresarHIs(1);
        hojas.ingresarHRs(1, 120);
        database.closeDB();
    }
}
