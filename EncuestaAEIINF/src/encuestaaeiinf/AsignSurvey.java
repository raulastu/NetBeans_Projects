
package encuestaaeiinf;

/**
 *
 * @author rC
 */
public class AsignSurvey {
    private String id;
    private String cicloAcad;
    private String idAsignatura;    
    int q1,q2,q3,q4,q5,q6,q7,q8,q9,q10,q11;    
    public AsignSurvey(String id, String cicloAcad, String idAsignatura,
            int q1, int q2, int q3, int q4, int q5, int q6,
            int q7, int q8, int q9, int q10, int q11) {        
        this.id=id;
        this.cicloAcad=cicloAcad;
        this.idAsignatura=idAsignatura;
        this.q1=q1;this.q2=q2;
        this.q3=q3;this.q4=q4;
        this.q5=q5;this.q6=q6;
        this.q8=q8;this.q8=q8;
        this.q9=q9;this.q10=q10;
        this.q11=q11;
    }
    
    
}
