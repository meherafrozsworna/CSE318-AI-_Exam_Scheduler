package Graph_coloring;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import static Graph_coloring.main.*;

public class Graph {
    public int vertex ;
    public int student_count;
    public Course_node[] nodes ;
    public Student student[];
    public int color[];
    int slot = 0 ;


    public Graph(int vertex, int student_c)
    {
        this.vertex = vertex;
        this.student_count = student_c;
        nodes = new Course_node[vertex+1];
        for (int i = 1; i <= vertex ; i++) {
            nodes[i] = new Course_node(i);
        }

        student = new Student[student_count];
        for (int i = 0; i < student_count ; i++) {
            student[i] = new Student() ;
        }

        color = new int[vertex+1];
        Arrays.fill(color,-1);
    }

    public void addCourseNode(String c_title , int enroll,int id)
    {
        //Graph_coloring.Course_node course_node = new Graph_coloring.Course_node(c_title,enroll,id);
        nodes[id].course_title = c_title;
        nodes[id].enrollment = enroll;
    }

    public void make_graph()
    {
        for (int i = 0; i < student_count ; i++) {
            for (int j = 0; j < student[i].courses.size() ; j++) {
                for (int k = j+1; k < student[i].courses.size(); k++) {
                    addEdgeGraph(student[i].courses.get(j),student[i].courses.get(k));
                }
            }
        }
    }

    public void addEdgeGraph(int i , int j)
    {
        //System.out.println(i +"  "+ j);
        nodes[i].addEdge(j);
        nodes[j].addEdge(i);
    }

    public int graphColoring_LargestDegree() throws IOException {
        FileWriter fw = new FileWriter(solnFile[file_no]);

        PriorityQueue<Course_node> pq = new PriorityQueue<Course_node>(vertex, new CourseComparetor());
        for (int i = 1; i <= vertex; i++) {
            pq.add(nodes[i]);
        }
        boolean availableColor[] = new boolean[vertex];
        Arrays.fill(availableColor, true);

        while (!pq.isEmpty())
        {
            Course_node node = pq.peek();
            pq.poll();
            //System.out.println(node.id);
            for (int i = 0; i < node.adjList.size(); i++) {
                int adjColor = color[node.adjList.get(i)];
                if(adjColor != -1 ){
                    availableColor[adjColor] = false;
                }
            }
            for (int i = 0; i < vertex; i++) {
                if(availableColor[i])
                {
                    color[node.id] = i;
                    fw.write(node.id + "  "+ (color[node.id]+1)+"\n");
                    if(i ==  slot)
                        slot++;
                    break;
                }
            }
            Arrays.fill(availableColor,true);
        }


        /*for (int i = 1; i <= vertex ; i++) {
            System.out.println((i)+ " ----- "+ color[i]);
        }*/
        //System.out.println(penalty_count());
        fw.close();
        hightestDegreeSlot[file_no] = slot ;
        hightestDegreePenalty[file_no] = penalty_count() ;
        kempeChain();
        hightestDegreePenalty_after_Kempe[file_no] = penalty_count();


        return slot;
    }


