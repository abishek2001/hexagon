    public class Player{
      
      private int D = 10;

      public Move chooseMove(Graph G) { 
        int max = -10000;

        Move best = new Move(0, 1);
        for(int mmm = 0; mmm < 5; mmm++){
            for(int nnn = 0; nnn < 5; nnn++){
                if(G.isEdge(mmm, nnn) == false && mmm != nnn){
                    best = new Move(mmm, nnn);
            }
          }
        }

        for(int i = 0; i < G.sizeOfGraph(); i++){
          for(int j = i+1; j < G.sizeOfGraph(); j++){
            if(i != j && G.isEdge(i, j) == false){
              System.out.println(i + j + " are I and J");
              G.addEdge(i, j, -1);
              Move m = new Move(i, j);
              int val = minMax( G, 1, 0, 0);
              
              if(val >= max) {
                            System.out.println("New best");
                best = m;
                max = val; 
              }
              G.removeEdge(i, j);
            }
          }
        }
        System.out.println(best);
        return best; 
      }

      
      private int eval(Graph G){
        int i = 0;
        for(int j = 0; j < G.sizeOfGraph(); j++){
          if(G.degree(j, 1) == 1)
            i += 1;
          if(G.degree(j, 1) > 1)
            i += 6;
          if(G.degree(j, -1) == 1)
            i -= 1;
          if(G.degree(j, -1) > 1)
            i -= 4;
        }
        if(G.isCycleOfLength(3, 1))
          i = 1000000000;
        if(G.isCycleOfLength(3, -1))
          i = -1000000000;
        return i;
      }

     
      int minMax(Graph G, int depth, int alpha, int beta ) {  
        if(depth == D || G.isFull())  
          return eval(G);  
        else if(depth%2 == 0) {  
          int val = -100000000;  
          for(int i = 0; i < G.sizeOfGraph(); i++){
            for(int j = i+1; j < G.sizeOfGraph(); j++){
              if(!G.isEdge(i, j)){
                alpha = Math.max(alpha, val);   
                if(beta < alpha) break; 
                G.addEdge(i, j, -1);
                val = Math.max(val, minMax( G, depth+1, alpha, beta ));
                G.removeEdge(i, j);
              }
            }
          }  
          return val;  
        } else {   
          int val = 10000000;  
          for(int i = 0; i < G.sizeOfGraph(); i++){
            for(int j = i+1; j < G.sizeOfGraph(); j++){
              if(!G.isEdge(i, j)){
                beta = Math.min(beta, val); 
                if(beta < alpha) break; 
                G.addEdge(i, j, 1);
                val = Math.min(val, minMax( G, depth+1, alpha, beta ) );
                G.removeEdge(i, j);
              }
            }
          }
          return val;  
        } 
      }
    }