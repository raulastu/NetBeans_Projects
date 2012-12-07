
package myownautoshopproject;

public class Truck extends Car{    
    int weight;
    
    public Truck(int speed, String color, double regularprice, int weight) {
        super(speed,regularprice,color);
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
    
}
