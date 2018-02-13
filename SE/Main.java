import java.util.*;

public class Main{
    public static void main(String[] args)
    {

        //set up some students
        Student bernard = new Student(1111, 60, "Bernard Black", "b.black@books.co.uk", false, "1B Book Store, Fake Street, London", Utils.lectOne, Utils.MSCI);
        Student manny = new Student(1112, 60, "Manny Bianco", "m.bianco@books.co.uk", false, "Upstairs Book Store, Fake Street, London", Utils.lectOne, Utils.MSCI);
        Student fran = new Student(1113, 120, "Fran Katzenjammer", "f.katz@niftygrifty.co.uk", false, "Nifty Grifty, Fake Street, London", Utils.lectOne, Utils.MSCI);
    
        int zero = 0;
        int one = 1;
        
        //set up some courses
        MastersCourse algs = new MastersCourse("algs", "Algorithmics", 10);
        algs.setIncludesGroupWork(zero); //this doesn't have group work
    
        MastersCourse web = new MastersCourse("it", "ITech", 10);
        web.setIncludesGroupWork(one); //this does have group work

        //add some courses to some student's enrollment
        enrollStudent(fran, algs);
        enrollStudent(fran, web);
        enrollStudent(manny, web);
        enrollStudent(bernard, algs);

        //email Fran (or rather just print it to the console
        System.out.println(emailStudentEnrollementDetails(fran));
    }

    public static void enrollStudent(Student s, MastersCourse c)
    {
        if(s.courses == null)
        {
            s.courses = new LinkedList<MastersCourse>();
        }
        s.courses.add(c);
        
        for (int i = 0; i < c.getCredits(); i++)
        {
            s.incrementCredits();
        }
    }


    public static String emailStudentEnrollementDetails(Student s)
    {
        String email = "Hi " + s.name + ",\n \n";
        email = email + "I'm glad to inform you that you're enrolled for:\n";
        email = email + s.getCourses();
        return email;
    }
}
