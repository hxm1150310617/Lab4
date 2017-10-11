package lab1;

//edit on 1150830203
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Stack;

public class Dijkstra {
    private int[] distTo;
    private DirectedEdge[] edgeTo;
    private IndexMinPQ<Integer> pq;

    public Dijkstra(EdgeWeightedDigraph G, int s) {
        for (DirectedEdge e : G.edges()) {
            if (e.getWeight() < 0) {
                throw new IllegalArgumentException("edge " + e + " has negative weight");
            }
        }
        edgeTo = new DirectedEdge[G.getVertices()];
        distTo = new int[G.getVertices()];
        pq = new IndexMinPQ<>(G.getVertices());

        // init
        for (int v = 0; v < G.getVertices(); v++) {
            distTo[v] = Integer.MAX_VALUE;
        }
        distTo[s] = 0;

        pq.insert(s, 0);
        while (!pq.isEmpty()) {
            relax(G, pq.delMin());
        }

    }

    private void relax(EdgeWeightedDigraph G, int v) {
        for (DirectedEdge e : G.adj(v)) {
            int w = e.to();
            if (distTo[w] > (distTo[v] + e.getWeight())) {
                distTo[w] = distTo[v] + e.getWeight();
                edgeTo[w] = e;
                if (pq.contains(w)) pq.changeKey(w, distTo[w]);
                else pq.insert(w, distTo[w]);
            }
        }
    }

    public int distTo(int v) {
        return distTo[v];
    }

    public boolean hasPathTo(int v) {
        return distTo[v] < Integer.MAX_VALUE;
    }

    public Iterable<DirectedEdge> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<DirectedEdge> path = new Stack<>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
            path.push(e);
        }
        Stack<DirectedEdge> newPath = new Stack<>();
        Iterator<DirectedEdge> iter = path.iterator();
        ArrayList<DirectedEdge> arrayList = new ArrayList<>();
        while (iter.hasNext()) {
            arrayList.add(iter.next());
        }
        Collections.reverse(arrayList);
        Iterator<DirectedEdge> it = arrayList.iterator();
        while (it.hasNext()) {
            newPath.push(it.next());
        }
        return newPath;
    }

}
