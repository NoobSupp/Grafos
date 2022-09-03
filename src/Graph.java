import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

public class Graph {
    final private int countNodes;
    private int countEdges;
    private int[][] adjmatrix;
    private ArrayList<Edge>[] adjList;
    private char listaOuMatrix;

    public Graph(int numNodes, char listaOuMatrix) {
        this.countNodes = numNodes;
        this.countEdges = 0;

        switch (listaOuMatrix) {
            case 'm' -> {
                this.adjmatrix = new int[countNodes][countNodes];
                this.listaOuMatrix = listaOuMatrix;
            }
            case 'l' -> {
                this.adjList = new ArrayList[countNodes];
                for (int i = 0; i < this.countNodes; i++) {
                    this.adjList[i] = new ArrayList<Edge>();
                }
                this.listaOuMatrix = listaOuMatrix;
            }
            default -> System.err.println("erro ao escolher entre lista ou matrix de adjacencia");
        }
    }

    public Graph(String fileName,char listaOuMatriz)throws IOException {
        this.listaOuMatrix = listaOuMatriz;
        File file = new File(fileName);
        FileReader reader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(reader);

        // Read header
        String[] line = bufferedReader.readLine().split(" ");
        this.countNodes = (Integer.parseInt(line[0]));
        int fileLines = (Integer.parseInt(line[1]));
// Create and fill adjMatrix with read edges
        if (this.listaOuMatrix == 'm')
        this.adjmatrix = new int[this.countNodes][this.countNodes];
        else {
            this.adjList = new ArrayList[countNodes];
            for (int i = 0; i < this.countNodes; i++) {
                this.adjList[i] = new ArrayList<Edge>();
            }
        }
        for (int i = 0; i < fileLines; ++i) {
            String[] edgeInfo = bufferedReader.readLine().split(" ");
            int source = Integer.parseInt(edgeInfo[0]);
            int sink = Integer.parseInt(edgeInfo[1]);
            int weight = Integer.parseInt(edgeInfo[2]);
            addEdge(source, sink, weight);
        }
        bufferedReader.close();
        reader.close();

    }

    public int getCountEdges() {
        return countEdges;
    }

    @Override
    public String toString() {
        String str = "";
        if (this.listaOuMatrix == 'm') {
            int[][] adjmatrix = this.adjmatrix;
            for (int i = 0; i < adjmatrix.length; i++) {
                int[] ints = adjmatrix[i];
                for (int j = 0; j < ints.length; j++) {
                    str += ints[j] + "\t";
                }
                str += "\n";
            }
        } else {
            for (int i = 0; i < this.adjList.length; i++) {
                str += "[" + i + "]:\t";
                for (Edge edge : this.adjList[i]) {
                    str += "(" + edge.getEdge() + "," + edge.getWeight() + "), ";
                }
                str = str.substring(0, str.length() - 2);
                str += "\n";
            }
        }
        return str;
    }

    public void addEdge(int u, int v, int w) {
        if (this.listaOuMatrix == 'm') {
            if (u < 0 || u > this.adjmatrix.length - 1 ||
                    v < 0 || v > this.adjmatrix.length - 1 ||
                    w <= 0
            ) {
                System.err.println("aresta " + u + " " + v + " está invalida");
                return;
            }
            this.adjmatrix[u][v] = w;
        } else {
            if (u < 0 || u > this.adjList.length - 1 ||
                    v < 0 || v > this.adjList.length - 1 ||
                    w <= 0) {
                System.err.println("aresta " + u + " " + v + " está invalida");
            }
            this.adjList[u].add(new Edge(v, w));
        }
        this.countEdges++;
    }

