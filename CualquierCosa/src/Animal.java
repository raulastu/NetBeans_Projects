public class Animal {

String tipo = " N   ";
String tamaño = "   ";
int edad = 50;

void selva(){
   System.out.println("el tipo es =" + tipo + "el tamaño es =" + tamaño + "la edad es =" + edad);
}
void subirEdad() {
    edad=edad+1;
    selva();
}

public static void main (String[] args)
{ Animal leon = new Animal();
    leon.selva();
  Animal mono = new Animal();
        mono.edad = 10;
    mono.subirEdad();
  
        mono.edad = 20;
    mono.subirEdad();

        mono.edad = 30;
    mono.subirEdad();
 
}       
}
