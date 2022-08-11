
public class Main {
    public static void main(String[] args) {
        String a = "aaaaaaaaaa";
        System.out.println(a);
        Graph g1 = new Graph(4);
        System.out.println(g1);
        g1.toString();
        g1.addEdge(0, 1 , 3);
        g1.toString();
        System.out.println(g1);
        g1.addEdge(0, 2 , 3);
        System.out.println(g1.degreee(0));
    }
}
