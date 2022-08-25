import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Graph g1 = new Graph(8, 'l');

        g1.addEdgeUnorieted(1, 6, 1);
        g1.addEdgeUnorieted(0, 6, 1);
        g1.addEdgeUnorieted(4, 0, 1);
        g1.addEdgeUnorieted(5, 4, 1);
        g1.addEdgeUnorieted(3, 4, 1);
        g1.addEdgeUnorieted(7, 3, 1);
        g1.addEdgeUnorieted(2, 3, 1);
        System.out.println(g1.oriented());
        System.out.println(g1);
        System.out.println(g1.breadthFirstSearch(4));
    }
}
