
import DesignPackage.Properties;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;


import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author zalve
 */
public class SudokuPanel extends JPanel  implements KeyListener,ActionListener  {

    /**
     * Creates new form SudokuPanel
     */
    private Timer timer;
    private Properties prop = new Properties();
    private int pos[] = {0,0};
    private int board[][] =new int[9][9];
    private int gaps=10;
    private  int xLoc = 0;
    private  int yLoc = 0;

    public SudokuPanel() {
        initComponents();
   
        
                addKeyListener(this);
                setFocusable(true);
                setFocusTraversalKeysEnabled(false);
    
        
        
                addMouseListener(new MouseAdapter(){
                    @Override
                    public void mouseClicked(MouseEvent e) {
                     int x =(e.getY()-10);
                       int y = (e.getX()-250);



                       int gapX = x/230;
                       int gapY = y/230;


                       if(x>=0){
                       x  =(e.getY()-10-gaps*gapX)/(72);
                       y =(e.getX()-250-gaps*gapY)/(72);
                           if(x>=0 && x <=8 && y>=0 && y <=8 )
                            if(board[x][y] != -4 && board[x][y] != -6)
                               pos = new int[]{x,y};
                        }
                    }
               
        });
        
           
          addMouseMotionListener(new MouseAdapter() {
              @Override
                public void  mouseDragged(MouseEvent e) {
                     int x =(e.getY()-10);
                    int y = (e.getX()-250);
                     
                     
                   
                    int gapX = x/230;
                    int gapY = y/230;
                     
                     
                    if(x>=0){
                    x  =(e.getY()-10-gaps*gapX)/(72);
                    y =(e.getX()-250-gaps*gapY)/(72);
                        if(x>=0 && x <=8 && y>=0 && y <=8 )
                         if(board[x][y] != -4 && board[x][y] != -6)
                            pos = new int[]{x,y};
                     }
                }
          });
        
              timer = new Timer((int)prop.DELAY,this);
            timer.start();
        
    }

    
    
