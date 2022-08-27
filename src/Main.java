import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Graph g1 = new Graph(8, 'l');

        g1.addEdgeUnorieted(0, 5, 1);
        g1.addEdgeUnorieted(0, 7, 1);
        g1.addEdgeUnorieted(7, 6, 1);
        g1.addEdgeUnorieted(3, 6, 1);
        g1.addEdgeUnorieted(4, 3, 1);
        g1.addEdgeUnorieted(6, 1, 1);
        g1.addEdgeUnorieted(1, 2, 1);
        System.out.println(g1);
        System.out.println(g1.breadthFirstSearch(4));
        System.out.println(g1.isConnected());
        System.out.println(g1.depthFirstSearch(2));
    }
}
