// A student
import java.util.*;

public class Student{

	private int matricNum;
	private int enrolledCredits;
	public String name;
	private String email;
	private boolean fullTime;
	private String address;
	private Lecturer advisor;
	private String degreeProgramme;
    public LinkedList<MastersCourse> courses;
    private int n; // count some for loop with this!

	public Student()
	{
		//empty constructor
	}

	public Student(int m, int e, String n, String mail, boolean fT, String add, Lecturer ad, String deg)
    {
        matricNum = m; 
        fullTime = fT; 
        enrolledCredits = 0;
        email = mail;
        address = add;
        advisor = ad;
        degreeProgramme = deg;
        name = n;
    }

    public String getName()
    {
        return name;
    }

    public void incrementCredits()
    {
        enrolledCredits++;
    }

    public void countMastersCourses(LinkedList<MastersCourse> l)
    {
        for(MastersCourse c: l)
        {
            n++;
        }
    }

    public String getCourses()
    {
        String s = "";
        n = Utils.countMastersCourses(courses);
        s = s + courses.get(0).shortCode;
        for (int i = 1; i < n; i++)
        {
            s = s + ", " + courses.get(i).shortCode; 
        }

        return s;
    }


}

