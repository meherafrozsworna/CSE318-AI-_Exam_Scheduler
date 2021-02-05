package Graph_coloring;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Course_node {
    public String course_title ;
    public int enrollment ;
    public int color = -1 ;
    public int id ;
    public List<Integer> adjList;
    public Set<Integer> adjacent_colors ;

    public Course_node(String c_title , int enroll,int id_)
    {
        course_title = c_title;
        enrollment = enroll ;
        id = id_;
        adjList = new ArrayList<Integer>();
        adjacent_colors  = new HashSet<Integer>();
    }

    public Course_node(int id_)
    {
        id = id_;
        adjList = new ArrayList<Integer>();
        adjacent_colors  = new HashSet<Integer>();
    }

    public void addEdge(int j)
    {
        if(!adjList.contains(j))
            adjList.add(j);
    }
    public int getSaturation_degree()
    {
        return adjacent_colors.size();
    }
    public int getDegree()
    {
        return adjList.size();
    }
}
