package PaqueteLogicaDelNegocio;
public class Empleado
{
    private int empleado_id;
    private String apellidos;
    private String nombres;
    private String correo;
    private int sucursal_id;
    private int sindicato_id;
    private double sueldoBasico;
    private double bonoAdicional;
    private double sueldoTotal;
    private double descuentoAFP;
    private double descuentoSindical;
    private double descuentoTotal;
    private double sueldoNeto;

    public Empleado() {
    }

    public Empleado(int empleado_id) {
        this.empleado_id = empleado_id;
    }

    public Empleado(int empleado_id, String apellidos, String nombres, String correo, int sucursal_id, int sindicato_id, double sueldoBasico, double bonoAdicional, double sueldoTotal, double descuentoAFP, double descuentoSindical, double descuentoTotal, double sueldoNeto) {
        this.empleado_id = empleado_id;
        this.apellidos = apellidos;
        this.nombres = nombres;
        this.correo = correo;
        this.sucursal_id = sucursal_id;
        this.sindicato_id = sindicato_id;
        this.sueldoBasico = sueldoBasico;
        this.bonoAdicional = bonoAdicional;
        this.sueldoTotal = sueldoTotal;
        this.descuentoAFP = descuentoAFP;
        this.descuentoSindical = descuentoSindical;
        this.descuentoTotal = descuentoTotal;
        this.sueldoNeto = sueldoNeto;
    }

    Empleado(int aInt, String string, double aDouble, double aDouble0, double aDouble1, double aDouble2) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    Empleado(int aInt, String string, String string0, String string1, int aInt0, int aInt1, double aDouble) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

  
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }



    public int getEmpleado_id() {
        return empleado_id;
    }

    public void setEmpleado_id(int empleado_id) {
        this.empleado_id = empleado_id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public int getSindicato_id() {
        return sindicato_id;
    }

    public void setSindicato_id(int sindicato_id) {
        this.sindicato_id = sindicato_id;
    }

    public int getSucursal_id() {
        return sucursal_id;
    }

    public void setSucursal_id(int sucursal_id) {
        this.sucursal_id = sucursal_id;
    }

    public double getSueldoBasico() {
        return sueldoBasico;
    }

    public void setSueldoBasico(double sueldoBasico) {
        this.sueldoBasico = sueldoBasico;
    }




    public double getBonoAdicional() {
        return bonoAdicional;
    }

    public void setBonoAdicional() {
        if (sucursal_id==1)
        {
           this.bonoAdicional = sueldoBasico*0.10;
        }
        else
        {
            if (sucursal_id==2)
            {
             this.bonoAdicional = sueldoBasico*0.12;
            }
            else
            {
                this.bonoAdicional = sueldoBasico*0.15;
            }
        }
    }




     public double getSueldoTotal() {
        return sueldoTotal;
    }

    public void setSueldoTotal() {
        this.sueldoTotal = sueldoBasico+bonoAdicional;
    }

     public double getDescuentoAFP() {
        return descuentoAFP;
    }

    public void setDescuentoAFP() {
        this.descuentoAFP =sueldoTotal*0.12;
    }
        public double getDescuentoSindical() {
        return descuentoSindical;
    }

    public void setDescuentoSindical() {
        if(sindicato_id==1)
        {
        this.descuentoSindical =10.00;
        }
        else
        {
            if(sindicato_id==2)
            {
               this.descuentoSindical =12.00;
            }
            else
            {

            }
        }
    }


    public double getDescuentoTotal() {
        return descuentoTotal;
    }

    public void setDescuentoTotal() {
        this.descuentoTotal = descuentoAFP+descuentoSindical;
    }

     public double getSueldoNeto() {
        return sueldoNeto;
    }

    public void setSueldoNeto()
    {
        this.sueldoNeto = sueldoTotal-descuentoTotal;
    }


}