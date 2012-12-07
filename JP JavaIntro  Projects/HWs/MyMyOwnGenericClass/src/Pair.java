public class Pair<P, S> {
    P first;  S second;
    
    public Pair(P f, S s) {
        first = f;  second = s;
        
    }
    
    public void setFirst(P f){
        first = f;
    }
    
    public P getFirst(){
        return first;
    }
    
    public void setSecond(S s){
        second = s;
    }
    
    public S getSecond(){
        return second;
    }
}