    public int[][] decorConv(char board[][],int x,int y){
        /*
            >free = 0 
            >selected1 = -1
            >selected2 = -2
            >track = -3
            >uneditable = -4
            error = -5
        */
        
        int result[][] = new int[9][9];
        int change = 0 ;
        
    
            for(int i = 0 ; i < 9 ; i ++)
                for(int j = 0 ; j < 9 ; j ++)
                    if(board[i][j] == '.')
                        result[i][j]  = 0;
            
            for(int i = 0 ; i < 9 ; i ++)
            for(int j = 0 ; j < 9 ; j ++)
                if(board[i][j] >= '0'  && board[i][j]<='9')
                      result[i][j]  = -4;
                  
            
        
            
        for(int j = 0 ; j < 9 ; j ++)
            if(result[j][y] == -4)
                result[j][y] = -6;
            else 
                result[j][y] = -1; 
        
        for(int j = 0 ; j < 9 ; j ++)
             if(result[x][j] == -4)
                result[x][j] = -6;
            else
                 result[x][j] = -1;
               
        
        
        int rowY= x/3;
        int colX = y/3;
        for(int i =  rowY*3 ; i < rowY*3+3 ;i++)
            for(int j =  colX*3 ; j < colX*3+3 ;j++)
                if(result[i][j] == -4)
                    result[i][j] = -6;
                else if( result[i][j] == 0)
                    result[i][j] = -1;
        
        
        
        
        
        result[x][y] = -3;
        
        return result;
    }
    
   
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(21, 32, 43));
        setLayout(null);

        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setFocusPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1);
        jButton1.setBounds(400, 710, 190, 40);

        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setFocusPainted(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        add(jButton2);
        jButton2.setBounds(210, 710, 190, 40);

        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.setFocusPainted(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        add(jButton3);
        jButton3.setBounds(0, 710, 210, 40);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/t5.png"))); // NOI18N
        jLabel2.setText("jLabel2");
        add(jLabel2);
        jLabel2.setBounds(0, 710, 1360, 40);
    }// </editor-fold>//GEN-END:initComponents
    @Override
    public void paint(Graphics g){
        super.paint(g);
     
        
        //sudokuBoard
        
        
        /*
            >free = 0 
            >selected1 = -1
            >selected2 = -2
            >track = -3
            >uneditable = -4
            error = -5
        */
        
        board =decorConv(prop.sudokuBoard,pos[0],pos[1]) ;
    
        int gap = 3;
        for(int split = 0 ; split < 3; split++){
            
            for(int row = 0 ; row < 3 ; row++){
                for(int col = split*3 ; col < split*3+3 ; col++){
                    
                    int change = 0;
                    if( board[row][col] >= 0 ) change = 1;
                    if( board[row][col] == -1 ) change = 9;
                    if( board[row][col] == -2 ) change = 9;
                    if( board[row][col] == -3 ) change = 2;
                    if( board[row][col] == -4 ) change = 10;
                    if( board[row][col] == -5 ) change = 4; 
                    if( board[row][col] == -6 ) change = 12; 
                    int rgb[] = prop.COLORS[change];
                    
                    // Box
                    g.setColor(new Color(rgb[0],rgb[1],rgb[2])); 
                    g.fillRect(70*col+gap*col+270+(10*split)      ,   70*row+2 *row+25   , 70,70);
                    
                    
                    // Text
                    change =2;
                    if(board[row][col] ==0 || board[row][col] ==-3) change = 8;
                    else change = 2;
                    rgb = prop.COLORS[change];
                    g.setColor(new Color(rgb[0],rgb[1],rgb[2])); 
                    g.setFont(new Font("TimesRoman", Font.PLAIN, prop.fontSize)); 
                    char x= prop.sudokuBoard[row][col];
                    g.drawString(x >='1' && x<= '9'?prop.sudokuBoard[row][col]+"":" "+"", 70*col+gap*col+270+(gaps*split)+ prop.fontSize/2      ,   70*row+2 *row+25+ prop.fontSize );
                    
                   

                    
                    
                    
                }
            }
            for(int row =3 ; row < 6 ; row++){
                for(int col = split*3; col < split*3+3 ; col++){
                     int change = 0;
                    if( board[row][col] >= 0 ) change = 1;
                    if( board[row][col] == -1 ) change = 9;
                    if( board[row][col] == -2 ) change = 9;
                    if( board[row][col] == -3 ) change = 2;
                    if( board[row][col] == -4 ) change = 10;
                    if( board[row][col] == -5 ) change = 4; 
                    if( board[row][col] == -6 ) change = 12; 
                    int rgb[] = prop.COLORS[change];
                    
                    
                    g.setColor(new Color(rgb[0],rgb[1],rgb[2])); 
                    g.fillRect(70*col+gap*col+270+(10*split)       ,   70*row+2 *row+35  , 70,70);
                    
                    change =2;
                    if(board[row][col] ==0 || board[row][col] ==-3 ) change = 8;
                    else change = 2;
                    rgb = prop.COLORS[change];
                    g.setColor(new Color(rgb[0],rgb[1],rgb[2])); 
                    g.setFont(new Font("TimesRoman", Font.PLAIN, prop.fontSize)); 
                    
                    char x= prop.sudokuBoard[row][col];
                    g.drawString(x >='1' && x<= '9'?prop.sudokuBoard[row][col]+"":" "+"", 70*col+gap*col+270+(gaps*split) + prop.fontSize/2      ,   70*row+2 *row+35 + prop.fontSize );
                    
                }
            }
            for(int row =6 ; row < 9 ; row++){
                for(int col = split*3 ; col < split*3+3  ; col++){
                     int change = 0;
                    if( board[row][col] >= 0 ) change = 1;
                    if( board[row][col] == -1 ) change = 9;
                    if( board[row][col] == -2 ) change =9;
                    if( board[row][col] == -3 ) change = 2;
                    if( board[row][col] == -4 ) change = 10;
                    if( board[row][col] == -5 ) change = 4; 
                    if( board[row][col] == -6 ) change = 12; 
                    int rgb[] = prop.COLORS[change];
                    g.setColor(new Color(rgb[0],rgb[1],rgb[2])); 
                     g.setFont(new Font("TimesRoman", Font.PLAIN, prop.fontSize)); 
                    g.fillRect(70*col+gap*col+270+(10*split)       ,   70*row+2 *row+45  , 70,70);
                    
                    change =2;
                    
                    if(board[row][col] ==0 || board[row][col] ==-3) change = 8;
         
                    else change = 2;
                    rgb = prop.COLORS[change];
                    g.setColor(new Color(rgb[0],rgb[1],rgb[2]));   
                    
                    
                    char x= prop.sudokuBoard[row][col];
                    g.drawString(x >='1' && x<= '9'?prop.sudokuBoard[row][col]+"":" "+"",70*col+gap*col+270+(gaps*split) + prop.fontSize/2     ,   70*row+2 *row+45 + prop.fontSize );
                    
                }
            }
        }
        g.dispose();
        
        
        
        
        
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
                  timer.stop();
                
                FloodFillAlgorithm panel = new FloodFillAlgorithm();
                MainPanel main = new MainPanel();
                for(int i = 0 ; i < prop.paintWidth ;i++)
                for(int j = 0 ; j <prop.paintHeight ; j++)
                    prop.painBoard[i][j] = 1;
            // panel.setVisible(true);

                prop.board =  new int[ prop.ROWS][prop.COLUMNS];

               main. frame.getContentPane().removeAll();

                // refresh the panel.
                 main.frame.add(panel);
                 main.frame.setVisible(true);
                 ImageIcon img = new ImageIcon("src\\DSicon.png");
                 main.frame.setIconImage(img.getImage());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
//            removeAll();
//            removeAll();
//            updateUI();
          timer.stop();
            
            prop.board =  new int[ prop.ROWS][prop.COLUMNS];
            
            
            MainPanel panel = new MainPanel();
            panel.setVisible(true);

            try {
                panel.main(new String[]{});
            } catch (Exception ex) {
                Logger.getLogger(PathFinderInterface.class.getName()).log(Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        PathFinderInterface panel = new PathFinderInterface();
        timer.stop();
        MainPanel main = new MainPanel();
        for(int i = 0 ; i < prop.paintWidth ;i++)
        for(int j = 0 ; j <prop.paintHeight ; j++)
        prop.painBoard[i][j] = 1;
        // panel.setVisible(true);

        prop.board =  new int[ prop.ROWS][prop.COLUMNS];

        main. frame.getContentPane().removeAll();

        // refresh the panel.
        main.frame.add(panel);
        main.frame.setVisible(true);
        ImageIcon img = new ImageIcon("src\\DSicon.png");
        main.frame.setIconImage(img.getImage());

    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables

  
    
    @Override
    public void keyTyped(KeyEvent e) {
       char key = e.getKeyChar();
        if(key >='0' && key <= '9')  prop.sudokuBoard[pos[0]][pos[1]] = key;
        else   prop.sudokuBoard[pos[0]][pos[1]] ='.';

    }
    @Override
    public void keyPressed(KeyEvent e) {
       
    }
    
    
    @Override
    public void keyReleased(KeyEvent e) {
         
//       //int key = e.getKeyChar();
        
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        
        timer.start();
        repaint();
    
        
    }
//    public static void prints(int board[][]){
//        int count = 0;
//        for(int i = 0 ; i < board.length ; i ++){
//            for(int j = 0 ; j < board[i].length ; j++){
//                if(board[i][j] != 0) {
//                    System.out.print(" "+board[i][j]+" ");
//                }else{ 
//                    System.out.print(" . ");
//                    count++;
//                }
//                
//                if( (j+1 )%3 == 0) 
//                    System.out.print(" | ");
//            }
//            System.out.println();
//            if(( i+1 )%3 == 0) System.out.println("------------------------------------"); }
//        System.out.println("============================="+count);
//        System.out.println("=============================");
//    }
//    public  void prints(char board[][]){
//        for(int i = 0 ; i < board.length ; i ++){
//            for(int j = 0 ; j < board[i].length ; j++){
//                if(board[i][j] != '.')   System.out.print(" "+board[i][j]+" ");
//                else  System.out.print(" . ");
//                
//                if( (j+1 )%3 == 0) 
//                    System.out.print(" | ");
//            }
//            System.out.println();
//            if(( i+1 )%3 == 0) System.out.println("------------------------------------"); }
//        System.out.println("=============================");
//        System.out.println("=============================");
//    }
}


