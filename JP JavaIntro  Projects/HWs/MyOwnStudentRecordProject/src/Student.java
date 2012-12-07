
public class Student {
    
    private StudentRecord studentRecord;
    private int studentId;
    private static int studentCount = 0;
   
    public int getStudentId(){
        return studentId;
    }
    public StudentRecord getStudentRecord(){
       return studentRecord;
    }
   
    public Student(String name) {
       studentRecord=new StudentRecord();
       studentRecord.setName(name);
    }
    public Student(int studentId,String name){
        this(name);
        this.studentId=studentId;        
    }
    public Student(int studentId,String name, double eGrade, double sGrade, double mGrade ){
       this(studentId,name);
       studentRecord.setEnglishGrade(eGrade);
       studentRecord.setScienceGrade(sGrade);
       studentRecord.setMathGrade(mGrade);
   }
    public static int getStudentCount(){
        return studentCount;
    }
     public static void increaseStudentCount(){
        studentCount++;
    }
}
