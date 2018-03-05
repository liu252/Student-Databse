import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBConnection
{
    private static Connection con;
    private MenuSystem menu = new MenuSystem();
    public int Connect()
    {
        int menuSelection = 0;
        try
        {
            con = DBConfig.GetConnection();
            if(con.isClosed())
            {
                System.out.println("Connection was closed; creating new connection to MySQL.");
                con = DBConfig.GetConnection();
            }
            menuSelection = menu.startMenu();
            
        
        
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return menuSelection;
    }
    public void DisplayAllStudents()
    {
        try
        {
            if(con.isClosed())
            {
                System.out.println("Connection was closed; creating new connection to MySQL.");
                con = DBConfig.GetConnection();
            }

            PreparedStatement pst = con.prepareStatement("SELECT  * FROM Student");
            ResultSet rs = pst.executeQuery();
    
            System.out.printf("%-22s%-22s%-22s%-22s%-22s%-22s\n","Student ID","First Name","Last Name","GPA","Major","Advisor");
            while(rs.next())
            {
                System.out.printf("%-22s%-22s%-22s%-22s%-22s%-22s\n",rs.getString("StudentId"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("GPA"),rs.getString("Major"),rs.getString("FacultyAdvisor"));
            }
            
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        
        finally
        {
            try
            {
                if(con != null) con.close();
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }
    public void AddStudent()
    {
        Student addStudent = menu.AddStudentMenu();
        try
        {
            if (con.isClosed())
            {
                System.out.println("Connection was closed; creating new connection to MySQL.");
                con = DBConfig.GetConnection();
            }
            PreparedStatement insertStudent = con.prepareStatement("INSERT INTO Student(StudentId, FirstName, LastName, GPA, Major, FacultyAdvisor) VALUES(?,?,?,?,?,?)");
            insertStudent.clearParameters();
            insertStudent.setInt(1, addStudent.studentID);
            insertStudent.setString(2, addStudent.firstName);
            insertStudent.setString(3, addStudent.lastName);
            insertStudent.setDouble(4, addStudent.gpa);
            insertStudent.setString(5, addStudent.major);
            insertStudent.setString(6, addStudent.advisor);
            insertStudent.executeUpdate();
            
            System.out.println("Student Added Successfully!");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            try
            {
                if(con != null) con.close();
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
        
    }
    public void UpdateStudent()
    {
        Student updateStudent = menu.UpdateStudentMenu();
        try
        {
            if (con.isClosed())
            {
                System.out.println("Connection was closed; creating new connection to MySQL.");
                con = DBConfig.GetConnection();
            }
            
            PreparedStatement updateStudentDB = con.prepareStatement("UPDATE Student SET Major = ?, FacultyAdvisor = ? WHERE StudentId = ?");
            updateStudentDB.clearParameters();
            updateStudentDB.setString(1, updateStudent.major);
            updateStudentDB.setString(2, updateStudent.advisor);
            updateStudentDB.setInt(3, updateStudent.studentID);
            updateStudentDB.executeUpdate();
            
            PreparedStatement updatedStudent = con.prepareStatement("SELECT * FROM STUDENT WHERE StudentId = ?");
            updatedStudent.clearParameters();
            updatedStudent.setInt(1, updateStudent.studentID);
            ResultSet rs = updatedStudent.executeQuery();
            
            if (rs.next())
            {
                System.out.println("Student Updated Successfully!");
            }
            else
            {
                System.out.println("Update Unsuccessful Student ID Not Found");
            }
            
            
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            try
            {
                if(con != null) con.close();
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
        
    }
    public void DeleteStudent()
    {
        int studentID = menu.DeleteStudentMenu();
        try
        {
            if (con.isClosed())
            {
                System.out.println("Connection was closed; creating new connection to MySQL.");
                con = DBConfig.GetConnection();
            }
            
            PreparedStatement deletedStudent = con.prepareStatement("SELECT * FROM STUDENT WHERE StudentId = ?");
            deletedStudent.clearParameters();
            deletedStudent.setInt(1, studentID);
            ResultSet rs = deletedStudent.executeQuery();
            
            
            PreparedStatement deleteStudent = con.prepareStatement("DELETE FROM Student WHERE StudentId = ?");
            deleteStudent.clearParameters();
            deleteStudent.setInt(1, studentID);
            deleteStudent.executeUpdate();
            
    
            if (rs.next())
            {
                System.out.println("Student Deleted Successfully!");
            }
            else
            {
                System.out.println("Delete Unsuccessful Student ID Not Found");
            }
            
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            try
            {
                if(con != null) con.close();
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }
    public void SearchStudent()
    {
        Student searchStudent = menu.SearchStudentMenu();
        try
        {
            if (con.isClosed())
            {
                System.out.println("Connection was closed; creating new connection to MySQL.");
                con = DBConfig.GetConnection();
            }
            System.out.println("Search Results: ");
            if (searchStudent.major != null)
            {
                PreparedStatement majorSearch = con.prepareStatement("SELECT * FROM Student WHERE major = ?");
                majorSearch.clearParameters();
                majorSearch.setString(1, searchStudent.major);
                ResultSet rs = majorSearch.executeQuery();
                
                System.out.printf("%-22s%-22s%-22s%-22s%-22s%-22s\n","Student ID","First Name","Last Name","GPA","Major","Advisor");
                while(rs.next())
                {
                    System.out.printf("%-22s%-22s%-22s%-22s%-22s%-22s\n",rs.getString("StudentId"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("GPA"),rs.getString("Major"),rs.getString("FacultyAdvisor"));
                }
            }
            else if (searchStudent.advisor != null)
            {
                PreparedStatement advisorSearch = con.prepareStatement("SELECT * FROM Student WHERE FacultyAdvisor = ?");
                advisorSearch.clearParameters();
                advisorSearch.setString(1, searchStudent.advisor);
                ResultSet rs = advisorSearch.executeQuery();
    
                System.out.printf("%-22s%-22s%-22s%-22s%-22s%-22s\n","Student ID","First Name","Last Name","GPA","Major","Advisor");
                while(rs.next())
                {
                    System.out.printf("%-22s%-22s%-22s%-22s%-22s%-22s\n",rs.getString("StudentId"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("GPA"),rs.getString("Major"),rs.getString("FacultyAdvisor"));
                }
            }
            else
            {
                PreparedStatement gpaSearch = con.prepareStatement("SELECT * FROM Student WHERE gpa = ?");
                gpaSearch.clearParameters();
                gpaSearch.setDouble(1, searchStudent.gpa);
                ResultSet rs = gpaSearch.executeQuery();
    
                System.out.printf("%-22s%-22s%-22s%-22s%-22s%-22s\n","Student ID","First Name","Last Name","GPA","Major","Advisor");
                while(rs.next())
                {
                    System.out.printf("%-22s%-22s%-22s%-22s%-22s%-22s\n",rs.getString("StudentId"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("GPA"),rs.getString("Major"),rs.getString("FacultyAdvisor"));
                }
            }
        
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            try
            {
                if(con != null) con.close();
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
        
    }
}
