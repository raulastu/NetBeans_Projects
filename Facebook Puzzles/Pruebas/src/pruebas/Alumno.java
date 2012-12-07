/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

/**
 *
 * @author rC
 */
public class Alumno {

    int id_alumno;
    int id_escuela;
    String cod_alumno;
    String ape_pat;
    String ape_mat;
    String nombres;
    String email;
    String fecha;
    int id_distrito;
    String direccion;

    public Alumno() {
    }

    public Alumno(int id_alumno, int id_escuela, String cod_alumno, String ape_pat, String ape_mat, String nombres, String email, String fecha, int id_distrito, String direccion) {
        this.id_alumno = id_alumno;
        this.id_escuela = id_escuela;
        this.cod_alumno = cod_alumno;
        this.ape_pat = ape_pat;
        this.ape_mat = ape_mat;
        this.nombres = nombres;
        this.email = email;
        this.fecha = fecha;
        this.id_distrito = id_distrito;
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return id_alumno + " " + nombres;
    }
}
