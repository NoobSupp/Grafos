public class Edge {
   final private int edge;
   final private int weight;

   Edge(int edge, int weight){
       this.edge = edge;
       this.weight = weight;
   }

    public int getWeight() {
        return weight;
    }

    public int getEdge() {
        return edge;
    }
}
