



package SolverPackage;



import LinkedList.LinkedList;
import java.util.Arrays;

public class PathFinderAlgo  {
    
    
    private static int orangeVal = -4;
    
    
    public static int[][] pathFind(int[][] board,int[] pos,int[] tar,int numTar,int numPos,int normal,
        boolean isSecondPath){
        LinkedList<int[]> list = new LinkedList<>();
        // Change the player to 1 
        //int target = board[ tar[0]][ tar[1]];
        board[ pos[0] ][ pos[1] ] = normal;
        // Stack the position 
        list.push(pos);
        for ( int i = 0 ; i < list.size() ;i++ ){
            for(int j = 0 ; j < list.size();j++){
                int x = list.get(j)[0];
                int y = list.get(j)[1];
                if(x == tar[0] && y == tar[1] ){
                    return locator(board,pos,tar,numTar,numPos,isSecondPath);
                }
                
               
                if(x > 0){
                     if( board[x-1][y] == 0 ||  board[x-1][y] == numTar){
                        board[x-1][y] = board[x][y]+1;
                        int arr [] = {x-1,y};
                        list.push(arr);
                        
                     }
                }

                if(y > 0){
                    if(board[x][y-1] == 0 || board[x][y-1] == numTar){
                        board[x][y-1] = board[x][y]+1;
                        int arr[] = {x,y-1};
                        list.push(arr);
                       
                    }
                }
                 
                if(y < board[x].length -1){
                    if(board[x][y+1] == 0 || board[x][y+1] == numTar){
                        board[x][y+1] = board[x][y]+1;
                        int arr[] = {x,y+1};
                        list.push(arr);
                    }
                }
                if(x < board.length -1){
                    if(board[x+1][y] == 0 || board[x+1][y] == numTar){
                        board[x+1][y] = board[x][y]+1;
                        int arr[] = {x+1,y};
                        list.push(arr);
                      
                    }
                }
            }
        }
        return board;
    }
    
    
    public static int[][] locator(int board[][],int[] pos,int[] tar,int numTar ,int numPos,boolean isSecondPath ){
        
        LinkedList<int[]> list = new LinkedList<>();
        
        // -3 = path 
        // -2 = target 
        // -1 = barrier 
        // -0 = empty space 
        // 1  = player
        // else all pathways
        
        
       // board[pos[0]][pos[1]] = numPos;
        
        
        int targetVal = board[tar[0]][tar[1]];
        list.push(tar);
        for(int i = 0 ; i < list.size() ;i++){
            for(int j = 0 ; j < list.size() ; j++){
                int x = list.get(j)[0];
                int y = list.get(j)[1];
                
                
                if(x == pos[0] && y == pos[1]){
                    board[tar[0]][tar[1]] = numTar ;
                    board[pos[0]][pos[1]] = numPos;
                    return board ;
                }
                if(x < board.length -1){
                    if((board[x+1][y] >1 || board[x+1][y] == numTar )&& board[x+1][y] <targetVal ){
                        targetVal = board[x+1][y];
                        board[x+1][y] = -3;
                        int arr[] = {x+1, y};
                        list.push(arr);
                    }
                }
                if(y < board[x].length -1){
                    if(  (board[x][y+1] >1 ||board[x][y+1]  == numTar )   && board[x][y+1] < targetVal  ){
                        targetVal = board[x][y+1];
                        board[x][y+1] = -3;
                        int arr[] = {x, y+1};
                        list.push(arr);
                    }
                }
                if(x > 0){
                    if(  (board[x-1][y] > 1 || board[x-1][y] == numTar) &&  board[x-1][y]< targetVal  ){
                        targetVal = board[x-1][y];
                        board[x-1][y] = -3;
                        int arr[] = {x-1,y};
                        list.push(arr);
                    }
                }
                
                if(y > 0){
                    if(  (board[x][y-1] >1|| board[x][y-1] == numTar ) &&  board[x][y-1]< targetVal ){
                        targetVal = board[x][y-1];
                        board[x][y-1] = -3;
                        int arr[] = {x,y-1};
                        list.push(arr);
                    }
                }
                 
            }
        }
        orangeVal = board[tar[0]][tar[1]];
        board[tar[0]][tar[1]] = numTar;
        board[pos[0]][pos[1]] = numPos;
        return board;
    }
             
     public static int[][] process( int board[][] ,int[] position,int[] target,int[] point){
         try{
             for(int i = 0 ; i < board.length ; i ++)
                 for(int j = 0 ; j < board[i].length ; j++)
                     if(board[i][j] > 1 || board[i][j] == -3 || board[i][j] == -5 )
                         board[i][j] = 0;
             
             
          
            return mainPorcess(board,position,target,point);
         }catch(Exception e){
             return board;
         }
     }

