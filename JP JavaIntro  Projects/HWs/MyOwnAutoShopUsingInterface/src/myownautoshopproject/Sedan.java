package myownautoshopproject;

public class Sedan extends Car implements CarInterface{
    int length;
    
    public Sedan(int speed, double regularPrice, String color,int length,
            EngineInterface engine) {
        super(speed,regularPrice,color,engine);
        this.length=length;
    }
    public void setLength(int length){
        this.length=length;
    }
    public int getLength(){
        return this.length;
    }
    public double getSalePrice(){
        if(this.length>20)
            return super.getRegularPrice()*0.95;        
        else
            return super.getRegularPrice()*0.9;            
    } 
    public int getSecForMaxSpeed(){
        return super.getSpeed()/10;
    }
    
}
