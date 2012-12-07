package myownautoshopproject;

public class Ford extends Car implements CarInterface{
    int year;
    int manufacturerDiscount;
   
    public Ford(int speed, double regularPrice, String color, int year, int manufacturerDiscount, 
            EngineInterface engine) {
        super(speed,regularPrice,color,engine);
        this.year=year;
        this.manufacturerDiscount=manufacturerDiscount;
    }
    public void setYear(int year){
        this.year=year;
    }
    public int getYear(){
        return year;
    }
    public void setManufacturerDiscount(int manDisc){
        this.manufacturerDiscount=manDisc;
    }
    public int getManufacturerDiscount(){
        return this.manufacturerDiscount;
    }
    public double getSalePrice(){
        return super.getSalePrice()-this.manufacturerDiscount;
    }      
    public int getSecForMaxSpeed(){        
        return super.getSpeed()/12;
    }
}
