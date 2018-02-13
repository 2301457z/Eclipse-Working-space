import java.util.*;

public class Lecturer
{
    private String name;
    private String office;
    private LinkedList<Course> courses;
    private LinkedList<MastersCourse> mastersCourses;

    public Lecturer()
    {
    }

    public Lecturer(String n, String where, LinkedList<Course> c, LinkedList<MastersCourse> mc)
    {
        name = n;
        office = where;
        courses = c;
        mastersCourses = mc;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public LinkedList<Course> getCourses() {
		return courses;
	}

	public void setCourses(LinkedList<Course> courses) {
		this.courses = courses;
	}

	public LinkedList<MastersCourse> getMastersCourses() {
		return mastersCourses;
	}

	public void setMastersCourses(LinkedList<MastersCourse> mastersCourses) {
		this.mastersCourses = mastersCourses;
	}
    
}
