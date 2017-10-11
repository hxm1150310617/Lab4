package lab1;

import java.io.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class EdgeWeightedDigraph {

    private final int V;
    private int E;
    private LinkedList<DirectedEdge>[] adj;

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
    public EdgeWeightedDigraph(int V) {
        if (V < 0) throw new IllegalArgumentException("Number of vertices in a Digraph must be nonnegative");
        this.V = V;
        this.E = 0;
        adj = (LinkedList<DirectedEdge>[]) new LinkedList[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new LinkedList<>();
        }

    }

    public EdgeWeightedDigraph(Scanner scanner) {
        this(scanner.nextInt());
        int E = scanner.nextInt();
        if (E < 0) throw new IllegalArgumentException("Number of edges must be nonnegative");

        for (int i = 0; i < E; i++) {
            String v = scanner.next();
            String w = scanner.next();
            boolean existed = false;
            for (DirectedEdge e : adj(map.get(v))) {
                // if a same edge exists, ++weight instead of creating a new edge
                if (e.to() == map.get(w)) {
                    e.addWeight(1);
                    existed = true;
                }
            }
            //no same edge exists, then add the new edge
            if (!existed) {
                addEdge(new DirectedEdge(map.get(v), map.get(w), 1));
            }

        }
        scanner.close();
    }

    public void addEdge(DirectedEdge e) {
        int v = e.from();
        adj[v].add(e); //single direction
        E++;
    }

    public int getVertices() {
        return V;
    }


    public Iterable<DirectedEdge> adj(int v) {
        return adj[v];
    }

    // all edges...
    public Iterable<DirectedEdge> edges() {
        LinkedList<DirectedEdge> list = new LinkedList<>();
        for (int v = 0; v < V; v++) {
            for (DirectedEdge e : adj(v)) {
                list.add(e);
            }
        }
        return list;
    }

    public int outdegree(int v) {
        return adj[v].size();
    }

    public LinkedList<DirectedEdge> getAdj(int v) {
        return adj[v];
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(V + " vertices. " + E + " edges\n");
        for (int v = 0; v < V; v++) {
            sb.append(reversedMap.get(v)).append(": ");
            for (DirectedEdge w : this.adj(v)) {
                sb.append(reversedMap.get(w.to())).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void randomWalk(EdgeWeightedDigraph G) {
        // first: select a node from map randomly
        int randomNode = ThreadLocalRandom.current().nextInt(0, G.getVertices());// generate random number from 0 to V - 1
        System.out.println("random number from graph: " + randomNode + ": " + reversedMap.get(randomNode));

        // if cur node's out degree >= 1, then continue
        int outDegree = G.outdegree(randomNode);
        boolean sameFlag = false;

        boolean first = true;
        while (outDegree != 0) {
            LinkedList<DirectedEdge> curAdj = G.getAdj(randomNode);
            //
//            System.out.println("outDegree: " + outDegree);
            int subRandomNode = ThreadLocalRandom.current().nextInt(outDegree);
//            System.out.println("SubRandomNumber: " + subRandomNode);
            // get the edge using the index->subRandomNode
//            System.out.println("Size: " + curAdj.size());
            DirectedEdge e = curAdj.get(subRandomNode);
            if (e.isVisited()) {
                // second: output node you walked until come across the same edge and end.
                // still output
                System.out.println("->" + reversedMap.get(e.to()));
                System.out.println("\nSame path came across, ended");
                sameFlag = true;
                break;
            } else {
                if (first) {
                    System.out.print("Start: " + reversedMap.get(e.from()));
                }
                first = false;
                System.out.print("->" + reversedMap.get(e.to()));
                e.setVisited(true);

                // update the outdegree of cur node
                // get the new outdegree from the old outDegree

                int newOutDegree = G.outdegree(e.to());
                randomNode = e.to();
                outDegree = newOutDegree;
            }
        }
        if (!sameFlag) {

            System.out.println("\nOut degree is 0! ended");
        }


    }

    public static void getDijkstraBetweenTwoNodes(EdgeWeightedDigraph G, String s, String t) throws IOException {

        final String dotName = "dijkstra_two.dot";
        final String outputName = "dijkstra_two_out.png";

        int start = map.get(s);
        int end = map.get(t);
        Dijkstra sp = new Dijkstra(G, start);
        StringBuilder sb = new StringBuilder();
        PrintStream ps = new PrintStream(new FileOutputStream(new File("C:\\Users\\lty96117\\code\\HIT\\HIT-2017SE\\data\\" + dotName)));

        String header = "digraph Dijkstra {\n" +
                "  size = \"8.5, 11\";\n" +
                "  fontname = \"Roboto\";\n" +
                "  fontsize = 10;\n" +
                "  \n" +
                "  node [shape = circle, fontname = \"Roboto\", fontsize = 10];\n" +
                "  edge [fontname = \"Roboto\", fontsize = 10];\n";
        ps.print(header);

        if (sp.hasPathTo(end)) {
            System.out.println(String.format("%s to %s (%d) ",
                    reversedMap.get(start), reversedMap.get(end), sp.distTo(end)));
            for (DirectedEdge e : sp.pathTo(end)) {
                System.out.print(reversedMap.get(e.from()) + " -> " + reversedMap.get(e.to()) + " (" + e.getWeight() + ")\n");
                sb.append("  ")
                        .append(reversedMap.get(e.from()))
                        .append(" -> ")
                        .append(reversedMap.get(e.to()))
                        .append(" [label=\"")
                        .append(e.getWeight())
                        .append("\",color=\"red\"]")
                        .append(";\n");
                ps.println(sb.toString());

            }
        } else {
            System.out.println(String.format("%s to %s has no path!",
                    reversedMap.get(start), reversedMap.get(end)));
        }

        ps.println("}");
        openFile(dotName, outputName);


    }

    public static void getDijkstraSP(EdgeWeightedDigraph G, String s) throws IOException {
        final String dotName = "dijkstra_all.dot";
        final String outputName = "dijkstra_all_out.png";
        PrintStream ps = new PrintStream(new FileOutputStream(new File("C:\\Users\\lty96117\\code\\HIT\\HIT-2017SE\\data\\" + dotName)));

        String header = "digraph Dijkstra {\n" +
                "  size = \"8.5, 11\";\n" +
                "  fontname = \"Roboto\";\n" +
                "  fontsize = 10;\n" +
                "  \n" +
                "  node [shape = circle, fontname = \"Roboto\", fontsize = 10];\n" +
                "  edge [fontname = \"Roboto\", fontsize = 10];\n";
        ps.print(header);


        StringBuilder sb = new StringBuilder();

        int start = map.get(s);
        Dijkstra sp = new Dijkstra(G, start);
        for (int i = 0; i < G.getVertices(); i++) {
            if (sp.hasPathTo(i)) {
                System.out.println(String.format("%s to %s (%d) ",
                        reversedMap.get(start), reversedMap.get(i), sp.distTo(i)));
                for (DirectedEdge e : sp.pathTo(i)) {
                    System.out.print(reversedMap.get(e.from()) + " -> " + reversedMap.get(e.to()) + " (" + e.getWeight() + ") | ");
                    sb.append("  ")
                            .append(reversedMap.get(e.from()))
                            .append(" -> ")
                            .append(reversedMap.get(e.to()))
                            .append(" [label=\"")
                            .append(e.getWeight())
                            .append("\",color=\"red\"]")
                            .append(";\n");
                    ps.println(sb.toString());

                }
                System.out.println("\n-------------------------");
            } else {
                System.out.println(String.format("%s to %s has no path!",
                        reversedMap.get(start), reversedMap.get(i)));
            }
        }
        ps.print("}");

//        openFile(dotName,outputName);
    }

    public static void openFile(String dotName, String outputName) throws IOException {
        System.out.println("file path: " + outputName);
        String tmp = "dot -Tpng " + dotName + " -o " + outputName + " && C:\\Users\\lty96117\\code\\HIT\\HIT-2017SE\\data\\" + outputName;
        System.out.println(tmp);
        String[] command = {"cmd.exe", "/C", "C:\\Users\\lty96117\\code\\HIT\\HIT-2017SE\\data\\To.txt"  };
        ProcessBuilder probuilder = new ProcessBuilder(command);
        Process process = probuilder.start();
    }

    public static void getGUI4Graph(EdgeWeightedDigraph G) throws IOException {
        final String dotName = "graph.dot";
        final String outputName = "graph_out.png";
        PrintStream ps = new PrintStream(new FileOutputStream(new File("C:\\Users\\lty96117\\code\\HIT\\HIT-2017SE\\data\\graph.dot")));
        String header = "digraph test {\n" +
                "  size = \"8.5, 11\";\n" +
                "  fontname = \"Roboto\";\n" +
                "  fontsize = 10;\n" +
                "  \n" +
                "  node [shape = circle, fontname = \"Roboto\", fontsize = 10];\n" +
                "  edge [fontname = \"Roboto\", fontsize = 10];\n";
        ps.print(header);

//        Set<Integer> set = reversedMap.keySet();
//        for (Integer key : set) {
//            ps.println(key + "->" + reversedMap.get(key));
//        }
        StringBuilder sb = new StringBuilder();
        for (int v = 0; v < G.getVertices(); v++) {
//            sb.append(reversedMap.get(v)).append(": ");
            for (DirectedEdge w : G.adj(v)) {
                sb.append("  ")
                        .append(reversedMap.get(v))
                        .append(" -> ")
                        .append(reversedMap.get(w.to()))
                        .append(" [label=\"")
                        .append(w.getWeight())
                        .append("\"]")
                        .append(";\n");

            }
//            sb.append("\n");
        }
        System.out.println("DATA TO TEST: \n" + sb.toString());
        ps.println(sb.toString());
        ps.print("}");

        openFile(dotName, outputName);


    }


    public static String queryBridgeWords(EdgeWeightedDigraph G, String word1, String word2) {
//    	@SuppressWarnings("static-access")
        StringBuilder sb = new StringBuilder("");
        if (EdgeWeightedDigraph.getMap().get(word1) == null
                && EdgeWeightedDigraph.getMap().get(word2) == null) {
            sb.append("NO \"").append(word1).append("\" and \"").append(word2).append("\" in the graph!");
            return sb.toString();
        }
        if (EdgeWeightedDigraph.getMap().get(word1) == null) {
            sb.append("No \"").append(word1).append("\" in the graph!");
            return sb.toString();
        }
        if (EdgeWeightedDigraph.getMap().get(word2) == null) {
            sb.append("No \"").append(word2).append("\" in the graph!");
            return sb.toString();
        }
        int v = EdgeWeightedDigraph.getMap().get(word1);
        int w = EdgeWeightedDigraph.getMap().get(word2);
        String bridge = "";

        ArrayList<String> ls = new ArrayList<>();
        boolean tag = false;
        for (DirectedEdge e : G.adj(EdgeWeightedDigraph.getMap().get(word1))) {
            for (DirectedEdge f : G.adj(e.to())) {
                if (f.to() == w) {
                    tag = true;
                    bridge = EdgeWeightedDigraph.getReversedMap().get(e.to());
                    ls.add(bridge);
                    System.out.println(word1 + "--->" + bridge + "--->" + word2);
                }
            }
        }
        if (tag == false) {
            //System.out.println("No bridge words from "+word1+" to "+word2+"!");
            sb.append("No bridge words from \"" + word1 + "\" to \"" + word2 + "\"!");
            return sb.toString();
        } else {

            if (ls.size() == 1) {
                sb.append("The bridge words from \"" + word1 + "\" to \"" + word2 + "\" is:");
            } else sb.append("The bridge words from \"" + word1 + "\" to \"" + word2 + "\" are:");
            for (Iterator<String> i = ls.iterator(); i.hasNext(); ) {
                sb.append((String) i.next() + " ");
            }
            return sb.toString();
        }
    }

    public static String queryBridgeWords_new(EdgeWeightedDigraph G, String word1, String word2) {
        StringBuilder sb = new StringBuilder("");
        if (EdgeWeightedDigraph.getMap().get(word1) == null
                || EdgeWeightedDigraph.getMap().get(word2) == null) {
            return sb.toString();
        }
        int v = EdgeWeightedDigraph.getMap().get(word1);
        int w = EdgeWeightedDigraph.getMap().get(word2);
        String bridge = "";

        ArrayList<String> ls = new ArrayList<>();
        boolean tag = false;
        for (DirectedEdge e : G.adj(EdgeWeightedDigraph.getMap().get(word1))) {
            for (DirectedEdge f : G.adj(e.to())) {
                if (f.to() == w) {
                    tag = true;
                    bridge = EdgeWeightedDigraph.getReversedMap().get(e.to());
                    ls.add(bridge);
//    				System.out.println(word1+"--->"+bridge+"--->"+word2);
                }
            }
        }
        if (!tag) return sb.toString();
        else if (ls.size() >= 2) {
            int s = ls.size();
            Random rand = new Random();
            int index = rand.nextInt(ls.size());
            sb.append(ls.get(index));
            return sb.toString();
        } else
            return ls.get(0);

    }

    public static String getInput() {
        System.out.println("Enter a line of words: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        scanner.close();
        return input;
//		scanner.useDelimiter(" |\r\n");
//		ArrayList<String> ls = new ArrayList<>();
//		while (scanner.hasNext()) {
//            String tmp = scanner.next().replaceAll("[^A-Za-z]", "");
//            if (!Objects.equals(tmp, "")) {
//                System.out.print(tmp + " ");
//                ls.add(tmp);
//            }
//        }
//		return ls.toString();
    }

    public static String generateNewText(EdgeWeightedDigraph G, String inputText) {
        inputText.replaceAll("[^A-za-z]", "");
        StringBuilder sb = new StringBuilder();
        String[] words = inputText.split(" |\r\n");
        for (int i = 0; i < words.length - 1; i++) {
            sb.append(words[i] + " ");
            String tmp = queryBridgeWords_new(G, words[i], words[i + 1]);
            if (!tmp.equals("")) {
                sb.append(tmp + " ");
            }
            if (i == words.length - 2) {
                sb.append(words[i + 1]);
            }
        }
        return sb.toString();
    }


}
