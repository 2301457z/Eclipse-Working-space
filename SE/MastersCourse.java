public class MastersCourse
{
    public String shortCode;
    public String name;
    public int credits;
    public boolean includesGroupWork;

    public MastersCourse()
    {
    }

    public MastersCourse(String sc, String n, int c)
    {
        shortCode = sc;
        name = n;
        credits = c;
    }

    public void setIncludesGroupWork(int bool)
    {
        if(bool == 0)
        {
            includesGroupWork = noGroupWork();
        }
        else if (bool == 1)
        {
            includesGroupWork = groupWork();
        }
    }

    public boolean groupWork()
    {
        return true;    
    }

    public boolean noGroupWork()
    {
        return false;
    }

    public int getCredits()
    {
        return credits;
    }
}

