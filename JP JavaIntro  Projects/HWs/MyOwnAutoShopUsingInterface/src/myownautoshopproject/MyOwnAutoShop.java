package myownautoshopproject;

public class MyOwnAutoShop {
     
    public static void main(String []args){
        
        Car car[]=new Car[7];
        car[0]=new Sedan(120,20000.00,"blue",15, new GasolineEngine());
        car[1]=new Ford(130,15000.20,"green",1990,20, new GasolineEngine());
        car[2]=new Ford(150,12000.90,"Black",1996,15, new PetroleumEngine());
        car[3]=new Car(100,10000.00,"white", new PetroleumEngine());        
        car[4]=new Truck(70,8000,"blueblue",21, new GasolineEngine());
        car[5]=new Truck(3,8000,"greendarker",25, new PetroleumEngine());        
        car[6]=new Car(100,8000,"gray",new PetroleumEngine());
        
        double totalRegularPrice=0;
        double totalSalePrice=0;
        
        /**Car class sale price factor = 0.95*/
        
        for(int i=0;i<car.length;i++){
            
            System.out.println("CarName:" + car[i].getClass().getName().substring(car[i].getClass().getName().lastIndexOf(".")+1) +
                    " RegularPrice: " + car[i].getRegularPrice() +
                    " SalePrice: "+ car[i].getSalePrice());
            System.out.print("Test Engine : ");
            
            /**Multiple polymorphism is when an abstract class uses another abstract class
             * (source :pre-reading class - Java Tip 30 JavaWorld.com -)
             *
             *in this call we can see it Multiple polymorphism behavior            
             */
            car[i].testEngine(car[i].getEngine());
            
            totalRegularPrice+=car[i].getRegularPrice();
            totalSalePrice+=car[i].getSalePrice();
        }
        System.out.println("Total Regular Price:"+ totalRegularPrice);
        System.out.println("Total Sale Price:"+ totalSalePrice);        
    }    
}
