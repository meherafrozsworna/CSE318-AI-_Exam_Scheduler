package Graph_coloring;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class main {
    public static String coursefile[] = new String[5];
    public static String studentFile[] = new String[5];
    public static String solnFile[] = new String[5];
    public static int known_best_timeslot[] = new int[5];
    public static float known_best_penalty[] = new float[5];
    public static int file_no = -1;

    public static int hightestDegreeSlot[] = new int[5];
    public static float hightestDegreePenalty[] = new float[5];
    public static float hightestDegreePenalty_after_Kempe[] = new float[5];
    public static int DSaturSlot[] = new int[5];
    public static float DSaturPenalty[] = new float[5];
    public static float DSaturPenalty_after_Kempe[] = new float[5];

    public static void main(String[] args) throws IOException {

        coursefile[0] = "input/car-s-91.crs";
        studentFile[0] = "input/car-s-91.stu";
        solnFile[0] = "input/car91.sol";
        known_best_timeslot[0] = 35 ;
        known_best_penalty[0] = (float) 4.42 ;

        coursefile[1] = "input/car-f-92.crs";
        studentFile[1] = "input/car-f-92.stu";
        solnFile[1] = "input/car92.sol";
        known_best_timeslot[1] = 32 ;
        known_best_penalty[1] = (float) 3.74 ;

        coursefile[2] = "input/kfu-s-93.crs";
        studentFile[2] = "input/kfu-s-93.stu";
        solnFile[2] = "input/kfu93.sol";
        known_best_timeslot[2] = 20 ;
        known_best_penalty[2] = (float) 12.96 ;

        coursefile[3] = "input/tre-s-92.crs";
        studentFile[3] = "input/tre-s-92.stu";
        solnFile[3] = "input/tre92.sol";
        known_best_timeslot[3] = 23 ;
        known_best_penalty[3] = (float) 7.75 ;

        coursefile[4] = "input/yor-f-83.crs";
        studentFile[4] = "input/yor-f-83.stu";
        solnFile[4] = "input/yor83.sol";
        known_best_timeslot[4] = 21 ;
        known_best_penalty[4] = (float) 34.84 ;

        for (int p = 0; p < 5 ; p++) {
            file_no = p ;
            Scanner scanner = new Scanner(new File(coursefile[p]));
            Path path = Paths.get(coursefile[p]);
            int lineCount_courses = (int)Files.lines(path).count();

            path = Paths.get(studentFile[p]);
            int lineCount_student = (int)Files.lines(path).count();
            //System.out.println(lineCount_student);

            //System.out.println(lineCount_courses);
            Graph g = new Graph(lineCount_courses , lineCount_student);
            for (int i = 1; i <= lineCount_courses ; i++) {
                String title = scanner.next();
                int enroll = scanner.nextInt();
                //System.out.println(title +" "+enroll);
                g.addCourseNode(title,enroll,i);
            }


            BufferedReader br = new BufferedReader(new FileReader(new File(studentFile[p])));

            String line;
            int k = 0 ;
            while ((line = br.readLine()) != null) {
                if (!line.equals("")){
                    String courses[] = line.split(" ");
                    for (int i = 0; i < courses.length ; i++) {
                        int c = Integer.parseInt(courses[i]);
                        g.student[k].addCourse(c);
                    }
                    k++;
                }

            }
            g.make_graph();


            //System.out.println("slot : "+g.graphColoring_LargestDegree());
            //System.out.println("slot : "+g.DSature());

            g.graphColoring_LargestDegree();
            g.DSature();

        }

        System.out.println("Hightest degree : \n");
        System.out.println("           given time slot      given penalty" +
                "             my time slot          my penalty          my penalty after kempe Chain ");
        for (int i = 0; i < 5 ; i++) {
            String str[] = solnFile[i].split("/");
            String str2[] = str[1].split("\\.");
            System.out.println(str2[0] + "              " + known_best_timeslot[i] + "              "+
                    known_best_penalty[i]+ "          ||             "+ hightestDegreeSlot[i]
                    + "              "+ hightestDegreePenalty[i] + "              " + hightestDegreePenalty_after_Kempe[i] );
        }

        System.out.println("\n\nDSature Algo  : \n");
        System.out.println("           given time slot      given penalty" +
                "             my time slot          my penalty          my penalty after kempe Chain ");
        for (int i = 0; i < 5 ; i++) {
            String str[] = solnFile[i].split("/");
            String str2[] = str[1].split("\\.");
            System.out.println(str2[0] + "              " + known_best_timeslot[i] + "              "+
                    known_best_penalty[i]+ "          ||             "+ DSaturSlot[i]
                    + "              "+ DSaturPenalty[i] + "              " + DSaturPenalty_after_Kempe[i] );
        }



        /*Graph graph = new Graph(9,1);
        graph.addEdgeGraph(1,2);
        graph.addEdgeGraph(1,3);
        graph.addEdgeGraph(1,6);
        graph.addEdgeGraph(1,7);
        graph.addEdgeGraph(1,8);
        graph.addEdgeGraph(2,4);
        graph.addEdgeGraph(3,5);
        graph.addEdgeGraph(4,5);
        graph.addEdgeGraph(5,8);
        graph.addEdgeGraph(6,7);
        graph.addEdgeGraph(7,8);
        graph.addEdgeGraph(8,9);


        System.out.println(graph.DSature());
        graph.graphColoring_SaturationDegree();

*/
    }
}
