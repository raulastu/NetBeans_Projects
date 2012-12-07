package myownautoshopproject;

public interface CarInterface {
        
    public int getSpeed();
    public void setSpeed(int speed);    
    
    public double getRegularPrice();
    public void setRegularPrice(double regularPrice);
    
    public String getColor();
    public void setColor(String color);
    
    public double getSalePrice();
    public int getSecForMaxSpeed();
    
    /** Multiple Polymorphism */
    public void testEngine(EngineInterface engine);
    
}
