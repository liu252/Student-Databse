public class Student
{
    private int studentID;
    private String firstName;
    private String lastName;
    private double gpa;
    private String major;
    private String advisor;
    
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
    
    public int getStudentID()
    {
        return studentID;
    }
    
    public void setStudentID(int studentID)
    {
        this.studentID = studentID;
    }
    
    public String getFirstName()
    {
        return firstName;
    }
    
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }
    
    public String getLastName()
    {
        return lastName;
    }
    
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }
    
    public double getGpa()
    {
        return gpa;
    }
    
    public void setGpa(double gpa)
    {
        this.gpa = gpa;
    }
    
    public String getMajor()
    {
        return major;
    }
    
    public void setMajor(String major)
    {
        this.major = major;
    }
    
    public String getAdvisor()
    {
        return advisor;
    }
    
    public void setAdvisor(String advisor)
    {
        this.advisor = advisor;
    }
}
