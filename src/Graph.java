import java.util.ArrayList;

public class Graph {
    private int countNodes;
    private int countEdges;
    private int[][] adjmatrix ;
    private ArrayList<Edge>[] adjList;
    private char listaOuMatrix;

    public Graph(int numNodes, char listaOuMatrix){
        this.countNodes = numNodes;
        this.countEdges = 0;

        switch (listaOuMatrix) {

            case 'm':
                this.adjmatrix = new int[countNodes][countNodes];
                this.listaOuMatrix = listaOuMatrix;
            break;

            case 'l' :
                this.adjList = new ArrayList[countNodes];
                for (int i = 0; i < this.countNodes; i++) {
                    this.adjList[i] = new ArrayList<Edge>();
                }
                this.listaOuMatrix = listaOuMatrix;
            break;

            default:
                System.err.println("erro ao escolher entre lista ou matrix de adjacencia");
        }
    }

    public int getCountEdges() {
        return countEdges;
    }
    @Override
    public String toString(){
        String str = "";
        if (this.listaOuMatrix == 'm') {
            int[][] adjmatrix1 = this.adjmatrix;
            for (int i = 0; i < adjmatrix.length; i++) {
                int[] ints = adjmatrix[i];
                for (int j = 0; j < ints.length; j++) {
                    str += ints[j] + "\t";
                }
                str += "\n";
            }
        }
        else{
            str = "Não implementei ainda";
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
                v<0|| v > this.adjmatrix.length - 1||
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
            int conta = 0;
            for (int i = 0; i < this.adjmatrix[node].length; i++) {
                if (this.adjmatrix[node][i] > 0)
                    conta++;
            }
        }
        else{

        }
        return conta;
    }
    public int highestDegree(){

        int maior = this.degreee(0);
        for(int i = 0; i< this.adjmatrix.length; i++){
            if(this.degreee(i) > maior)
            maior = this.degreee(i);
        }
        return maior;
    }
    public float density(){

        return (this.countEdges)/(this.countNodes * (this.countNodes - 1));
    }
    public boolean oriented(){
        boolean eVerdade = false;
        for (int i = 0; i < this.adjmatrix.length; i++) {
            for (int j = 0; j < this.adjmatrix[i].length; j++) {
                if(this.adjmatrix[i][j] == this.adjmatrix[j][i]){
                    eVerdade = false;
                }else{
                    return true;

                }
            }
        }
        return  eVerdade;

    };

}
