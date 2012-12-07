
package myownautoshopproject;

/**
 *
 * @author Raul Ramirez
 */
public class Car {
    
    int speed;    
    double regularprice;
    String color;
    
    
    public Car(int speed, double regularprice, String color) {
        this.color=color;
        this.regularprice=regularprice;
        this.speed=speed;
    }
    public void setSpeed(int speed){
        this.speed=speed;
    }
    public int getSpeed(){
        return this.speed;
    }
    public void setRegularPrice(double regularPrice){
        this.regularprice=regularPrice;
    }
    public double getRegularPrice(){
        return this.regularprice;
    }
    public void setColor(String color){
        this.color=color;
    }
    public String getColor(){
        return this.color;
    }
    public double getSalePrice(){
        return this.regularprice*0.95;
    }    
}