    public void addEdgeUnorieted(int u, int v, int w) {
        if (this.listaOuMatrix == 'm') {
            if (u < 0 || u > this.adjmatrix.length - 1 ||
                    v < 0 || v > this.adjmatrix.length - 1 ||
                    w <= 0
            ) {
                System.err.println("aresta " + u + " " + v + " está invalida");
                return;
            }
            this.adjmatrix[u][v] = w;
            this.adjmatrix[v][u] = w;
        } else {
            if (u < 0 || u > this.adjList.length - 1 ||
                    v < 0 || v > this.adjList.length - 1 ||
                    w <= 0) {
                System.err.println("aresta " + u + " " + v + " está invalida");
            }
            this.adjList[u].add(new Edge(v, w));
            this.adjList[v].add(new Edge(u, w));
        }
        this.countEdges += 2;
    }

    public int degreee(int node) {
        int conta = 0;
        if (this.listaOuMatrix == 'm') {


            if (node > this.adjmatrix[node].length - 1) {
                System.err.println("vertice escolhido é maior que o grafo");
                return -1;
            }

            for (int i = 0; i < this.adjmatrix[node].length; i++) {
                if (this.adjmatrix[node][i] > 0)
                    conta++;
            }
        } else {
            if (node > this.adjList.length) {
                System.err.println("vertice escolhido é maior que o grafo");
            }
            conta = this.adjList[node].size();
        }
        return conta;
    }

    public int highestDegree() {

        int maior = this.degreee(0);
        int temp;
        if (this.listaOuMatrix == 'm') {
            for (int i = 0; i < this.adjmatrix.length; i++) {
                temp = this.degreee(i);
                if (temp > maior)
                    maior = temp;
            }
        } else {
            for (int i = 0; i < this.adjList.length; i++) {
                temp = this.degreee(i);
                if (temp > maior)
                    maior = temp;
            }
        }
        return maior;
    }

    public float density() {

        return (this.countEdges) / (this.countNodes * (this.countNodes - 1));
    }

    public boolean oriented() {
        boolean eVerdade = false;
        if (this.listaOuMatrix == 'm') {
            for (int i = 0; i < this.adjmatrix.length; i++) {
                for (int j = 0; j < this.adjmatrix[i].length; j++) {
                    if (this.adjmatrix[i][j] == this.adjmatrix[j][i]) {
                        eVerdade = false;
                    } else {
                        return true;

                    }
                }
            }
        } else {
            //ta Errado

            int index = 0, j = 0;
            for (int i = 0; i < this.adjList.length; i++) {
                for (Edge edge : this.adjList[i]) {
                    for (Edge edge1 : this.adjList[edge.getEdge()]) {
                        if (edge1.getEdge() == edge.getEdge())
                            index = j;
                        j++;
                    }
                    if (edge.getEdge() == this.adjList[edge.getEdge()].get(index).getEdge())
                        eVerdade = false;
                    else
                        return true;
                }
            }
        }

        return eVerdade;

    }
    public ArrayList<Integer> edgeAdjList(int edge){
        ArrayList<Integer>list = new ArrayList<>();

        for (int i = 0; i <this.adjmatrix.length ; i++) {
            if(this.adjmatrix[edge][i] != 0){
                list.add(i);
            }
        }
        return list;
    }
    public ArrayList<Integer> breadthFirstSearch(int s) {
        boolean[] descoberto = new boolean[this.countNodes];
        ArrayList<Integer> queue = new ArrayList<>();
        ArrayList<Integer> retorno = new ArrayList<>();

        if (this.listaOuMatrix == 'l') {
            queue.add(s);
            retorno.add(s);
            descoberto[s] = true;

            int priQ;
            while (queue.size() > 0) {
                priQ = queue.remove(0);
                for (Edge edge : this.adjList[priQ]) {
                    if (!descoberto[edge.getEdge()]) {
                        queue.add(edge.getEdge());
                        retorno.add(edge.getEdge());
                        descoberto[edge.getEdge()] = true;
                    }
                }
            }
        } else {

            queue.add(s);
            retorno.add(s);
            descoberto[s] = true;

            int priQ;
            ArrayList<Integer> vAdjacenteAu;
            while (queue.size() > 0) {
                priQ = queue.remove(0);
                vAdjacenteAu = this.edgeAdjList(priQ);
                for (int edge : vAdjacenteAu) {
                    if(!descoberto[edge]){
                        queue.add(edge);
                        retorno.add(edge);
                        descoberto[edge] = true;
                    }
                }
            }
        }
        return retorno;
    }
    public boolean isConnected() {
    return this.breadthFirstSearch(0).size() == this.countNodes;
    }

