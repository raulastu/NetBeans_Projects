
public class PairExtendedAgain<F,S,T,F1> extends PairExtended<F, S, T> {
    
    F1 fourth;
    public PairExtendedAgain(F f,S s,T t,F1 f1) {
        super(f,s,t);
        fourth=f1;
        
    }
    public F1 getFourth(){
        return fourth;
    }
    
}