    public int DSature()
    {

        boolean availableColor[] = new boolean[vertex];
        Arrays.fill(availableColor, true);

        int max_saturation_degree = 0;
        int max_degree = -1;
        int id = -1 ;
        int index = -1 ;

        List<Integer> vertex_list = new ArrayList<Integer>();
        for (int i = 1; i <= vertex ; i++) {
            vertex_list.add(i);
        }
        while (vertex_list.size() > 0)
        {
            for (int i = 0; i < vertex_list.size(); i++) {
                int ii = vertex_list.get(i) ;
                if (nodes[ii].getSaturation_degree() > max_saturation_degree){
                    max_saturation_degree = nodes[ii].getSaturation_degree() ;
                    max_degree = nodes[ii].getDegree() ;
                    id = ii ;
                    index = i ;
                    //System.out.println(ii + "  "+ max_saturation_degree + "  "+ max_degree);
                }else if(nodes[ii].getSaturation_degree() == max_saturation_degree){
                    if(nodes[ii].getDegree() > max_degree){
                        max_saturation_degree = nodes[ii].getSaturation_degree() ;
                        max_degree = nodes[ii].getDegree() ;
                        id = ii ;
                        index = i ;
                        //System.out.println(ii + "  "+ max_saturation_degree + "  "+ max_degree);
                    }
                }
            }
            //System.out.println("_____________");
            //System.out.println(id);
            for (int i = 0; i < nodes[id].adjList.size(); i++) {
                int adjColor = color[nodes[id].adjList.get(i)];
                if(adjColor != -1 ){
                    availableColor[adjColor] = false;
                }
            }

            for (int i = 0; i < vertex; i++) {
                if(availableColor[i])
                {
                    color[id] = i;
                    //or adjacent node a color add korlam
                    for (int j = 0; j < nodes[id].adjList.size(); j++) {
                        nodes[nodes[id].adjList.get(j)].adjacent_colors.add(color[id]);
                        //System.out.println(nodes[nodes[id].adjList.get(j)].adjacent_colors);
                    }
                    if(i ==  slot)
                        slot++;
                    break;
                }
            }
            Arrays.fill(availableColor,true);
            vertex_list.remove(index);
            max_saturation_degree = -1;
            max_degree = -1;
            index = -1 ;
            id = -1 ;
        }

        /*for (int i = 1; i <= vertex ; i++) {
            System.out.println((i)+ " ----- "+ color[i]);
        }
        */

        DSaturSlot[file_no] = slot ;
        DSaturPenalty[file_no] = penalty_count() ;
        kempeChain();
        DSaturPenalty_after_Kempe[file_no] = penalty_count() ;
        return slot ;
    }

    public float penalty_count()
    {
        float penalty = 0;
        for (int i = 0; i < student_count ; i++) {
            ArrayList<Integer> exam_colors_list = new ArrayList<Integer>();
            for (int j = 0; j < student[i].courses.size() ; j++) {
                int c = color[student[i].courses.get(j)];
                exam_colors_list.add(c);
            }
            Collections.sort(exam_colors_list);
            for (int j = 0; j < exam_colors_list.size()-1 ; j++) {
                //System.out.println(exam_colors_list.get(j) + "  "+ exam_colors_list.get(j+1));
                for (int k = j+1; k < exam_colors_list.size() ; k++) {
                    int difference =  ( exam_colors_list.get(k) -  exam_colors_list.get(j) ) ;

                    if (difference == 1)
                        penalty += 16;
                    else if(difference == 2 )
                        penalty += 8;
                    else if(difference == 3)
                        penalty += 4;
                    else if(difference == 4)
                        penalty += 2 ;
                    else if(difference == 5)
                        penalty += 1 ;
                    else {
                        break;
                    }
                }

            }
            exam_colors_list.removeAll(exam_colors_list);
        }

        penalty = penalty / student_count ;
        //System.out.println("Penalty : "+ penalty);
        return  penalty;
    }

    public void kempeChain()
    {
        for (int i = 1; i <= vertex ; i++) {
            if(nodes[i].adjList.size() > 0)
            {
                int old_color[] = new int[color.length];
                System.arraycopy(color, 0, old_color, 0, color.length);

                Random r = new Random();
                int random_adjvertex = Math.abs(r.nextInt()) % nodes[i].adjList.size();

                int color1 = color[nodes[i].id] ;
                int color2 = color[nodes[i].adjList.get(random_adjvertex)];

                Queue<Integer> q = new LinkedList<>();

                color[nodes[i].id] = color2 ;
                color[nodes[i].adjList.get(random_adjvertex)] = color1 ;

                q.add(nodes[i].id);
                q.add(nodes[i].adjList.get(random_adjvertex)) ;

                float penalty_before = penalty_count() ;

                while (!q.isEmpty())
                {
                    int id = q.peek() ;
                    q.poll() ;
                    for (int j = 0; j < nodes[id].adjList.size(); j++) {
                        //System.out.println(j + "   "+ nodes[id].adjList.get(j));
                        if(color[nodes[id].adjList.get(j)] == color[id])
                        {
                            if(color[id] == color1){
                                color[nodes[id].adjList.get(j)] = color2;
                            }else{
                                color[nodes[id].adjList.get(j)] = color1 ;
                            }
                            q.add(nodes[id].adjList.get(j));
                        }
                    }
                }
                float penalty_after = penalty_count() ;
                //System.out.println(penalty_before + "    LLL    "+ penalty_after);
                if (penalty_after > penalty_before)
                {
                    System.arraycopy(old_color, 0, color, 0, color.length);
                }
            }
        }

        //System.out.println("\nPenalty after kempe : "+ penalty_count());


    }



}
