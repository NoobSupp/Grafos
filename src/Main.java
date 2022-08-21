import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Graph g1 = new Graph(6, 'l');

        g1.addEdge(1, 2, 1);
        g1.addEdge(2, 3, 1);
        g1.addEdge(3, 5, 1);
        g1.addEdge(5, 4, 1);
        g1.addEdge(3, 1, 1);
        g1.addEdge(1, 0, 1);
        g1.addEdge(0, 5, 1);
        g1.addEdge(2, 1, 1);
        g1.addEdge(3, 2, 1);
        g1.addEdge(5, 3, 1);
        g1.addEdge(4, 5, 1);
        g1.addEdge(1, 3, 1);
        g1.addEdge(0, 1, 1);
        g1.addEdge(5, 0, 1);
        System.out.println(g1.oriented());
        System.out.println(g1);
        System.out.println(g1.breadthFirstSearch(5));
    }
}
