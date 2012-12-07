package myownautoshopproject;

public class GasolineEngine implements EngineInterface{
    public void test(int sec){
        System.out.print("GasolineEngine :: ");                   
        for(int i=0;i<sec;i++){
            System.out.print("rr~");            
        }     
        System.out.println();
    }    
}
