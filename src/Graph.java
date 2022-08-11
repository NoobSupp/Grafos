public class Graph {
    private int countNodes;
    private int countEdges;
    private int[][] adjmatrix ;

    public Graph(int numNodes){
        this.countNodes = numNodes;
        this.countEdges = 0;
        this.adjmatrix = new int [countNodes][countNodes];
    }

    public int getCountEdges() {
        return countEdges;
    }
    @Override
    public String toString(){
        String str = "";
        for(int i = 0; i < this.adjmatrix.length; i++){
            for (int j = 0; j < this.adjmatrix[i].length; j++){
                str += this.adjmatrix[i][j] + "\t";
            }
            str += "\n";
        }
        return str;
    }
    public void addEdge(int u , int v, int w){
        if( u < 0 || u > this.adjmatrix.length - 1||
            v<0|| v > this.adjmatrix.length - 1||
            w <= 0
        ){
            System.err.println("aresta "+u+" "+v+" está invalida");
            return;
        }
            this.countEdges++;
            this.adjmatrix[u][v] = w;
    }
    public int degreee(int node){
        if(node > this.adjmatrix[node].length - 1){
            System.err.println("vertice escolhido é maior que o grafo");
            return -1;
        }
    int conta = 0;
        for(int i = 0 ; i < this.adjmatrix[node].length ; i++){
            if(this.adjmatrix[node][i] > 0)
            conta++;
        }
        return conta;
    }

}