package myownautoshopproject;

public class MyOwnAutoShop {
        
    public MyOwnAutoShop() {
    }
    public static void main(String []args){
        
        Car car[]=new Car[4];
        car[0]=new Sedan(120,20000.00,"blue",15);
        car[1]=new Ford(130,15000.20,"green",1990,20);
        car[2]=new Ford(150,12000.90,"Black",1996,15);
        car[3]=new Car(100,10000.00,"white");        
        
        double totalRegularPrice=0;
        double totalSalePrice=0;
        
        /**Car class sale price factor = 0.95*/
        
        for(int i=0;i<car.length;i++){
            
            System.out.println("Car specification:" + car[i].getClass().getName().substring(car[i].getClass().getName().lastIndexOf(".")+1) +
                    " Regular Price: " + car[i].getRegularPrice() +
                    " Sale Price: "+ car[i].getSalePrice());
            totalRegularPrice+=car[i].getRegularPrice();
            totalSalePrice+=car[i].getSalePrice();
        }
        System.out.println("Total Regular Price:"+ totalRegularPrice);
        System.out.println("Total Sale Price:"+ totalSalePrice);        
    }    
}
