import java.io.*;

public class Math2
{
   public static String leerCadena()
   {                
	  char c[]=new char[80];
	  try {
	  int i=0;
	  do{
		 c[i]=(char)System.in.read(); //read() produce IOExceptions
	  }while((char)c[i++]!='\n');
	  }
	  catch(IOException e){c[0]=' ';}
	  String str=new String(c,0,c.length);
	  str=str.trim();
	  return str;
   }         
   public static char leerCaracter() 
   {                
	  char c[]=new char[80];
	  char a;

	  try{
	  int i=0;
	  do{
		 c[i]=(char)System.in.read(); //read() produce IOExceptions
	  }while((char)c[i++]!='\n');
	  String str=new String(c,0,c.length);
	  str=str.trim();
	  if(str.length()>0)
		 a=str.charAt(0);
	  else
		 a=' ';
	  }
	  catch(IOException e){a=' ';}
	  return a;
   }         
   public static int leerNumeroEntero() 
   {                
	  char c[]=new char[80];
	  try {
	  int i=0;
	  do{
		 c[i]=(char)System.in.read(); //read() produce IOExceptions
	  }while((char)c[i++]!='\n');
	  }
	  catch(IOException e){c[0]=' ';}
	  String str=new String(c,0,c.length);
	  str=str.trim();
	  Integer objX=new Integer(str);
	  int x=objX.intValue();
	  return x;
   }         
   public static long leerNumeroEnteroLargo()
   {                
	  char c[]=new char[80];
	  try {
	  int i=0;
	  do{
		 c[i]=(char)System.in.read(); //read() produce IOExceptions
	  }while((char)c[i++]!='\n');
	  }
	  catch(IOException e){c[0]=' ';}

	  String str=new String(c,0,c.length);
	  str=str.trim();
	  Long objX=new Long(str);
	  long x=objX.longValue();
	  return x;
   }         
   public static double leerNumeroRealDoble()
   {                
	  char c[]=new char[80];
	  try {
	  int i=0;
	  do{
		 c[i]=(char)System.in.read(); //read() produce IOExceptions
	  }while((char)c[i++]!='\n');
	  }
	  catch(IOException e){c[0]=' ';}

	  String str=new String(c,0,c.length);
	  str=str.trim();
	  Double objX=new Double(str);
	  double x=objX.doubleValue();
	  return x;
   }         
   public static double leerNumeroRealSimple() 
   {                
	  char c[]=new char[80];
	  try {
	  int i=0;
	  do{
		 c[i]=(char)System.in.read(); //read() produce IOExceptions
	  }while((char)c[i++]!='\n');
	  }
	  catch(IOException e){c[0]=' ';}

	  String str=new String(c,0,c.length);
	  str=str.trim();
	  Float objX=new Float(str);
	  float x=objX.floatValue();
	  return x;
   }         
}