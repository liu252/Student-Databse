public class Student
{
    public int studentID;
    public String firstName;
    public String lastName;
    public double gpa;
    public String major;
    public String advisor;
    
    public Student()
    {
    
    }
    
    public Student (String fn, String ln, double g, String m, String a)
    {
        firstName = fn;
        lastName = ln;
        gpa = g;
        major = m;
        advisor = a;
        
    }
}
