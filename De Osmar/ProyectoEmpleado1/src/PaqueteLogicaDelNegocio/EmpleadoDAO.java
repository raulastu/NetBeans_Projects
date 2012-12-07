package PaqueteLogicaDelNegocio;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.util.Vector;
public class EmpleadoDAO
  { 
    public boolean insertarEmpleado(Empleado oEmpleado)
      {
        Connection conexion = ConexionConBaseDeDatos.obtenerConexion();    
        try 
          {	
            Statement sentencia= conexion.createStatement();
            sentencia.executeUpdate("INSERT INTO Empleado(empleado_id,apellidos,nombres,correo,sucursal_id,sindicato_id,sueldo_basico) VALUES ("+oEmpleado.getEmpleado_id()+",'"+
                                                                  oEmpleado.getApellidos()+"','"+
                                                                  oEmpleado.getNombres()+"','"+
                                                                  oEmpleado.getCorreo()+"',"+
                                                                  oEmpleado.getSucursal_id()+",+" +
                                                                  oEmpleado.getSindicato_id()+",+"+
                                                                  oEmpleado.getSueldoBasico()+")");
            conexion.close();
            return true;
          } 
      	catch (Exception e) 
          {	
            System.out.print("Error ....x"+e);
            return false;
          }
        }
    
      public boolean eliminarEmpleado(Empleado oEmpleado)
        { Connection conexion;       
          conexion = ConexionConBaseDeDatos.obtenerConexion();    
          try 	
            { int cantidadDeFilasAfectadas;
              Statement sentencia=conexion.createStatement();                                              
              cantidadDeFilasAfectadas = sentencia.executeUpdate("DELETE FROM Empleado WHERE empleado_id="+oEmpleado.getEmpleado_id());
              conexion.close();
              if(cantidadDeFilasAfectadas==0)
                {   
                  return false;
                }
              else 
                {
                  return true;
                }
            } 
          catch (Exception e) 
            { System.out.print("Error ...."+e);
              return false;
            }        
        }
    
      public Vector<Empleado> obtenerDatosEnVectorEmpleado()
    	{ Connection conexion;       
          conexion = ConexionConBaseDeDatos.obtenerConexion();   
          try
            { Vector<Empleado> oVector = new Vector<Empleado>();
              Empleado oEmpleadoTemporal;
              ResultSet resultado;                                  
              Statement sentencia = conexion.createStatement();
              resultado=sentencia.executeQuery("SELECT * FROM Empleado");
              if(resultado.next())
                {  do 
                    { oEmpleadoTemporal = new Empleado( resultado.getInt("empleado_id"),
                                                    resultado.getString("apellidos"),
                                                    resultado.getString("nombres"),
                                                    resultado.getString("correo"),
                                                    resultado.getInt("sucursal_id"),
                                                    resultado.getInt("sindicato_id"),
                                                    resultado.getDouble("sueldo_basico")
                                                   );                             
                       oVector.addElement(oEmpleadoTemporal);
                     }
                   while(resultado.next());                
                   return oVector;
                  }
               else
                  {
                    return null;
                  }            
                } 
            catch (Exception e) 
      		{ System.out.print("Error ...."+e);
                  return null;
        	}
            }        

        public boolean modificarEmpleado(Empleado oEmpleado)
          {   
            Connection conexion;  
            conexion = ConexionConBaseDeDatos.obtenerConexion();             
              try
      	  	{   
                  int cantidadDeFilasAfectadas;
                  Statement sentencia= conexion.createStatement();                
                  cantidadDeFilasAfectadas=sentencia.executeUpdate("UPDATE Empleado SET apellidos='"+oEmpleado.getApellidos()+
                      "',nombres="+oEmpleado.getNombres()+
                      ",correo="+oEmpleado.getCorreo()+
                      ",sucursal_id="+oEmpleado.getSucursal_id()+
                      ",sindicato_id="+oEmpleado.getSindicato_id()+
                      ",sueldo_basico="+oEmpleado.getSueldoBasico()+
                      ",bonoAdicional="+oEmpleado.getBonoAdicional()+
                      ",sueldoTotal="+oEmpleado.getSueldoTotal()+
                      ",descuentoAFP"+oEmpleado.getDescuentoAFP()+
                      ",descuentoSindical="+oEmpleado.getDescuentoSindical()+
                      ",descuentoTotal="+oEmpleado.getDescuentoTotal()+
                      ",sueldoNeto="+oEmpleado.getSueldoNeto()+
                      " where empleado_id="+oEmpleado.getEmpleado_id());
              conexion.close();
              if(cantidadDeFilasAfectadas==0)
            			{
            				return false;
            	}
            else 
            			{   return true;
           	}
        	} 
      	  catch (Exception e) 
        	{	                              System.out.print("Error ...."+e);
            return false;
        	}               
    	}
  
    public Empleado buscarEmpleado(Empleado oEmpleado)
    	{  
      Connection conexion; 
        conexion = ConexionConBaseDeDatos.obtenerConexion();       
      try 
        	{                 ResultSet resultado;            
      	      Empleado oEmpleadoTemporal;
      	    Statement sentencia= conexion.createStatement();
          
    
      	      resultado=sentencia.executeQuery("SELECT * FROM Empleado WHERE empleado_id = "+oEmpleado.getEmpleado_id());
            oEmpleadoTemporal = new Empleado();
            if(resultado.next())
      	      	{   oEmpleadoTemporal.setEmpleado_id(resultado.getInt("empleado_id"));
                    oEmpleadoTemporal.setApellidos(resultado.getString("apellidos"));
                    oEmpleadoTemporal.setCorreo(resultado.getString("correo"));
                    oEmpleadoTemporal.setSindicato_id(resultado.getInt("sucursal_id"));
                    oEmpleadoTemporal.setSindicato_id(resultado.getInt("sindicato_id"));
                    oEmpleadoTemporal.setSueldoBasico(resultado.getDouble("sueldo_basico"));
                    oEmpleadoTemporal.setBonoAdicional();
                    oEmpleadoTemporal.setSueldoTotal();
                    oEmpleadoTemporal.setDescuentoAFP();
                    oEmpleadoTemporal.setDescuentoSindical();
                    oEmpleadoTemporal.setDescuentoTotal();
                    oEmpleadoTemporal.setSueldoNeto();
                    resultado.close();
                    conexion.close();
                    return oEmpleadoTemporal;
            		}
            else
      	      	{   
return null;
            		}            
    		} 
        	catch (Exception e) 
        		{   	
                        System.out.print("Error ...."+e);
return null;
        		}
    		}
    public boolean procesarCalcularPlanilla()
      {
        Connection conexion;
        conexion = ConexionConBaseDeDatos.obtenerConexion();       
        try
          { Empleado oEmpleadoTemporal=new Empleado();
            Statement sentencia = conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                                                           ResultSet.CONCUR_UPDATABLE);
            ResultSet resultado = sentencia.executeQuery("SELECT * FROM empleado");
            while (resultado.next()) 
              { oEmpleadoTemporal.setSucursal_id(resultado.getInt("sucursal_id"));
                oEmpleadoTemporal.setSindicato_id(resultado.getInt("sindicato_id"));
                oEmpleadoTemporal.setSueldoBasico(resultado.getDouble("sueldo_basico"));
                oEmpleadoTemporal.setBonoAdicional();
                resultado.updateDouble("bonoAdicional",oEmpleadoTemporal.getBonoAdicional());
                oEmpleadoTemporal.setSueldoTotal();
                resultado.updateDouble("sueldoTotal",oEmpleadoTemporal.getSueldoTotal());
                oEmpleadoTemporal.setDescuentoAFP();
                resultado.updateDouble("descuentoAFP",oEmpleadoTemporal.getDescuentoAFP());
                oEmpleadoTemporal.setDescuentoSindical();
                resultado.updateDouble("descuentoSindical",oEmpleadoTemporal.getDescuentoSindical());
                oEmpleadoTemporal.setDescuentoTotal();
                resultado.updateDouble("descuentoTotal",oEmpleadoTemporal.getDescuentoTotal());
                oEmpleadoTemporal.setSueldoNeto();
                resultado.updateDouble("sueldoNeto",oEmpleadoTemporal.getSueldoNeto());
                resultado.updateRow();
							}	
            resultado.close();
            sentencia.close();
            conexion.close();
            return true;
          } 
        catch (Exception e) 
       		{   	
            System.out.print("Error ...."+e);           
            return false ;
       		}
       }
	}

