import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MenuSystem
{
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public int startMenu() throws IOException
    {
        
        System.out.println("Welcome to the Student Database!");
        System.out.println("-------------------------------------------------");
        System.out.println("Enter 1 to Display All Students");
        System.out.println("Enter 2 to Add a Student");
        System.out.println("Enter 3 to Update a Student's Information");
        System.out.println("Enter 4 to Remove a Student");
        System.out.println("Enter 5 to Search a Student's Information");
        System.out.println("Enter 6 to Quit");
    
        int menuSelection = 0;
        
        
        boolean menuInputValid = false;
        while(!menuInputValid)
        {
            String menuInput = br.readLine();
            try
            {
                menuSelection = Integer.parseInt(menuInput);
                if (menuSelection <= 6 && menuSelection >= 1)
                {
                    menuInputValid = true;
                }
                else
                {
                    System.out.println("Please enter a number between 1 and 6");
                }
            }
            catch (NumberFormatException e)
            {
                System.out.println(menuInput + " is not a valid integer number");
            }
        }
        return menuSelection;
        
    }
    
    public Student AddStudentMenu() throws IOException
    {
        Student addStudent = new Student();
        System.out.print("Please Enter Student's ID: ");
        int idInput = integerCheck();
        System.out.print("Please Enter Student's First Name: ");
        String fnInput = br.readLine();
        while(fnInput.length()>25)
        {
            System.out.println("Data Length Too Long! Please Enter a Shorter Name");
            fnInput = br.readLine();
        }
        System.out.print("Please Enter Student's Last Name: ");
        String lnInput = br.readLine();
        while(lnInput.length()>25)
        {
            System.out.println("Data Length Too Long! Please Enter a Shorter Name");
            lnInput = br.readLine();
        }
        System.out.print("Please Enter Student's GPA: ");
        double gpaInput = doubleCheck();
        while (gpaInput > 4 || gpaInput < 0)
        {
            System.out.println("Please Enter a GPA in the 4.0 Grade Range");
            gpaInput = doubleCheck();
        }
        System.out.print("Please Enter Student's Major: ");
        String majorInput = br.readLine();
        while(majorInput.length()>10)
        {
            System.out.println("Data Length Too Long! Please Use Abbreviation of Major (i.e. CPSC for Computer Science)");
            majorInput = br.readLine();
        }
        System.out.print("Please Enter Student's Advisor: ");
        String advisorInput = br.readLine();
        while(advisorInput.length()>25)
        {
            System.out.println("Data Length Too Long! Please Enter a Shorter Name");
            advisorInput = br.readLine();
        }
        
        
        addStudent.setStudentID(idInput);
        addStudent.setFirstName(fnInput);
        addStudent.setLastName(lnInput);
        addStudent.setGpa(gpaInput);
        addStudent.setMajor(majorInput);
        addStudent.setAdvisor(advisorInput);
        
        
        return addStudent;
    }
    
    public Student UpdateStudentMenu() throws IOException
    {
        Student updateStudent = new Student();
    
        System.out.println("Only Student's Major and Advisor may be updated");
        System.out.print("Please Enter Student's ID: ");
        int idInput = integerCheck();
        System.out.print("Please Enter Student's New Major: ");
        String majorInput = br.readLine();
        System.out.print("Please Enter Student's New Advisor: ");
        String advisorInput = br.readLine();
        
        updateStudent.setStudentID(idInput);
        updateStudent.setMajor(majorInput);
        updateStudent.setAdvisor(advisorInput);
        
        return updateStudent;
        
    }
    
    public int DeleteStudentMenu() throws IOException
    {
        System.out.print("Please Enter Student's ID: ");
        int idInput = integerCheck();
        
        return idInput;
    }
    
    public Student SearchStudentMenu() throws IOException
    {
        Student searchStudent = new Student();
        
        System.out.println("Enter 1 to Search By Major");
        System.out.println("Enter 2 to Search By GPA");
        System.out.println("Enter 3 to Search By Advisor");
    
        int searchOption = 0;
        boolean searchOptionValid = false;
        while(!searchOptionValid)
        {
            String searchInput = br.readLine();
            try
            {
                searchOption = Integer.parseInt(searchInput);
                if (searchOption <= 3 && searchOption >= 1)
                {
                    searchOptionValid = true;
                } else
                {
                    System.out.println("Please enter a number between 1 and 3");
                }
            } catch (NumberFormatException e)
            {
                System.out.println(searchInput + " is not a valid integer number");
            }
        }
        if (searchOption == 1)
        {
            System.out.print("Please Enter Student's Major: ");
            String majorInput = br.readLine();
            searchStudent.setMajor(majorInput);
        }
        else if (searchOption == 2)
        {
            System.out.print("Please Enter Student's GPA: ");
            double gpaInput = doubleCheck();
            searchStudent.setGpa(gpaInput);
        }
        else if (searchOption == 3)
        {
            System.out.print("Please Enter Student's Advisor: ");
            String advisorInput = br.readLine();
            searchStudent.setAdvisor(advisorInput);
        }
        
        
        return searchStudent;
    }
    
    private int integerCheck() throws IOException
    {
        int inputCheck = 0;
        boolean inputValid = false;
        while(!inputValid)
        {
            String input = br.readLine();
            try
            {
                inputCheck = Integer.parseInt(input);
                inputValid = true;
            }
            catch (NumberFormatException e)
            {
                System.out.println(input + " is not a valid integer number");
            }
        }
        return inputCheck;
        
    }
    
    
    private double doubleCheck() throws IOException
    {
        double inputCheck = 0.0;
        boolean inputValid = false;
        while(!inputValid)
        {
            String input = br.readLine();
            try
            {
                inputCheck = Double.parseDouble(input);
                inputValid = true;
            }
            catch (NumberFormatException e)
            {
                System.out.println(input + " is not a valid double number");
            }
        }
        return inputCheck;
    }
    
    
}
