
import DesignPackage.Properties;
import LinkedList.LinkedList;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JPanel;


import SolverPackage.PathFinderAlgo;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

import javax.swing.Timer;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class PathFinderInterface extends javax.swing.JPanel implements KeyListener,ActionListener {
    private Properties settings = new Properties();
    private Timer timer;
    private boolean  hold = false;
    private int pick = -1;
    private int previous = 0;
    private int objectX= 0;
    private int objectY=0;
    private int[] position;
    private int[] target;
    private int[] points ;
    private  Thread thread = new Thread();
    private int speed = 50;
    private boolean running = false;  
    private int count = 0;
    
    public PathFinderInterface() {
        initComponents();
  
//
//        setLayout(null);
//        addKeyListener(this);
//        setFocusable(true);
//        setFocusTraversalKeysEnabled(false);
            
       int[] rgbs = settings.COLORS[0];
       int reds = rgbs[0] ,greens = rgbs[1],blues = rgbs[2];
        setBackground(new Color(reds,greens,blues));  
        settings.board =  new int[ settings.ROWS][settings.COLUMNS];
        position = null;
        target = null;
        points = null;
        running = false;
        System.out.println("reset");
       
       
       JLabel lab1 = new JLabel("RESET");
       add(lab1);
       lab1.setForeground(Color.WHITE);
       lab1.setLocation(settings.SIZE*1+settings.GAP*1,  
                 settings.SIZE*(settings.ROWS+2)+settings.GAP*(settings.ROWS+2));
       lab1.setSize(100, 35);
       
       JLabel lab2 = new JLabel("ERASE");
       add(lab2);
       lab2.setForeground(Color.WHITE);
       lab2.setLocation(settings.SIZE*4+settings.GAP*4,  
                 settings.SIZE*(settings.ROWS+2)+settings.GAP*(settings.ROWS+2));
       lab2.setSize(100, 35);
       
       
       JLabel lab3 = new JLabel("TARGET");
       add(lab3);
       lab3.setForeground(Color.WHITE);
       lab3.setLocation(settings.SIZE*7+settings.GAP*7,  
                 settings.SIZE*(settings.ROWS+2)+settings.GAP*(settings.ROWS+2));
       lab3.setSize(100, 35);
       
       
       
         
       JLabel lab4 = new JLabel("POINT");
       add(lab4);
       lab4.setForeground(Color.WHITE);
       lab4.setLocation(settings.SIZE*10+settings.GAP*10,  
                 settings.SIZE*(settings.ROWS+2)+settings.GAP*(settings.ROWS+2));
       lab4.setSize(100, 35);
         
       
       
          
       JLabel lab5 = new JLabel("PLAYER");
       add(lab5);
       lab5.setForeground(Color.WHITE);
       lab5.setLocation(settings.SIZE*13+settings.GAP*13,  
                 settings.SIZE*(settings.ROWS+2)+settings.GAP*(settings.ROWS+2));
       lab5.setSize(100, 35);
       
       
       
       JLabel lab6 = new JLabel("BORDER");
       add(lab6);
       lab6.setForeground(Color.WHITE);
       lab6.setLocation(settings.SIZE*16+settings.GAP*16,  
                 settings.SIZE*(settings.ROWS+2)+settings.GAP*(settings.ROWS+2));
       lab6.setSize(100, 35);
         
        timer = new Timer((int)settings.DELAY,this);
        timer.start();
        
        
   
       
    }
    private int orangeVal = -4;
    
    
    public  int[][] pathFind(int[] pos,int[] tar,int numTar,int numPos,int normal, boolean isSecondPath){
      

        try{
            Thread t = new Thread();
            t.start();
            LinkedList<int[]> list = new LinkedList<>();
            // Change the player to 1 
            //int target = board[ tar[0]][ tar[1]];
            settings.board[ pos[0] ][ pos[1] ] = normal;
            // Stack the position 
            list.push(pos);
            for ( int i = 0 ; i < list.size() ;i++ ){
                for(int j = 0 ; j < list.size();j++){
                    int x = list.get(j)[0];
                    int y = list.get(j)[1];
                    if(x == tar[0] && y == tar[1] ){
                        return locator(pos,tar,numTar,numPos,isSecondPath);
                    }


                    if(x > 0){
                         if( settings.board[x-1][y] == 0 ||  settings.board[x-1][y] == numTar){
                            settings.board[x-1][y] = settings.board[x][y]+1;
                            int arr [] = {x-1,y};
                            list.push(arr);
                            try { Thread.sleep(speed); } catch (Exception ex) {}
                            repaint();

                         }
                    }

                    if(y > 0){
                        if(settings.board[x][y-1] == 0 || settings.board[x][y-1] == numTar){
                            settings.board[x][y-1] = settings.board[x][y]+1;
                            int arr[] = {x,y-1};
                            list.push(arr);
                            try { Thread.sleep(speed); } catch (Exception ex) {}
                            repaint();

                        }
                    }

                    if(y < settings.board[x].length -1){
                        if(settings.board[x][y+1] == 0 || settings.board[x][y+1] == numTar){
                            settings.board[x][y+1] = settings.board[x][y]+1;
                            int arr[] = {x,y+1};
                            list.push(arr);
                            try { Thread.sleep(speed); } catch (Exception ex) {}
                            repaint();
                        }
                    }
                    if(x <settings.board.length -1){
                        if( settings.board[x+1][y] == 0 ||settings.board[x+1][y] == numTar){
                            settings.board[x+1][y] = settings.board[x][y]+1;
                            int arr[] = {x+1,y};
                            list.push(arr);
                            try { Thread.sleep(speed); } catch (Exception ex) {}
                            repaint();

                        }
                    }
                }
            }
            running = false;
            return settings.board;
        }catch(Exception e){
            running = false;
            return settings.board;
        }
    }
    
    
    public int[][] locator(int[] pos,int[] tar,int numTar ,int numPos,boolean isSecondPath ){

        try{
            Thread t = new Thread();
            t.start();
            LinkedList<int[]> list = new LinkedList<>();

            // -3 = path 
            // -2 = target 
            // -1 = barrier 
            // -0 = empty space 
            // 1  = player
            // else all pathways
           // board[pos[0]][pos[1]] = numPos;


            int targetVal = settings.board[tar[0]][tar[1]];
            list.push(tar);
            for(int i = 0 ; i < list.size() ;i++){
                for(int j = 0 ; j < list.size() ; j++){

                    int x = list.get(j)[0];
                    int y = list.get(j)[1];

                    if(x == pos[0] && y == pos[1]){
                        settings.board[tar[0]][tar[1]] = numTar ;
                        settings.board[pos[0]][pos[1]] = numPos;
                        return settings.board ;
                    }
                    if(x < settings.board.length -1){
                        if((settings.board[x+1][y] >1 || settings.board[x+1][y] == numTar )&& settings.board[x+1][y] <targetVal ){
                            targetVal = settings.board[x+1][y];
                            settings.board[x+1][y] = -3;
                            int arr[] = {x+1, y};
                            list.push(arr);
                            try { Thread.sleep(speed); } catch (Exception ex) {}
                            repaint();

                        }
                    }
                    if(y < settings.board[x].length -1){
                        if(  ( settings.board[x][y+1] >1 ||settings.board[x][y+1]  == numTar )   && settings.board[x][y+1] < targetVal  ){
                            targetVal = settings.board[x][y+1];
                            settings.board[x][y+1] = -3;
                            int arr[] = {x, y+1};
                            list.push(arr);
                            try { Thread.sleep(speed); } catch (Exception ex) {}
                            repaint();
                        }
                    }
                    if(x > 0){
                        if(  ( settings.board [x-1][y] > 1 || settings.board[x-1][y] == numTar) &&  settings.board[x-1][y]< targetVal  ){
                            targetVal =settings.board[x-1][y];
                            settings.board[x-1][y] = -3;
                            int arr[] = {x-1,y};
                            list.push(arr);
                            try { Thread.sleep(speed); } catch (Exception ex) {}
                            repaint();
                        }
                    }

                    if(y > 0){
                        if(  (settings.board[x][y-1] >1|| settings.board[x][y-1] == numTar ) && settings.board[x][y-1]< targetVal ){
                            targetVal = settings.board[x][y-1];
                            settings.board[x][y-1] = -3;
                            int arr[] = {x,y-1};
                            list.push(arr);
                            try { Thread.sleep(speed); } catch (Exception ex) {}

                        }
                    }
                }
            }
            orangeVal = settings.board[tar[0]][tar[1]];
            settings.board[tar[0]][tar[1]] = numTar;
            settings.board[pos[0]][pos[1]] = numPos;
            try { Thread.sleep(speed); } catch (Exception ex) {}
            repaint();
                running = false;
            return settings.board;
        }catch(Exception e){
            running = false;
            return settings.board;
        }
    }
    
             
     public  int[][] process( int[] position,int[] target,int[] point){
        
         try{
             Thread t = new Thread();
            t.start();
             for(int i = 0 ; i < settings.board.length ; i ++)
                 for(int j = 0 ; j < settings.board[i].length ; j++)
                     if(settings.board[i][j] > 1 || settings.board[i][j] == -3 || settings.board[i][j] == -5 )
                         settings.board[i][j] = 0;
            
            return mainPorcess(position,target,point);
         }catch(Exception e){
             running = false;
             return settings.board;
         }
     }

    private int[][] mainPorcess( int[] position, int[] target, int[] point) throws InterruptedException {
        //System.out.println(board[point[0]][point[1]]);
        
        //tar,int numTar,int numPos
        
        if(target == null){
            //System.out.println("he");
                Thread t = new Thread(new Runnable(){
                    @Override
                    public void run() {
                         pathFind(position,point,-4,1,1,false); // Yellow to orange
                    }
                });
             t.start();
             
        }else if (point == null){
               
            
             
             Thread t = new Thread(new Runnable(){
                    @Override
                    public void run() {
                          pathFind(position,target,-2,1,1,false); // Yellow to red
                    }
                });
             t.start();
             
             
             
        }else if(position == null){
            
             Thread t = new Thread(new Runnable(){
                    @Override
                    public void run() {
                        pathFind1(point,target,-2,-4,1,true); // OrangeToRed
                        running = false;
                        
                    }
                });
             t.start();
             
        }else{
            Thread t = new Thread(new Runnable(){
                    @Override
                    public void run() {
                    pathFind1(position,point,-4,1,1,false); // Yellow to orange
            
                    for(int i = 0 ; i < settings.board.length ; i ++)
                         for(int j = 0 ; j < settings.board[i].length ; j++)
                             if(settings.board[i][j] > 1 )
                                settings.board[i][j] = 0;
                    //System.out.println(board[point[0]][point[1]]+" "+board[target[0]][target[1]]);
                    pathFind(point,target,-2,-4,orangeVal,true); // OrangeToRed
                    
                    running = false;
                    }
                    
                    
                });
             t.start();
            
        }
       
        //
        //pathFind(board,target,point,-4,-2);
        //pathFind(board,position,point,-,-4);
       
        return settings.board;
    }
   
    
    
    public int[][] pathFind1(int[] pos,int[] tar,int numTar,int numPos,int normal,boolean isSecondPath){
   
        try{
            Thread t = new Thread();
            t.start();
            LinkedList<int[]> list = new LinkedList<>();
        // Change the player to 1 
        //int target = board[ tar[0]][ tar[1]];
        settings.board[ pos[0] ][ pos[1] ] = normal;
        // Stack the position 
        list.push(pos);
        for ( int i = 0 ; i < list.size() ;i++ ){
            for(int j = 0 ; j < list.size();j++){
                int x = list.get(j)[0];
                int y = list.get(j)[1];
                if(x == tar[0] && y == tar[1] ){
                    try { Thread.sleep(speed); } catch (Exception ex) {}
                        repaint();
                    return locator1(pos,tar,numTar,numPos,isSecondPath);
                }
                
               
                if(x > 0){
                     if( settings.board[x-1][y] == 0 ||  settings.board[x-1][y] == numTar){
                        settings.board[x-1][y] = settings.board[x][y]+1;
                        int arr [] = {x-1,y};
                        list.push(arr);
                        try { Thread.sleep(speed); } catch (Exception ex) {}
                        repaint();
                        
                          
                     }
                }

                if(y > 0){
                    if(settings.board[x][y-1] == 0 || settings.board[x][y-1] == numTar){
                        settings.board[x][y-1] = settings.board[x][y]+1;
                        int arr[] = {x,y-1};
                        list.push(arr);
                         try { Thread.sleep(speed); } catch (Exception ex) {}
                        repaint();
                       
                    }
                }
                 
                if(y < settings.board[x].length -1){
                    if(settings.board[x][y+1] == 0 || settings.board[x][y+1] == numTar){
                       settings.board[x][y+1] = settings.board[x][y]+1;
                        int arr[] = {x,y+1};
                        list.push(arr);
                         try { Thread.sleep(speed); } catch (Exception ex) {}
                        repaint();
                    }
                }
                if(x < settings.board.length -1){
                    if(settings.board[x+1][y] == 0 || settings.board[x+1][y] == numTar){
                        settings.board[x+1][y] = settings.board[x][y]+1;
                        int arr[] = {x+1,y};
                        list.push(arr);
                         try { Thread.sleep(speed); } catch (Exception ex) {}
                        repaint();
                      
                    }
                }
            }
        }
        running = false;
        return settings.board;
        }catch(Exception e){
            running = false;
         return settings.board;
        }
        
    }
    
    
    public int[][] locator1(int[] pos,int[] tar,int numTar ,int numPos,boolean isSecondPath ){
       
        try{
            Thread t = new Thread();
            t.start();

            LinkedList<int[]> list = new LinkedList<>();

            // -3 = path 
            // -2 = target 
            // -1 = barrier 
            // -0 = empty space 
            // 1  = player
            // else all pathways

           // board[pos[0]][pos[1]] = numPos;

            int targetVal = settings.board[tar[0]][tar[1]];
            list.push(tar);
            for(int i = 0 ; i < list.size() ;i++){
                for(int j = 0 ; j < list.size() ; j++){
                    int x = list.get(j)[0];
                    int y = list.get(j)[1];


                    if(x == pos[0] && y == pos[1]){
                        settings.board[tar[0]][tar[1]] = numTar ;
                        settings.board[pos[0]][pos[1]] = numPos;
                        return settings.board ;

                    }
                    if(x < settings.board.length -1){
                        if((settings.board[x+1][y] >1 ||settings.board[x+1][y] == numTar )&& settings.board[x+1][y] <targetVal ){
                            targetVal =settings.board[x+1][y];
                            settings.board[x+1][y] = -5;
                            int arr[] = {x+1, y};
                            list.push(arr);
                             try { Thread.sleep(speed); } catch (Exception ex) {}
                            repaint();

                        }
                    }
                    if(y <settings.board[x].length -1){
                        if(  (settings.board[x][y+1] >1 ||settings.board[x][y+1]  == numTar )   && settings.board[x][y+1] < targetVal  ){
                            targetVal = settings.board[x][y+1];
                            settings.board[x][y+1] = -5;
                            int arr[] = {x, y+1};
                            list.push(arr); 
                            try { Thread.sleep(speed); } catch (Exception ex) {}
                            repaint();
                        }
                    }
                    if(x > 0){
                        if(  ( settings.board [x-1][y] > 1 || settings.board[x-1][y] == numTar) && settings.board[x-1][y]< targetVal  ){
                            targetVal = settings.board[x-1][y];
                            settings.board[x-1][y] = -5;
                            int arr[] = {x-1,y};
                            list.push(arr);
                            try { Thread.sleep(speed); } catch (Exception ex) {}
                            repaint();
                        }
                    }

                    if(y > 0){
                        if(  (settings.board[x][y-1] >1|| settings.board[x][y-1] == numTar ) &&  settings.board[x][y-1]< targetVal ){
                            targetVal = settings.board[x][y-1];
                            settings.board[x][y-1] = -5;
                            int arr[] = {x,y-1};
                            list.push(arr);
                            try { Thread.sleep(speed); } catch (Exception ex) {}
                            repaint();

                        }
                    }

                }
            }
            orangeVal = settings.board[tar[0]][tar[1]];
            settings.board[tar[0]][tar[1]] = numTar;
            settings.board[pos[0]][pos[1]] = numPos;
            //running = false;
            return settings.board;
        }catch(Exception e){
            running = false;
            return settings.board;
        }
    }

       public void paint(Graphics g){
        super.paint(g);
        int change = 0;
       
  

       
       
       
        
        for(int row = 0 ; row < settings.board.length ; row++){
            
                    // -4 =POINTS 
                    // -3 = path 
                    // -2 = target 
                    // -1 = barrier 
                    // -0 = empty space 
                    // 1  = player
                    // else all pathways
                    
            for(int col = 0 ; col < settings.board[row].length ; col++){
                if(settings.board[row][col] == 0) change=1;
                if(settings.board[row][col] == -1) change=2;
                if(settings.board[row][col] == -4) change=6;
                if(settings.board[row][col] == -3) change=7;
                if(settings.board[row][col] == -2) change=4;
                if(settings.board[row][col] == 1) change=3;
                if(settings.board[row][col] > 1) change=9;
                if(settings.board[row][col] == -5) change=5;
                int rgb[] = settings.COLORS[change];
                
                
                int red = rgb[0], green = rgb[1] , blue = rgb[2];
                g.setColor(new Color(red,green,blue));
                
                
                g.fillRect( settings.SIZE*col+settings.GAP*col ,
                            settings.SIZE*row+settings.GAP*row,
                            settings.SIZE, settings.SIZE);
                
            }
        }
        
        int rgbx[] = settings.COLORS[8];
        int redx = rgbx[0], greenx = rgbx[1] , bluex = rgbx[2];
                    // -4 =POINTS 
                    // -3 = path 
                    // -2 = target 
                    // -1 = barrier 
                    // -0 = empty space 
                    // 1  = player
                    // else all pathways
        
        
        
       if(pick == 0){
            g.setColor(new Color(redx,greenx,bluex));
            g.fillRect( settings.SIZE*4-5+settings.GAP*4 ,
                            settings.SIZE*(settings.ROWS+1)+settings.GAP*(settings.ROWS+1),
                            settings.SIZE*2+10, settings.SIZE+10);
       }
        
        
        if(pick == -2){
            
        g.setColor(new Color(redx,greenx,bluex));
        g.fillRect( settings.SIZE*7-5+settings.GAP*7 ,
                            settings.SIZE*(settings.ROWS+1)+settings.GAP*(settings.ROWS+1),
                            settings.SIZE*2+10, settings.SIZE+10);
        }
        
        
        
        
        if(pick == -4){
            g.setColor(new Color(redx,greenx,bluex));
            g.fillRect( settings.SIZE*10-5+settings.GAP*10 ,
                            settings.SIZE*(settings.ROWS+1)+settings.GAP*(settings.ROWS+1),
                            settings.SIZE*2+10, settings.SIZE+10);
        
        }
        
        if(pick == 1){
            g.setColor(new Color(redx,greenx,bluex));
            g.fillRect( settings.SIZE*13-5+settings.GAP*13 ,
                                settings.SIZE*(settings.ROWS+1)+settings.GAP*(settings.ROWS+1),
                                settings.SIZE*2+10, settings.SIZE+10);
        }
        
        
        
        
        if(pick == -1){
            g.setColor(new Color(redx,greenx,bluex));
            g.fillRect( settings.SIZE*16-5+settings.GAP*16 ,
                                settings.SIZE*(settings.ROWS+1)+settings.GAP*(settings.ROWS+1),
                                settings.SIZE*2+10, settings.SIZE+10);
        
        }
        
        
         // ===================================================================================================
        
        
        
        int rgb[] = settings.COLORS[9];
        int red = rgb[0], green = rgb[1] , blue = rgb[2];
        g.setColor(new Color(red,green,blue));
        g.fillRect( settings.SIZE*1+settings.GAP*1 ,
                            settings.SIZE*(settings.ROWS+1)+settings.GAP*(settings.ROWS+1),
                            settings.SIZE*2, settings.SIZE);
        int rgb1[] = settings.COLORS[8];
        int red1 = rgb1[0], green1 = rgb1[1] , blue1 = rgb1[2];
        g.setColor(new Color(red1,green1,blue1));
        g.fillRect( settings.SIZE*4+settings.GAP*4 ,
                            settings.SIZE*(settings.ROWS+1)+settings.GAP*(settings.ROWS+1),
                            settings.SIZE*2, settings.SIZE);
        
        int rgb2[] = settings.COLORS[4];
        int red2 = rgb2[0], green2 = rgb2[1] , blue2 = rgb2[2];
        g.setColor(new Color(red2,green2,blue2));
        g.fillRect( settings.SIZE*7+settings.GAP*7 ,
                            settings.SIZE*(settings.ROWS+1)+settings.GAP*(settings.ROWS+1),
                            settings.SIZE*2, settings.SIZE);
        
        int rgb3[] = settings.COLORS[6];
        int red3 = rgb3[0], green3 = rgb3[1] , blue3 = rgb3[2];
        g.setColor(new Color(red3,green3,blue3));
        g.fillRect( settings.SIZE*10+settings.GAP*10 ,
                            settings.SIZE*(settings.ROWS+1)+settings.GAP*(settings.ROWS+1),
                            settings.SIZE*2, settings.SIZE);
        int rgb4[] = settings.COLORS[3];
        int red4 = rgb4[0], green4 = rgb4[1] , blue4 = rgb4[2];
        g.setColor(new Color(red4,green4,blue4));
        g.fillRect( settings.SIZE*13+settings.GAP*13 ,
                            settings.SIZE*(settings.ROWS+1)+settings.GAP*(settings.ROWS+1),
                            settings.SIZE*2, settings.SIZE);
         
        int rgb5[] = settings.COLORS[2];
        int red5 = rgb5[0], green5 = rgb5[1] , blue5 = rgb5[2];
        g.setColor(new Color(red5,green5,blue5));
        g.fillRect( settings.SIZE*16+settings.GAP*16 ,
                            settings.SIZE*(settings.ROWS+1)+settings.GAP*(settings.ROWS+1),
                            settings.SIZE*2, settings.SIZE);
        
        
        
        // Mouse 
        
        
                if(pick == 0) change=8;
                if(pick  == -1) change=2;
                if(pick  == -4) change=6;
                if(pick == -3) change=7;
                if(pick == -2) change=4;
                if(pick  == 1) change=3;
                if(pick  > 1) change=9;
                int rgbMouse[] = settings.COLORS[change];
                
        g.setColor(new Color(rgbMouse[0] ,rgbMouse[1] ,rgbMouse[2] ));
        g.fillRect( objectX , objectY, settings.SIZE, settings.SIZE);
        
    }
       
        public  boolean isTherePosition(){
        for(int i = 0 ; i < settings.board.length ; i++)
            for(int j = 0 ; j < settings.board[i].length ; j++)
                if( settings.board[i][j] == 1){
                    int arr[] = {i,j};
                    position = arr;
                    return true;
                }
        return false;
    }
    
    public  boolean isThereTarget(){
        for(int i = 0 ; i < settings.board.length ; i++)
            for(int j = 0 ; j < settings.board[i].length ; j++)
                if( settings.board[i][j] == -2){
                   int arr[] = {i,j};
                    target = arr;
                    return true;
                }
        return false;
    }
    public  boolean isTherePoints(){
        for(int i = 0 ; i < settings.board.length ; i++)
            for(int j = 0 ; j < settings.board[i].length ; j++)
                if( settings.board[i][j] == -4){
                    int arr[] = {i,j};
                    points = arr;
                    return true;
                }
        return false;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton7 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jSlider1 = new javax.swing.JSlider();
        jLabel1 = new javax.swing.JLabel();

        setLayout(null);

        jButton7.setBorderPainted(false);
        jButton7.setContentAreaFilled(false);
        jButton7.setFocusPainted(false);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        add(jButton7);
        jButton7.setBounds(10, 710, 190, 40);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/t2.png"))); // NOI18N
        jLabel3.setText("jLabel2");
        add(jLabel3);
        jLabel3.setBounds(0, 700, 1360, 60);

        jPanel1.setLayout(null);

        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setFocusPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(20, 20, 160, 30);

        jSlider1.setMinimum(5);
        jSlider1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jSlider1MouseDragged(evt);
            }
        });
        jPanel1.add(jSlider1);
        jSlider1.setBounds(190, 30, 320, 10);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/close.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(0, -20, 540, 110);

        add(jPanel1);
        jPanel1.setBounds(810, 630, 530, 70);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        if(running == false){
            removeAll();
            removeAll();
            updateUI();
            timer.stop();
            
            settings.board =  new int[ settings.ROWS][settings.COLUMNS];
            position = null;
            target = null;
            points = null;
            running = false;
            
            
            MainPanel panel = new MainPanel();
            panel.setVisible(true);

            try {
                panel.main(new String[]{});
            } catch (Exception ex) {
                Logger.getLogger(PathFinderInterface.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(running == false){
            animation();
        }
        running = true;
        
    }//GEN-LAST:event_jButton1ActionPerformed
    public void animation(){
        
        Thread t = new Thread();
        t.start();
        for(int i = 0 ; i < settings.board.length ; i ++)
           for(int j = 0 ; j < settings.board[i].length ; j++)
               if(settings.board[i][j] > 1 || settings.board[i][j] == -3 || settings.board[i][j] == -5 )
                   settings.board[i][j] = 0;    
         
         settings.board = process(position,target,points); 
        
         
         
    }
    private void jSlider1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSlider1MouseDragged
        speed = jSlider1.getValue();
    }//GEN-LAST:event_jSlider1MouseDragged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSlider jSlider1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    
    repaint();
        if(isTherePoints() == false)
            points = null;
        if(isThereTarget() == false)
            target = null;
        if(isTherePosition() == false)
            position = null;
         
         
        //System.out.println(running);
       //settings.board = PathFinderAlgo.process(settings.board,position,target,points); 
       if (running == false){
            jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/close.png")));
            settings.board = PathFinderAlgo.process(settings.board,position,target,points); 

                        // -4 =POINTS 
                        // -3 = path 
                        // -2 = target 
                        // -1 = barrier 
                        // -0 = empty space 
                        // 1  = player
                        // else all pathways


                addMouseListener(new MouseAdapter(){
                 public void mouseClicked(MouseEvent e) {
                        // -4 =POINTS 
                        // -3 = path 
                        // -2 = target 
                        // -1 = barrier 
                        // -0 = empty space 
                        // 1  = player
                        // else all pathways
                      int y = (int)((e.getY()) /25);
                      int x = (int)(e.getX() /25 );

                      // Reset
                      if(( (x == 2 || x == 1) && y == 26  ) && running == false){
                         settings.board =  new int[ settings.ROWS][settings.COLUMNS];

                         position = null;
                         target = null;
                         points = null;
                         //                     pick = -1;
                      }
                      //Erase 
                      if((x == 4 || x == 5) && y == 26){

                          pick = 0;
                      }
                      // Target 
                      if((x == 7 || x == 8) && y == 26){

                          pick = -2;
                      }
                      // point
                        if((x == 10 || x == 11) && y == 26){
                            pick = -4;
                        }
                        // PLayer 
                        if((x == 13 || x == 14) && y == 26){
                            pick = 1;

                        }
                     // Boader   
                     if((x == 16 || x == 17) && y == 26){
                            pick = -1;
                        }

                        if(pick == 1 && running == false){
                        for(int i = 0 ; i < settings.board.length ; i++){
                            for(int j = 0 ; j < settings.board[i].length ; j++){
                                if(settings.board[i][j] == 1 &&(y>=0 && y< settings.ROWS && x>=0 && x< settings.COLUMNS)){
                                    settings.board[i][j] = previous;
                                }
                            }
                        }
                        if(y>=0 && y< settings.ROWS && x>=0 && x< settings.COLUMNS){
                            int arr[] ={(int)((e.getY()) /25),(int)(e.getX() /25 ) } ; 
                            position = arr;
                            previous = settings.board[(int)((e.getY()) /25)][ (int)(e.getX() /25 )  ];
                            settings.board[(int)((e.getY()) /25)][ (int)(e.getX() /25 )  ] = 1; 

                         }
                    }

                    if(pick == -4 && running == false){
                        for(int i = 0 ; i < settings.board.length ; i++){
                            for(int j = 0 ; j < settings.board[i].length ; j++){
                                if(settings.board[i][j] == -4 &&(y>=0 && y< settings.ROWS && x>=0 && x< settings.COLUMNS)){
                                    settings.board[i][j] = previous;
                                }
                            }
                        }
                        if(y>=0 && y< settings.ROWS && x>=0 && x< settings.COLUMNS){
                            int arr[] ={(int)((e.getY()) /25),(int)(e.getX() /25 ) } ; 
                            points = arr;
                            previous = settings.board[(int)((e.getY()) /25)][ (int)(e.getX() /25 )  ];
                            settings.board[(int)((e.getY()) /25)][ (int)(e.getX() /25 )  ] = -4; 
                         }
                    }


                    if(pick == -2 && running == false){
                        for(int i = 0 ; i < settings.board.length ; i++){
                            for(int j = 0 ; j < settings.board[i].length ; j++){
                                if(settings.board[i][j] == -2 &&  (y>=0 && y< settings.ROWS && x>=0 && x< settings.COLUMNS)){
                                    settings.board[i][j] = previous;
                                }
                            }
                        }
                     if(y>=0 && y< settings.ROWS && x>=0 && x< settings.COLUMNS ){
                         int arr[] ={(int)((e.getY()) /25),(int)(e.getX() /25 ) } ; 
                            target = arr;
                        previous = settings.board[(int)((e.getY()) /25)][ (int)(e.getX() /25 )  ];
                        settings.board[(int)((e.getY()) /25)][ (int)(e.getX() /25 )  ] = -2; 


                        }
                    }

                     if(pick == 0 || pick == -1 && running == false){
                        if(y>=0 && y< settings.ROWS && x>=0 && x< settings.COLUMNS){
                            int removes =   settings.board[(int)((e.getY()) /25)][ (int)(e.getX() /25 )  ]  ;
                            settings.board[(int)((e.getY()) /25)][ (int)(e.getX() /25 )  ] = pick;  
                            if(removes == -4)
                                points = null;
                            if(removes == 1)
                                position = null;
                            if(removes == -2)
                                target = null;

                    }}


                  }

             });
            addMouseMotionListener(new MouseAdapter() {
                @Override
                public void  mouseMoved(MouseEvent e){
                    objectY= ((e.getY()));
                    objectX = (e.getX());

                }
                @Override
                public void  mouseDragged(MouseEvent e) {
                    int y = (int)((e.getY()) /25);
                    int x = (int)(e.getX() /25 );
                    objectY= ((e.getY()));
                    objectX = (e.getX());
                   if(pick == 0 || pick == -1 && running == false){
                        if(y>=0 && y< settings.ROWS && x>=0 && x< settings.COLUMNS && running == false){
                            int removes =   settings.board[(int)((e.getY()) /25)][ (int)(e.getX() /25 )  ]  ;
                            settings.board[(int)((e.getY()) /25)][ (int)(e.getX() /25 )  ] = pick;  
                            if(removes == -4)
                                points = null;
                            if(removes == 1)
                                position = null;
                            if(removes == -2)
                                target = null;

                    }}
                    if(pick == -4 && running == false){
                        for(int i = 0 ; i < settings.board.length ; i++){
                            for(int j = 0 ; j < settings.board[i].length ; j++){
                                if(settings.board[i][j] == -4 &&(y>=0 && y< settings.ROWS && x>=0 && x< settings.COLUMNS)){
                                    settings.board[i][j] = previous;
                                }
                            }
                        }
                        if(y>=0 && y< settings.ROWS && x>=0 && x< settings.COLUMNS){
                             int arr[] = {(int)((e.getY()) /25) ,(int)(e.getX() /25 ) };
                            points = arr;
                            previous = settings.board[(int)((e.getY()) /25)][ (int)(e.getX() /25 )  ];
                            settings.board[(int)((e.getY()) /25)][ (int)(e.getX() /25 )  ] = -4; 
                         }
                    }
                    if(pick == 1 && running == false){
                        for(int i = 0 ; i < settings.board.length ; i++){
                            for(int j = 0 ; j < settings.board[i].length ; j++){
                                if(settings.board[i][j] == 1 &&(y>=0 && y< settings.ROWS && x>=0 && x< settings.COLUMNS)){
                                    settings.board[i][j] = previous;
                                }
                            }
                        }
                        if(y>=0 && y< settings.ROWS && x>=0 && x< settings.COLUMNS){
                             int arr[] = {(int)((e.getY()) /25) ,(int)(e.getX() /25 ) };
                            position = arr;
                            previous = settings.board[(int)((e.getY()) /25)][ (int)(e.getX() /25 )  ];
                            settings.board[(int)((e.getY()) /25)][ (int)(e.getX() /25 )  ] = 1; 
                         }
                    }
                    if(pick == -2 && running == false){
                        for(int i = 0 ; i < settings.board.length ; i++){
                            for(int j = 0 ; j < settings.board[i].length ; j++){
                                if(settings.board[i][j] == -2 &&  (y>=0 && y< settings.ROWS && x>=0 && x< settings.COLUMNS)){
                                    settings.board[i][j] = previous;
                                }
                            }
                        }
                     if(y>=0 && y< settings.ROWS && x>=0 && x< settings.COLUMNS){
                        int arr[] = {(int)((e.getY()) /25) ,(int)(e.getX() /25 ) };
                        target = arr;
                        previous = settings.board[(int)((e.getY()) /25)][ (int)(e.getX() /25 )  ];
                        settings.board[(int)((e.getY()) /25)][ (int)(e.getX() /25 )  ] = -2; 

                        }
                    }


                }

            });
         }else{
           
           System.out.println("hello -"+count++%10);
            jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/open_1.png"))); // NOI18N
       }
         
    } 
       
}

