package lab1;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class TestGraph {
    public static void main(String[] args) throws IOException {
        String filePath = "C:\\Users\\lty96117\\code\\HIT\\HIT-2017SE\\data\\Data.txt";

        String filePathTo = "C:\\Users\\lty96117\\code\\HIT\\HIT-2017SE\\data\\To.txt";

        EdgeWeightedDigraph.init(filePath, filePathTo);
        System.out.println("==================init accomplished=====");
        EdgeWeightedDigraph EWD = new EdgeWeightedDigraph(new Scanner(new BufferedInputStream(new FileInputStream(new File(filePathTo)))));
        EdgeWeightedDigraph.getGUI4Graph(EWD);
        EdgeWeightedDigraph.getDijkstraBetweenTwoNodes(EWD, "To", "civilizations");
        EdgeWeightedDigraph.getDijkstraSP(EWD, "To");
    }
}

// c4