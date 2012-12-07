
package mypersoninterfaceproject;

public class Main {
    
    public static void main(String[] args) {             
        
        Person person1=new Person(5000,10000,"Raúl","Ramírez Alvarado",
                new AddressImpl(155,"Antonio Raymondi","Perú"));
        
        System.out.println("Nombre :"+ person1.getName()+","+
                " ComputeTotalWealth = "+person1.computeTotalWealth()+","+
                " MeasureIntelligence = "+person1.measureIntelligence(person1.getName())+","+
                " FirstNameLenght = "+person1.computeFirstNameLenght(person1.getName()));
        System.out.println("--Address--");
        System.out.println("Country = " +person1.getAddress().getCountry()+
                " \nStreet Name = "+person1.getAddress().getStreetName()+
                " \nStreet Number = "+person1.getAddress().getStreetNumber());
                
    }    
}
