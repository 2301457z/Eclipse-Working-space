public class Course
{
    private String shortCode;
    private String name;
    private int credits;

    public Course()
    {
    }

    public Course(String sc, String n, int c)
    {
        shortCode = sc;
        name = n;
        credits = c;
    }

	public String getShortCode() {
		return shortCode;
	}

	public void setShortCode(String shortCode) {
		this.shortCode = shortCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}
}
