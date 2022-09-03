import java.util.ArrayList;
import java.io.IOException;
public class Main {
    public static void main(String[] args) throws IOException{

        Graph g1 = new Graph("C:\\Users\\aluno\\Grafos\\src\\graph1.txt", 'l');


        System.out.println(g1);
        System.out.println(g1.breadthFirstSearch(4));
        System.out.println(g1.hasCycle());
    }
}
