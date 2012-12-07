
package myownautoshopproject;

public class Truck extends Car implements CarInterface{    
    int weight;
    
    public Truck(int speed,double regularprice, String color, int weight,
            EngineInterface engine) {
        super(speed,regularprice,color,engine);
        this.weight=weight;
    }
    public void setWeight(int weight){
        this.weight=weight;
    }
    public int getWeight(){
        return this.weight;
    }
    
    public double getSalePrice(){
        if(this.weight>2000){
            return super.getRegularPrice()*0.9;
        }        
        else
            return super.getRegularPrice()*0.8;
    }     
    public int getSecForMaxSpeed(){
        return super.getSpeed()/8;
    }
}
