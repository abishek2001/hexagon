import java.util.*;

public class Graph {
    
 
    private static int[][] B;              
    private static boolean [] visited;
    
    public Graph(int N){                             
        B = new int[N][N];
        visited = new boolean[B.length];
    }
    
    public void addEdge(int u, int v, int w){       
        this.B[u][v] = w;
        this.B[v][u] = w;
    }
    
    public void removeEdge(int u, int v){         
        this.B[u][v] = 0;
        this.B[v][u] = 0;
    }
    
    public int getEdge(int u, int v){               
        return this.B[u][v];
    }
    
    public boolean isEdge(int u, int v){            
        if(this.B[u][v] != 0){
            return true;
        }else{
            return false;
        }
    }
    
    public int degree(int v){                       
        int counter = 0;
        for(int i = 0; i < this.B[v].length; ++i){
            if(this.B[v][i] != 0){
                counter++;
            }
        }
        return counter;
    }
    
    public int degree(int v, int w){                
        int counter = 0;
        for(int i = 0; i < this.B[v].length; ++i){
            if(this.B[v][i] == w){
                counter++;
            }
        }
        return counter;
    }
    
    public int sizeOfGraph(){
        return B.length;
    }
    
    public boolean isFull(){
        for(int i = 0; i < B.length; i++){
            if(degree(i) < B.length - 1)
                return false;
        }
        return true;                
    }
    
    public void printEdges(){                       
        System.out.print("     ");
        for(int i = 1; i < this.B.length +1; ++i){
            System.out.print(i+"   ");
        }
        System.out.println();
        System.out.print("     ");
        for(int i = 0; i < this.B.length; ++i){
            System.out.print("-   ");
        }
        System.out.println();
        for(int i = 0; i < this.B.length; ++i){
            System.out.print((i+1)+" |  ");
            for(int n = 0; n < this.B.length; ++n){
                if(n<5 && this.B[i][n+1] == -1){
                    System.out.print(this.B[i][n]+"  ");
                }else{
                System.out.print(this.B[i][n]+"   ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
    
    public boolean isCycleOfLength(int n, int w){   
        for(int i = 0; i < B.length; i++){
            visited[i] = true;
            for(int j = 0; j < B.length; j++){
                visited[j] = true;
                if(getEdge(i, j) == w){
                    if(isCycleHelper(j, i, n, w))
                        return true;
                }
                visited[j] = false;
            }
            visited[i] = false;
        }
        return false;
    }
    
    private boolean isCycleHelper(int u, int v, int n, int w){
        if(n == 2){
            if(getEdge(u, v) == w)
                return true;
            else
                return false;
        }else{
            for(int i = 0; i < B.length; i++){
                if(getEdge(u, i) == w && !visited[i]){
                    visited[i] = true;
                    boolean temp = isCycleHelper(i, v, --n, w);
                    visited[i] = false;
                    return temp;
                }
            }
        }
        return false;
    }
    
    public static void main(String[] args){
        Graph G = new Graph(6);
        
        
        System.out.println("Going to draw lines of red among points 1, 2, and 3");
        System.out.println(); 
        G.addEdge(0, 1, 1);
        G.addEdge(1, 2, 1);
        G.addEdge(0, 2, 1);
        G.printEdges();
        
       
        System.out.println("Going to remove lines of red among points 2 and 3");
        System.out.println();
        G.removeEdge(1, 2);
        G.printEdges();
        
        
        System.out.println("Going to get the value of the edge from 1 to 2");
        System.out.println();
        G.printEdges();
        System.out.println("1");
        System.out.println(G.getEdge(0, 1));
        System.out.println();
        
       
        System.out.println("Going to see if there is an edge between 1 and 2");
        System.out.println();
        G.printEdges();
        System.out.println("true");
        System.out.println(G.isEdge(0, 1));
        System.out.println();
        System.out.println("Going to see if there is an edge between 5 and 6");
        System.out.println();
        G.printEdges();
        System.out.println("false");
        System.out.println(G.isEdge(4, 5));
        System.out.println();
        
        System.out.println("Adding more vertices: Blue 4 to 5, Blue 5 to 6, and Blue 3 to 5");
        G.addEdge(3, 4, -1);
        G.addEdge(4, 5, -1);
        G.addEdge(2, 4, -1);
        System.out.println();
        
        
        System.out.println("Going to see the number of edges connected to vertex 3");
        System.out.println();
        G.printEdges();
        System.out.println("2");
        System.out.println(G.degree(2));
        System.out.println();
        
        
        System.out.println("Going to see the number of Blue edges connected to vertex 3");
        System.out.println();
        G.printEdges();
        System.out.println("1");
        System.out.println(G.degree(2, 1));
        System.out.println();
         
        System.out.println("Going to see if there are any red triangles amongst the current graph");
        System.out.println();
        G.printEdges();
        System.out.println("false");
        System.out.println(G.isCycleOfLength(3, 1));
        System.out.println();
        
        System.out.println("Now going to add a triangle and see if a blue cycle is found");
        G.addEdge(2, 5, -1);
        G.addEdge(0, 5, -1);
        G.addEdge(0, 2, -1);
        System.out.println();
        System.out.println("true");
        System.out.println(G.isCycleOfLength(3, -1));

    }
    
}