    public int notDescAdj(int u, boolean[]desc){
        for (Edge edge: this.adjList[u]) {
            if (!desc[edge.getEdge()]){
                return edge.getEdge();
            }
        }
        return -1;
    }

    public ArrayList<Integer> depthFirstSearch(int inicio){
        boolean[] descoberto = new boolean[this.countNodes];
        Stack<Integer>pilha = new Stack<>();
        ArrayList<Integer>retorno = new ArrayList<>();
        int u;

        pilha.add(inicio);
        retorno.add(inicio);
        descoberto[inicio] = true;
        while (pilha.size()!=0){
            u = pilha.peek();
            int v = this.notDescAdj(u, descoberto);
            if (v != -1){
                pilha.add(v);
                retorno.add(v);
                descoberto[v]= true;
            }
            else
            {
                pilha.pop();
            }

        }
        return retorno;
    }

    public ArrayList<Integer> dpsRecursive(int s){
        boolean[]desc = new boolean[this.countNodes];
        ArrayList<Integer> retorno = new ArrayList<>();

        return retorno;
    }

    private void dfsRecursiveAux(int u, ArrayList<Integer>retorno, boolean[] descoberto){
        descoberto[u] = true;
        retorno.add(u);

        }
   public ArrayList<Integer> ordTopologica(){
        boolean desc[] = new boolean[this.countNodes];
        ArrayList<Integer> Retorno = new ArrayList<Integer>();

       for (int i = 0; i <this.adjList.length ; i++) {
           if(!desc[i]){
               ordTopologicaAux(i,desc,Retorno);
           }
       }
       return Retorno;
   }
   void ordTopologicaAux(int u, boolean[] desc, ArrayList<Integer> retorno){
        desc[u] = true;

       for (Edge edge: this.adjList[u]) {
               if(!desc[edge.getEdge()]){
                   ordTopologicaAux(edge.getEdge(),desc, retorno);

               }

       }
       retorno.add(0,u);
   }
   public ArrayList<Integer> connectedComp(){
        int [] desc = new int[this.countNodes];
        ArrayList<Integer> retorno = new ArrayList<>();
        int comp = 0;

       for (int i = 0; i < this.adjList.length ; i++) {
           if(desc[i] == 0){
               comp++;
               connectedCompAux(i, desc, comp);
           }
       }
       for (int i = 0; i < desc.length; i++) {
           retorno.add(desc[i]);
       }
       return retorno;
   }

   private void connectedCompAux(int u, int[]desc, int comp){
        desc[u] = comp;

       for (Edge edge: this.adjList[u]
            ) {
           if(desc[edge.getEdge()] == 0)
               connectedCompAux(edge.getEdge(),desc,comp);
       }
   }
   private boolean hasCycleaux(int s){
       boolean[] descoberto = new boolean[this.countNodes];
       ArrayList<Integer> queue = new ArrayList<>();
       ArrayList<Integer> retorno = new ArrayList<>();

       queue.add(s);
       retorno.add(s);
       descoberto[s] = true;

       int priQ;
       while (queue.size() > 0) {
           priQ = queue.remove(0);
           for (Edge edge : this.adjList[priQ]) {
               if (!descoberto[edge.getEdge()]) {
                   queue.add(edge.getEdge());
                   retorno.add(edge.getEdge());
                   descoberto[edge.getEdge()] = true;
               } else return true;
           }
       }
       return false;
   }
   public boolean hasCycle(){
       for (int i = 0; i < this.adjList.length; i++) {
           if(this.hasCycleaux(i))
               return true;
       }
        return false;
   }
}
