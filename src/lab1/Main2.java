package lab1;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class Main2 {
    public static void main(String[] args) throws Exception {
        String filePath = "C:\\Users\\lty96117\\code\\HIT\\HIT-2017SE\\data\\Data.txt";

        String filePathTo = "C:\\Users\\lty96117\\code\\HIT\\HIT-2017SE\\data\\To.txt";

        EdgeWeightedDigraph.init(filePath, filePathTo);
        System.out.println("==================init accomplished=====");
        EdgeWeightedDigraph EWD = new EdgeWeightedDigraph(new Scanner(new BufferedInputStream(new FileInputStream(new File(filePathTo)))));

        System.out.println("Reversed Map:");
        HashMap<Integer, String> map = EdgeWeightedDigraph.getReversedMap();
        Set<Integer> set = map.keySet();
        for (Integer key : set) {
            System.out.println(key + "-->" + map.get(key));

        }
        System.out.println("======================================");
        System.out.println(EWD);

        System.out.println("============Dijkstra Test begins===========");
        EdgeWeightedDigraph.getDijkstraSP(EWD, "To");
        System.out.println("============Dijkstra Test ends===========");
        System.out.println("============Dijkstra between Two Nodes Test begins===========");
        EdgeWeightedDigraph.getDijkstraBetweenTwoNodes(EWD, "To", "civilizations");

        System.out.println("============Dijkstra between Two Nodes Test ends===========");


        System.out.println("============RandomWalk Test begins===========");
        EdgeWeightedDigraph.randomWalk(EWD);
        System.out.println("============RandomWalk Test ends===========");

        System.out.println("============GUI TEST begins=============");
        EdgeWeightedDigraph.getGUI4Graph(EWD);
        System.out.println("============GUI TEST ends=============");

        System.out.println("======================================");
        System.out.println(EdgeWeightedDigraph.queryBridgeWords(EWD, "To", "strange"));
//        System.out.println("======================================");
//        System.out.println(EdgeWeightedDigraph.queryBridgeWords_new(EWD, "To", "strange"));
        System.out.println("======================================");
        System.out.println(EdgeWeightedDigraph.generateNewText(EWD, EdgeWeightedDigraph.getInput()));
        System.out.println("======================================");
    }
}
