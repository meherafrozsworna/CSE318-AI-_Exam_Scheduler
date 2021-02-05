/*package Graph_coloring;

import Graph_coloring.Course_node;

import java.util.Comparator;

public class Dsatur_CourseComparetor implements Comparator<Course_node> {
    @Override
    public int compare(Course_node o1, Course_node o2) {
        //System.out.println(o1.adjacent_colors.size());
        //System.out.println(o2.adjacent_colors.size());
        int o1_saturation_degree = o1.adjacent_colors.size();
        int o2_saturation_degree = o2.adjacent_colors.size();
        if(o1_saturation_degree < o2_saturation_degree)
            return 1;
        else if(o1_saturation_degree > o2_saturation_degree)
            return -1;
        else{
            int o1_degree = o1.adjList.size();
            int o2_degree = o2.adjList.size();
            if(o1_degree < o2_degree)
                return 1;
            else if(o1_degree > o2_degree)
                return -1;

            return Integer.compare(o2.adjList.size() , o1.adjList.size());
        }


    }

}
*/

/*
    public int graphColoring_SaturationDegree()
    {
        System.out.println("\nSaturation degree : ");
        PriorityQueue<Course_node> pq = new PriorityQueue<Course_node>(vertex, new Dsatur_CourseComparetor() );

        for (int i = 1; i <= vertex; i++) {
            pq.add(nodes[i]);
        }
        boolean availableColor[] = new boolean[vertex];
        Arrays.fill(availableColor, true);

        while (!pq.isEmpty())
        {
            Course_node node = pq.peek();
            pq.poll();
            System.out.println(node.id + " **  " +node.adjacent_colors.size() + " ** "+ node.adjList.size());

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
                    //or adjacent node a color add korlam
                    for (int j = 0; j < node.adjList.size(); j++) {
                        nodes[node.adjList.get(j)].adjacent_colors.add(color[node.id]);
                        //System.out.println(nodes[node.adjList.get(j)].adjacent_colors);
                    }
                    if(i ==  slot)
                        slot++;
                    break;
                }
            }
            Arrays.fill(availableColor,true);
        }


        for (int i = 1; i <= vertex ; i++) {
            System.out.println((i)+ " ----- "+ color[i]);
        }
        System.out.println(penalty_count());


        return slot;
    }
    */