    private static int[][] mainPorcess(int[][] board, int[] position, int[] target, int[] point) {
        //System.out.println(board[point[0]][point[1]]);
        
        //tar,int numTar,int numPos
        
        if(target == null){
            //System.out.println("he");
            pathFind1(board,position,point,-4,1,1,false); // Yellow to orange
        }else if (point == null){
                //System.out.println("he");
             pathFind(board,position,target,-2,1,1,false); // Yellow to red
        }else if(position == null){
             pathFind1(board,point,target,-2,-4,1,true); // OrangeToRed
        }else{
            pathFind1(board,position,point,-4,1,1,false); // Yellow to orange
            
            
            for(int i = 0 ; i < board.length ; i ++)
                 for(int j = 0 ; j < board[i].length ; j++)
                     if(board[i][j] > 1 )
                        board[i][j] = 0;
            
            
            //System.out.println(board[point[0]][point[1]]+" "+board[target[0]][target[1]]);
            pathFind(board,point,target,-2,-4,orangeVal,true); // OrangeToRed
        }
       
        //
        //pathFind(board,target,point,-4,-2);
        //pathFind(board,position,point,-,-4);
       
        return board;
    }
   
    
    
    public static int[][] pathFind1(int[][] board,int[] pos,int[] tar,int numTar,int numPos,int normal,
        boolean isSecondPath){
        LinkedList<int[]> list = new LinkedList<>();
        // Change the player to 1 
        //int target = board[ tar[0]][ tar[1]];
        board[ pos[0] ][ pos[1] ] = normal;
        // Stack the position 
        list.push(pos);
        for ( int i = 0 ; i < list.size() ;i++ ){
            for(int j = 0 ; j < list.size();j++){
                int x = list.get(j)[0];
                int y = list.get(j)[1];
                if(x == tar[0] && y == tar[1] ){
                    return locator1(board,pos,tar,numTar,numPos,isSecondPath);
                }
                
               
                if(x > 0){
                     if( board[x-1][y] == 0 ||  board[x-1][y] == numTar){
                        board[x-1][y] = board[x][y]+1;
                        int arr [] = {x-1,y};
                        list.push(arr);
                        
                     }
                }

                if(y > 0){
                    if(board[x][y-1] == 0 || board[x][y-1] == numTar){
                        board[x][y-1] = board[x][y]+1;
                        int arr[] = {x,y-1};
                        list.push(arr);
                       
                    }
                }
                 
                if(y < board[x].length -1){
                    if(board[x][y+1] == 0 || board[x][y+1] == numTar){
                        board[x][y+1] = board[x][y]+1;
                        int arr[] = {x,y+1};
                        list.push(arr);
                    }
                }
                if(x < board.length -1){
                    if(board[x+1][y] == 0 || board[x+1][y] == numTar){
                        board[x+1][y] = board[x][y]+1;
                        int arr[] = {x+1,y};
                        list.push(arr);
                      
                    }
                }
            }
        }
        return board;
    }
    public static int[][] locator1(int board[][],int[] pos,int[] tar,int numTar ,int numPos,boolean isSecondPath ){
        LinkedList<int[]> list = new LinkedList<>();
        // -3 = path 
        // -2 = target 
        // -1 = barrier 
        // -0 = empty space 
        // 1  = player
        // else all pathways
       // board[pos[0]][pos[1]] = numPos;
        int targetVal = board[tar[0]][tar[1]];
        list.push(tar);
        for(int i = 0 ; i < list.size() ;i++){
            for(int j = 0 ; j < list.size() ; j++){
                int x = list.get(j)[0];
                int y = list.get(j)[1];
                
                
                if(x == pos[0] && y == pos[1]){
                    board[tar[0]][tar[1]] = numTar ;
                    board[pos[0]][pos[1]] = numPos;
                    return board ;
                }
                if(x < board.length -1){
                    if((board[x+1][y] >1 || board[x+1][y] == numTar )&& board[x+1][y] <targetVal ){
                        targetVal = board[x+1][y];
                        board[x+1][y] = -5;
                        int arr[] = {x+1, y};
                        list.push(arr);
                    }
                }
                if(y < board[x].length -1){
                    if(  (board[x][y+1] >1 ||board[x][y+1]  == numTar )   && board[x][y+1] < targetVal  ){
                        targetVal = board[x][y+1];
                        board[x][y+1] = -5;
                        int arr[] = {x, y+1};
                        list.push(arr);
                    }
                }
                if(x > 0){
                    if(  (board[x-1][y] > 1 || board[x-1][y] == numTar) &&  board[x-1][y]< targetVal  ){
                        targetVal = board[x-1][y];
                        board[x-1][y] = -5;
                        int arr[] = {x-1,y};
                        list.push(arr);
                    }
                }
                
                if(y > 0){
                    if(  (board[x][y-1] >1|| board[x][y-1] == numTar ) &&  board[x][y-1]< targetVal ){
                        targetVal = board[x][y-1];
                        board[x][y-1] = -5;
                        int arr[] = {x,y-1};
                        list.push(arr);
                    }
                }
            }
        }
        orangeVal = board[tar[0]][tar[1]];
        board[tar[0]][tar[1]] = numTar;
        board[pos[0]][pos[1]] = numPos;
        return board;
    }
}
