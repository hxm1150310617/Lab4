package lab1;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;


public class Digraph {
    private final int Vertices;
    private int Edges;
    private LinkedList<Integer>[] adj;
    private static HashMap<String, Integer> map; // only for storing in adj list
    private static HashMap<Integer, String> reversedMap; // only for printing out

    public static HashMap<String, Integer> getMap() {
        return map;
    }

    public static HashMap<Integer, String> getReversedMap() {
        return reversedMap;
    }

    public static void init(String filenameFrom, String filenameTo) {
        //get the map and the output file before calling constructor
        map = MyIOUtils.readDataAndWriteData(filenameFrom, filenameTo);
        reversedMap = reverseMap(map);
    }

    public static HashMap<Integer, String> reverseMap(HashMap<String, Integer> map) {
        HashMap<Integer, String> reversedMap = new HashMap<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            reversedMap.put(entry.getValue(), entry.getKey());
        }
        return reversedMap;
    }

    @SuppressWarnings("unchecked")
    public Digraph(int V) {
        if (V < 0) throw new IllegalArgumentException("Number of vertices in a Digraph must be nonnegative");
        this.Vertices = V;
        this.Edges = 0;
        adj = (LinkedList<Integer>[]) new LinkedList[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    @SuppressWarnings("unchecked")
    /*
     * 读输出的文件，并保存为int
     */
    public Digraph(Scanner scanner) {
        this(scanner.nextInt());
        int edges = scanner.nextInt();
        if (edges < 0) {
            throw new IllegalArgumentException("number of edges in a Digraph must be nonnegative");
        }
        for (int i = 0; i < edges; i++) {
            String v = scanner.next();
            String w = scanner.next();
            addEdge(v, w);
        }
        scanner.close();

    }

    public int getVertices() {
        return Vertices;
    }

    public int getEdges() {
        return Edges;
    }

    private void addEdge(String v, String w) {
        adj[map.get(v)].add(map.get(w));   //only one side!
        Edges++;
    }

    //    public String toString() {
//        StringBuilder s = new StringBuilder(Vertices + " vertices. " + Edges + " edges\n");
//        for (int v = 0; v < Vertices; v++) {
//            s.append(": ");
//            for (int map.get(w) : this.adj(map.get(v))) {
//                s.append(w).append(" ");
//            }
//            s.append("\n");
//        }
//        return s.toString();
//    }
    public Iterable<Integer> adj(int V) {
        return this.adj[V];
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(Vertices + " vertices. " + Edges + " edges\n");
        for (int v = 0; v < Vertices; v++) {
            sb.append(reversedMap.get(v)).append(": ");
            for (int w : this.adj(v)) {
                sb.append(reversedMap.get(w)).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
