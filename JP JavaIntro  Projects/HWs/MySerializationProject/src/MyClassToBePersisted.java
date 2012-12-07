import java.io.*;
public class MyClassToBePersisted implements Serializable{  
    static final long serialVersionUID=93923914L;
    private String name;
    private int age;
    private String hobby;
    private double stature;
       
    private String nameOfSchool;
    transient private int yearStarted;
    
    public MyClassToBePersisted(String name, int age, String hobby, String nos,int yearStarted) {
        this.name=name;
        this.age=age;
        this.hobby=hobby;
        this.nameOfSchool=nos;
        this.yearStarted=yearStarted;
        this.stature=1.70;
    }
    public String toString(){
        return "Profile:\n name: "+name+" age: "+age+" hobby: "+hobby +"Stature:"+stature
                +"\nSchool :\n Name of School: "+nameOfSchool+" yearStarted: "+yearStarted;
    }
    
}
