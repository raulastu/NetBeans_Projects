public class StudentRecord {
    
    /** Creates a new instance of StudentRecord */
    public StudentRecord() {
    }
    
    // Declare instance variables.
    private String name;
    private double mathGrade;
    private double englishGrade;
    private double scienceGrade;
    
    public String getName(){
        return name;
    }
    
    /**
     * Changes the name of the student
     */
    public void setName(String temp ){
        name =temp;
    }
    
    /**
     * Computes the average of the english, math and science
     * grades
     */
    public double getAverage(){
        double result =0;
        result =(getMathGrade()+getEnglishGrade()+getScienceGrade() )/3;
        return result;
    }   

    // Instance methods 
    public double getMathGrade() {
        return mathGrade;
    }

    public void setMathGrade(double mathGrade) {
        this.mathGrade = mathGrade;
    }

    public double getEnglishGrade() {
        return englishGrade;
    }

    public void setEnglishGrade(double englishGrade) {
        this.englishGrade = englishGrade;
    }

    public double getScienceGrade() {
        return scienceGrade;
    }

    public void setScienceGrade(double scienceGrade) {
        this.scienceGrade = scienceGrade;
    }
} 
