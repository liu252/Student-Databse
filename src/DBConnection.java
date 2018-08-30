import java.io.IOException;
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
    public void AddStudent() throws IOException
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
            insertStudent.setInt(1, addStudent.getStudentID());
            insertStudent.setString(2, addStudent.getFirstName());
            insertStudent.setString(3, addStudent.getLastName());
            insertStudent.setDouble(4, addStudent.getGpa());
            insertStudent.setString(5, addStudent.getMajor());
            insertStudent.setString(6, addStudent.getAdvisor());
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
    public void UpdateStudent() throws IOException
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
            updateStudentDB.setString(1, updateStudent.getMajor());
            updateStudentDB.setString(2, updateStudent.getAdvisor());
            updateStudentDB.setInt(3, updateStudent.getStudentID());
            updateStudentDB.executeUpdate();
            
            PreparedStatement updatedStudent = con.prepareStatement("SELECT * FROM STUDENT WHERE StudentId = ?");
            updatedStudent.clearParameters();
            updatedStudent.setInt(1, updateStudent.getStudentID());
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
    public void DeleteStudent() throws IOException
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
    public void SearchStudent() throws IOException
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
            if (searchStudent.getMajor() != null)
            {
                PreparedStatement majorSearch = con.prepareStatement("SELECT * FROM Student WHERE major = ?");
                majorSearch.clearParameters();
                majorSearch.setString(1, searchStudent.getMajor());
                ResultSet rs = majorSearch.executeQuery();
                
                System.out.printf("%-22s%-22s%-22s%-22s%-22s%-22s\n","Student ID","First Name","Last Name","GPA","Major","Advisor");
                while(rs.next())
                {
                    System.out.printf("%-22s%-22s%-22s%-22s%-22s%-22s\n",rs.getString("StudentId"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("GPA"),rs.getString("Major"),rs.getString("FacultyAdvisor"));
                }
            }
            else if (searchStudent.getAdvisor() != null)
            {
                PreparedStatement advisorSearch = con.prepareStatement("SELECT * FROM Student WHERE FacultyAdvisor = ?");
                advisorSearch.clearParameters();
                advisorSearch.setString(1, searchStudent.getAdvisor());
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
                gpaSearch.setDouble(1, searchStudent.getGpa());
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
