package Graph_coloring;

import java.util.ArrayList;
import java.util.List;

public class Student {
    public List<Integer> courses ;

    public Student()
    {
        courses = new ArrayList<Integer>();
    }
    public void addCourse(int c)
    {
        courses.add(c);
    }
}
