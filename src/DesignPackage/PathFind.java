/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DesignPackage;


import SolverPackage.PathFinderAlgo;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.MouseInfo;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;



/**
 *
 * @author zalve
 */
public class PathFind extends JPanel implements KeyListener,ActionListener{
    
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
    
    public PathFind(){
        setLayout(null);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
            
       int[] rgbs = settings.COLORS[0];
       int reds = rgbs[0] ,greens = rgbs[1],blues = rgbs[2];
       setBackground(new Color(reds,greens,blues));  
       
       
       
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
    
    
    
    //@Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        if(isTherePoints() == false)
            points = null;
        if(isThereTarget() == false)
            target = null;
        if(isTherePosition() == false)
            position = null;
     
        settings.board = PathFinderAlgo.process(settings.board,position,target,points); 
        
        
        
        
        
        
        
        
        
        
        
        
       
       //settings.board = PathFinderAlgo.process(settings.board); 
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
                  if((x == 2 || x == 1) && y == 26){
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
                
                 
                 
                 
                 
                    if(pick == 1){
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
                    
               
                
                
                
                if(pick == -4){
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
                
                
                
                
                
                
                if(pick == -2){
                    for(int i = 0 ; i < settings.board.length ; i++){
                        for(int j = 0 ; j < settings.board[i].length ; j++){
                            if(settings.board[i][j] == -2 &&  (y>=0 && y< settings.ROWS && x>=0 && x< settings.COLUMNS)){
                                settings.board[i][j] = previous;
                            }
                        }
                    }
                 if(y>=0 && y< settings.ROWS && x>=0 && x< settings.COLUMNS){
                     int arr[] ={(int)((e.getY()) /25),(int)(e.getX() /25 ) } ; 
                        target = arr;
                    previous = settings.board[(int)((e.getY()) /25)][ (int)(e.getX() /25 )  ];
                    settings.board[(int)((e.getY()) /25)][ (int)(e.getX() /25 )  ] = -2; 
                    
                    
                    }
                }
                 
                 if(pick == 0 || pick == -1){
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
                
                
               if(pick == 0 || pick == -1){
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
                
                
                if(pick == -4){
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
                 
                 
                 
                
                if(pick == 1){
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
                
                
                
                
                
                if(pick == -2){
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
    } 
    
    
    
}
