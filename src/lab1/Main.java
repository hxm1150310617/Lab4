package lab1;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        String filePath = "C:\\Users\\lty96117\\code\\HIT\\HIT-2017SE\\data\\Data.txt";

        String filePathTo = "C:\\Users\\lty96117\\code\\HIT\\HIT-2017SE\\data\\To.txt";
        Digraph.init(filePath, filePathTo);
        System.out.println("==================init accomplished=====");
        Digraph digraph = new Digraph(new Scanner(new BufferedInputStream(new FileInputStream(new File(filePathTo)))));
        System.out.println("Reversed Map: ");
        HashMap<Integer, String> map = Digraph.getReversedMap();
        Set<Integer> set = map.keySet();
        for (Integer key : set) {
            System.out.println(key + "-->" + map.get(key));

        }

        System.out.println("==========test entry begins===========");
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
//            it.remove();
        }


        System.out.println("==========test entry ends===========");

        System.out.println("======================================");
        System.out.println("Edges: " + digraph.getEdges());
        System.out.println("Vertices: " + digraph.getVertices());
        System.out.println(digraph.toString());


//        Map<String, Integer> map =    MyIOUtils.readDataAndWriteData(filePath, filePathTo);
//
//        Map<Integer, String> mapInversed =
//                map.entrySet()
//                        .stream()
//                        .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
//        Set<Integer> set = mapInversed.keySet();
//        Iterator<Integer> iter = set.iterator();
//        while (iter.hasNext()) {
//            int key = iter.next();
//            System.out.println(key + "-->" + mapInversed.get(key));
//        }
    }

}
