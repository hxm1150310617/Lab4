package lab1;


public class DirectedEdge {
    private final int v;
    private final int w;
    private int weight; // not final
    private boolean isVisited; //set the mark

    public DirectedEdge(int v, int w, int weight) {
        if (v < 0) throw new IllegalArgumentException("Vertex names must be nonnegative integers");
        if (w < 0) throw new IllegalArgumentException("Vertex names must be nonnegative integers");
        if (Double.isNaN(weight)) throw new IllegalArgumentException("Weight is NaN!");
        this.v = v;
        this.w = w;
        this.weight = 1; // add new edge
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }

    public int getWeight() {
        return weight;
    }

    public void addWeight(int add) {
        weight += add;
    }

    public int from() {
        return v;
    }

    public int to() {
        return w;
    }

    public String toString() {
        return v + " -> " + w + " " + weight;
    }

    public static void main(String[] args) {
        DirectedEdge e = new DirectedEdge(12, 34, 5);
        System.out.println(e);
    }
}
