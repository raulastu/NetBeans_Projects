

package myownautoshopproject;

public class Ford extends Car{
    int year;
    int manufacturerDiscount;
   
    public Ford(int speed, double regularPrice, String color, 
            int year, int manufacturerDiscount) {
        super(speed,regularPrice,color);
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
    
}
