
package myownautoshopproject;

/**
 *
 * @author Raul Ramirez
 */
public class Car implements CarInterface{    
    int speed;    
    double regularprice;
    String color;    
    EngineInterface engine;
    public Car(int speed, double regularprice, String color, EngineInterface engine) {
        this.color=color;
        this.regularprice=regularprice;
        this.speed=speed;
        this.engine=engine;
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
    public int getSecForMaxSpeed(){
        return 0;
    }
    public EngineInterface getEngine(){
        return engine;
    }
    /**Multiple Polymhorpism behavior */
    public void testEngine(EngineInterface engine){
        engine.test(getSecForMaxSpeed());
    }
}
