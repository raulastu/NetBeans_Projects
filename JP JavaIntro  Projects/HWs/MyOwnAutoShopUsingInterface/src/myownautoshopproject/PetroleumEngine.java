
package myownautoshopproject;

public class PetroleumEngine implements EngineInterface{    
    public void test(int sec){
        System.out.print(" PetroleumEngine :: ");   
        for(int i=0;i<sec;i++){
            System.out.print("BR~");            
        }      
        System.out.println();        
    }    
}
