import java.util.*;
import java.nio.*;

public class Utils
{
    public static final Lecturer lectOne = new Lecturer("John Smith", "T100", new LinkedList<Course>(), new LinkedList<MastersCourse>());
    public static final String MSCI = "MSci";
    public static final String MSIT = "MS(IT)";
    public static final int PART_TIME_CREDITS = 60;
    public static final int FULL_TIME_CREDITS = 120;

    public static int countMastersCourses(LinkedList<MastersCourse> l)  //return the number of the masters course
    {
        int n = 0;
        for (MastersCourse c: l)
        {
            n++;
        }
        return n;
    }
}
