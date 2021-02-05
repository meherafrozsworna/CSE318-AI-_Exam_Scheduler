package Graph_coloring;

import Graph_coloring.Course_node;

import java.util.Comparator;

public class CourseComparetor implements Comparator<Course_node> {
    @Override
    public int compare(Course_node o1, Course_node o2) {
        int o1_degree = o1.adjList.size();
        int o2_degree = o2.adjList.size();
        if(o1_degree < o2_degree)
            return 1;
        else if(o1_degree > o2_degree)
            return -1;

        return 0;
    }

}
