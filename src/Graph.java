import java.util.ArrayList;


public class Graph {
    final private int countNodes;
    private int countEdges;
    private int[][] adjmatrix ;
    private ArrayList<Edge>[] adjList;
    private char listaOuMatrix;

    public Graph(int numNodes, char listaOuMatrix){
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

    public int getCountEdges() {
        return countEdges;
    }
    @Override
    public String toString(){
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
        }
        else{
            for (int i = 0; i < this.adjList.length; i++) {
                str += "["+i+"]:\t";
                for (Edge edge : this.adjList[i]) {
                    str += "("+edge.getEdge()+","+edge.getWeight()+"), ";
                }
                str = str.substring(0,str.length()-2);
                str += "\n";
            }
        }
        return str;
    }
    public void addEdge(int u , int v, int w){
        if(this.listaOuMatrix == 'm') {
        if( u < 0 || u > this.adjmatrix.length - 1||
            v<0|| v > this.adjmatrix.length - 1||
            w <= 0
        ){
            System.err.println("aresta "+u+" "+v+" está invalida");
            return;
        }
            this.adjmatrix[u][v] = w;
        }
        else
        {
        if( u < 0 || u > this.adjList.length - 1||
                v<0|| v > this.adjList.length - 1||
                w <= 0){
            System.err.println("aresta "+u+" "+v+" está invalida");
        }
            this.adjList[u].add(new Edge(v,w));
        }
        this.countEdges++;
    }

    public int degreee(int node){
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
        }
        else{
            if(node > this.adjList.length){
                System.err.println("vertice escolhido é maior que o grafo");
            }
           conta = this.adjList[node].size();
        }
        return conta;
    }
    public int highestDegree(){

        int maior = this.degreee(0);
        int temp;
        if (this.listaOuMatrix == 'm') {
            for (int i = 0; i < this.adjmatrix.length; i++) {
                temp = this.degreee(i);
                if (temp > maior)
                    maior = temp;
            }
        }
        else{
            for (int i = 0; i <this.adjList.length ; i++) {
                temp = this.degreee(i);
                if (temp > maior)
                    maior = temp;
            }
        }
        return maior;
    }
    public float density(){

        return (this.countEdges)/(this.countNodes * (this.countNodes - 1));
    }
    public boolean oriented(){
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
        }
        else
        {
            //ta Errado

            int index = 0, j = 0;
            for (int i = 0; i <this.adjList.length ; i++) {
                for (Edge edge: this.adjList[i]) {
                    for (Edge edge1: this.adjList[edge.getEdge()]) {
                        if (edge1.getEdge() == edge.getEdge())
                        index = j;
                        j++;
                        }
                    if(edge.getEdge() == this.adjList[edge.getEdge()].get(index).getEdge())
                        eVerdade = false;
                    else
                        return true;
                    }
                }
            }

        return  eVerdade;

    }
    ArrayList<Integer> breadthFirstSearch(int s){
        boolean[] descoberto = new boolean[this.countNodes];
        ArrayList<Integer> queue = new ArrayList<>();
        ArrayList<Integer> retorno = new ArrayList<>();

        queue.add(s);
        retorno.add(s);
        descoberto[s] = true;

        int priQ;
        while (queue.size()>0){
            priQ = queue.remove(0);
            for (Edge edge: this.adjList[priQ]) {
                if(!descoberto[edge.getEdge()]){
                    queue.add(edge.getEdge());
                    retorno.add(edge.getEdge());
                    descoberto[edge.getEdge()] = true;
                }
            }
        }
        return retorno;
    }
}
