public class Main {
    
    public static void main(String[] args) {
        
        Student raul=new Student("Raul");
        Student.increaseStudentCount();
        Student diana=new Student(100,"Diana");
        Student.increaseStudentCount();
        Student dante=new Student(101,"Dante",50.0,96.0,78.33);
        Student.increaseStudentCount();
        
        System.out.println("Student "+ raul.getStudentRecord().getName() +
                ": studentId "+raul.getStudentId()+ ", AverageRecord "+raul.getStudentRecord().getAverage());
        
        System.out.println("Student "+ diana.getStudentRecord().getName() +
                ": studentId "+diana.getStudentId()+ ", AverageRecord "+diana.getStudentRecord().getAverage());
        
        System.out.println("Student "+ dante.getStudentRecord().getName() +
                ": studentId "+dante.getStudentId()+ ", AverageRecord "+dante.getStudentRecord().getAverage());
        
        System.out.println("StudentCount = " + Student.getStudentCount());
        
    }
    
}