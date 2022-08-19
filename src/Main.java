
public class Main {
    public static void main(String[] args) {

        Graph g1 = new Graph(4);
        System.out.println(g1);
        g1.addEdge(0, 1, 1);
        g1.addEdge(1, 0, 1);
        g1.addEdge(2, 3, 1);
        g1.addEdge(3, 2, 1);
        System.out.println(g1);
        if(g1.oriented()){
            System.out.println(" é orientado");
        }else{
            System.out.println("não é orientado");
        }
    }
}
