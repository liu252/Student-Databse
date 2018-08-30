import java.io.IOException;

public class main
{
    public static void main (String argv[]) throws IOException
    {
        DBConnection connection = new DBConnection();
        while(true)
        {
            System.out.println("-------------------------------------------------");
    
            int menuSelection = connection.Connect();
    
            System.out.println("-------------------------------------------------");
            if (menuSelection == 1)
            {
                System.out.println("Displaying All Students in Database: ");
                connection.DisplayAllStudents();
            } else if (menuSelection == 2)
            {
                System.out.println("Welcome to Add Student Menu. Please Enter All Information About Student");
                connection.AddStudent();
            } else if (menuSelection == 3)
            {
                System.out.println("Welcome to Update Student Menu. Please Enter Student ID to Update Student");
                connection.UpdateStudent();
            } else if (menuSelection == 4)
            {
                System.out.println("Welcome to Delete Student Menu. Please Enter Student ID to Delete Student");
                connection.DeleteStudent();
            } else if (menuSelection == 5)
            {
                System.out.println("Welcome to Search Student Menu");
                connection.SearchStudent();
            }
            else if (menuSelection == 6)
            {
                System.out.println("Thank you for using this database");
                break;
            }
        }
        
    }

}
