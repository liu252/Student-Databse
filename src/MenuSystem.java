import java.util.Scanner;

public class MenuSystem
{
    private Scanner sc = new Scanner(System.in);
    public int startMenu()
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
            String menuInput = sc.nextLine();
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
    
    public Student AddStudentMenu()
    {
        Student addStudent = new Student();
        System.out.print("Please Enter Student's ID: ");
        int idInput = integerCheck();
        System.out.print("Please Enter Student's First Name: ");
        String fnInput = sc.nextLine();
        System.out.print("Please Enter Student's Last Name: ");
        String lnInput = sc.nextLine();
        System.out.print("Please Enter Student's GPA: ");
        double gpaInput = doubleCheck();
        System.out.print("Please Enter Student's Major: ");
        String majorInput = sc.nextLine();
        System.out.print("Please Enter Student's Advisor: ");
        String advisorInput = sc.nextLine();
        
        
        addStudent.studentID = idInput;
        addStudent.firstName = fnInput;
        addStudent.lastName = lnInput;
        addStudent.gpa = gpaInput;
        addStudent.major = majorInput;
        addStudent.advisor = advisorInput;
        
        
        return addStudent;
    }
    
    public Student UpdateStudentMenu()
    {
        Student updateStudent = new Student();
    
        System.out.println("Only Student's Major and Advisor may be updated");
        System.out.print("Please Enter Student's ID: ");
        int idInput = integerCheck();
        System.out.print("Please Enter Student's New Major: ");
        String majorInput = sc.nextLine();
        System.out.print("Please Enter Student's New Advisor: ");
        String advisorInput = sc.nextLine();
        
        updateStudent.studentID = idInput;
        updateStudent.major = majorInput;
        updateStudent.advisor = advisorInput;
        
        return updateStudent;
    }
    
    public int DeleteStudentMenu()
    {
        System.out.print("Please Enter Student's ID: ");
        int idInput = integerCheck();
        
        return idInput;
    }
    
    public Student SearchStudentMenu()
    {
        Student searchStudent = new Student();
        
        System.out.println("Enter 1 to Search By Major");
        System.out.println("Enter 2 to Search By GPA");
        System.out.println("Enter 3 to Search By Advisor");
    
        int searchOption = 0;
        boolean searchOptionValid = false;
        while(!searchOptionValid)
        {
            String searchInput = sc.nextLine();
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
            String majorInput = sc.nextLine();
            searchStudent.major = majorInput;
        }
        else if (searchOption == 2)
        {
            System.out.print("Please Enter Student's GPA: ");
            double gpaInput = doubleCheck();
            searchStudent.gpa = gpaInput;
        }
        else if (searchOption == 3)
        {
            System.out.print("Please Enter Student's Advisor: ");
            String advisorInput = sc.nextLine();
            searchStudent.advisor = advisorInput;
        }
        
        
        return searchStudent;
    }
    
    private int integerCheck()
    {
        int inputCheck = 0;
        boolean inputValid = false;
        while(!inputValid)
        {
            String input = sc.nextLine();
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
    
    
    private double doubleCheck()
    {
        double inputCheck = 0.0;
        boolean inputValid = false;
        while(!inputValid)
        {
            String input = sc.nextLine();
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
