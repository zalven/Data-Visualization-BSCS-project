
import DesignPackage.Properties;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
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
public class SudokuPanel extends JPanel  implements KeyListener,ActionListener{

    /**
     * Creates new form SudokuPanel
     */
    private Timer timer;
    private Properties prop = new Properties();
    private int pos[] = {1,1};
    private int board[][] =new int[9][9];
    private int gaps=10;
    private  int xLoc = 0;
    private  int yLoc = 0;
    private char[][] clone;
    private char[][] orig,reset;
    private int speed = 1;
    private boolean isRunning = false;
    public SudokuPanel() {
   
                initComponents();
       
                addKeyListener(this);
                setFocusable(true);
                
                setFocusTraversalKeysEnabled(false);
                requestFocusInWindow();
                mouseEvents();
                timer = new Timer(prop.DELAY,this);
                timer.start();
                clone = cloneArr(  prop.sudokuBoard );
                orig = cloneArr(  prop.sudokuBoard );
                reset = cloneArr(  prop.sudokuBoard );
               
                 
    }
    public char[][] cloneArr(char arr[][]){
        char result[][] = new char[9][9];
        for(int i = 0 ; i <  9 ; i ++)
            for(int j = 0 ; j  < 9 ; j++)
                result[i][j] = arr[i][j];
        return result;
    }
    
    public void  mouseEvents(){   
                addMouseListener(new MouseAdapter(){
                    @Override
                    public void mouseClicked(MouseEvent e) {
                     int x =(e.getY()-10);
                       int y = (e.getX()-250);



                       int gapX = x/230;
                       int gapY = y/230;


                       if(x>=0){
                       x  =(e.getY()-10-(gaps+5)*gapX)/(72);
                       y =(e.getX()-250-(gaps+5)*gapY)/(72);
                           if(x>=0 && x <=8 && y>=0 && y <=8)
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
                    x  =(e.getY()-10-(gaps+5)*gapX)/(72);
                    y =(e.getX()-250-(gaps+5)*gapY)/(72);
                        if(x>=0 && x <=8 && y>=0 && y <=8 )
                         if(board[x][y] != -4 && board[x][y] != -6)
                            pos = new int[]{x,y};
                    }
                }
          });
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
                    if(board[i][j] == '.' || board[i][j] == '!' )
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
        
       
        for(int i = 0 ; i < 9 ;i ++){
            for(int j = 0 ; j <  9 ; j ++){
                if(!isPossible (i,j,  prop.sudokuBoard[i][j], prop.sudokuBoard) &&  board[i][j] == '!'){
                     result[i][j] = -5;
                }
            }
        }
         result[x][y] = -3;
        
        
        return result;
    }
    
     public boolean isPossible(int row,int col , char value , char[][] board){
        
        // Check the board horizontaly 
        for(int index = 0 ; index  < 9 ; index++)
            if(board[row][index] == value && col != index)
                return false;
        
        // Check the board Vertically 
        for(int index = 0 ; index  < 9 ; index++)
            if(board[index][col] == value && row != index)
                return false;
        
        // Check the board 3x3 
        int colX = col/3;
        int rowY = row/3;
        for(int i =  rowY*3 ; i < rowY*3+3 ;i++)
            for(int j =  colX*3 ; j < colX*3+3 ;j++)
                if(board[i][j] == value && i!= row && j != col )
                    return false;
      
        return true;
    }
       public int[] slots(char board[][]){
        for(int row  = 0 ; row < 9 ; row++)
            for(int col = 0 ; col < 9 ; col++)
                if(board[row][col] == '.')
                    return new int[]{row,col};
        return null;
    }
    
   
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(21, 32, 43));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                formKeyTyped(evt);
            }
        });
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

        jPanel1.setLayout(null);

        jButton5.setBorderPainted(false);
        jButton5.setContentAreaFilled(false);
        jButton5.setFocusPainted(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5);
        jButton5.setBounds(90, 210, 210, 50);

        jButton4.setBorderPainted(false);
        jButton4.setContentAreaFilled(false);
        jButton4.setFocusPainted(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4);
        jButton4.setBounds(90, 150, 210, 50);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rightsudoku.png"))); // NOI18N
        jLabel3.setText("jLabel3");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(0, 0, 400, 670);

        add(jPanel1);
        jPanel1.setBounds(960, 20, 390, 670);

        jPanel2.setLayout(null);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/LeftSudoku.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        jPanel2.add(jLabel1);
        jLabel1.setBounds(0, -20, 250, 700);

        add(jPanel2);
        jPanel2.setBounds(10, 30, 240, 650);
    }// </editor-fold>//GEN-END:initComponents
   
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
        
        if(isRunning == false){
        
            board =decorConv(clone,pos[0],pos[1]) ;
    
        }
        int gap = 3;
        for(int split = 0 ; split < 3; split++){
            
            for(int row = 0 ; row < 3 ; row++){
                for(int col = split*3 ; col < split*3+3 ; col++){
                    
                    int change = 0;
                    if( board[row][col] >= 0 ) change = 1;
                    if( board[row][col] == -1 ) change = 10;
                    if( board[row][col] == -2 ) change = 10;
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
                    if(board[row][col] ==0 || board[row][col] ==-3) 
                        change = 8;
                    else if(  board[row][col] == -6)
                         change = 3;
                    else   
                        change = 8;
                    
                    
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
                    if( board[row][col] == -1 ) change = 10;
                    if( board[row][col] == -2 ) change = 10;
                    if( board[row][col] == -3 ) change = 2;
                    if( board[row][col] == -4 ) change = 10;
                    if( board[row][col] == -5 ) change = 4; 
                    if( board[row][col] == -6 ) change = 12; 
                    int rgb[] = prop.COLORS[change];
                    
                    
                    g.setColor(new Color(rgb[0],rgb[1],rgb[2])); 
                    g.fillRect(70*col+gap*col+270+(10*split)       ,   70*row+2 *row+35  , 70,70);
                    
                   change =2;
                    if(board[row][col] ==0 || board[row][col] ==-3) 
                        change = 8;
                    else if(  board[row][col] == -6)
                         change = 3;
                    else   
                        change = 8;
                    
                    
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
                    if( board[row][col] == -1 ) change = 10;
                    if( board[row][col] == -2 ) change =10;
                    if( board[row][col] == -3 ) change = 2;
                    if( board[row][col] == -4 ) change = 10;
                    if( board[row][col] == -5 ) change = 4; 
                    if( board[row][col] == -6 ) change = 12; 
                    int rgb[] = prop.COLORS[change];
                    g.setColor(new Color(rgb[0],rgb[1],rgb[2])); 
                     g.setFont(new Font("TimesRoman", Font.PLAIN, prop.fontSize)); 
                    g.fillRect(70*col+gap*col+270+(10*split)       ,   70*row+2 *row+45  , 70,70);
                    
                    change =2;
                    
                    change =2;
                    if(board[row][col] ==0 || board[row][col] ==-3) 
                        change = 8;
                    else if(  board[row][col] == -6)
                         change = 3;
                    else   
                        change = 8;
                    
                    
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
                if(isRunning == false){
                    removeAll();
                    removeAll();
                    updateUI();
                    timer.stop();
                    prop.sudokuBoard = cloneArr(reset);
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
                
                }
                 
                 
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
           if(isRunning == false){
           
                removeAll();
                removeAll();
                updateUI();
                removeAll();
                removeAll();
                updateUI();
                timer.stop();

                prop.board =  new int[ prop.ROWS][prop.COLUMNS];
                prop.sudokuBoard = reset;

                MainPanel panel = new MainPanel();
                panel.setVisible(true);

                try {
                    panel.main(new String[]{});
                } catch (Exception ex) {
                    Logger.getLogger(PathFinderInterface.class.getName()).log(Level.SEVERE, null, ex);
                }
           
           }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        if(isRunning == false){
        
            PathFinderInterface panel = new PathFinderInterface();
            timer.stop();
            prop.sudokuBoard = reset;
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
            
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void formKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyTyped

    }//GEN-LAST:event_formKeyTyped

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        if(isRunning == false){
            prop.sudokuBoard = cloneArr(orig);
            clone= cloneArr( prop.sudokuBoard );

            requestFocusInWindow();
        
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        
        if( isRunning == false){Thread t = new Thread(new Runnable(){
                @Override
                     public void run() {
                            prop.sudokuBoard = cloneArr(orig);
                             clone= cloneArr( prop.sudokuBoard );
                            repaint();
                           _backTrack(prop.sudokuBoard);
                           repaint();
                           prints(prop.sudokuBoard);
                           isRunning =false;

                     }
            });

             t.start();
        }
        isRunning = true;
    }//GEN-LAST:event_jButton5ActionPerformed

    
    
    public boolean _backTrack(char[][] boards){
        
        Thread t = new Thread();
        t.run();
        
        
        repaint();
        try { Thread.sleep(speed); } catch (Exception ex) {}
        int slot[] = slots(boards);
        
        if(slot == null)
            return true;
        
        int row = slot[0],col = slot[1];
        repaint();
        for(int val = '1' ; val <= '9' ; val++ ){
            clone= cloneArr( prop.sudokuBoard );
            
            board =decorConv(clone,row,col) ;
            clone[row][col] = '!';
            repaint();
            repaint();
            if( isPossible(row,col,(char) val , boards) ){
                
                boards[row][col] = (char) val;
                repaint();
                
                
                if(_backTrack(boards) == true){
                    
                    clone= cloneArr( prop.sudokuBoard );
                    board =decorConv(clone,row,col) ;
                    repaint();
                    clone[row][col] = '!';
                    
                    try { Thread.sleep(speed); } catch (Exception ex) {}
                    return true;
                }
                
                boards[row][col] = '.';
                try { Thread.sleep(speed); } catch (Exception ex) {}
                 repaint();
            }
            
        }
         repaint();
        try { Thread.sleep(speed); } catch (Exception ex) {}
        
        return false;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables

    
    
         // Level Medium
     public static boolean sudokuIsWinner(int[][] grid) {

        // Remove duplicates 
        for(int index = 0 ; index < 9 ; index ++)
            grid[index] = sDuplicates(grid[index]);
        grid = sReversed(grid);


        // Remove duplicates for the reverse 
        for(int index = 0 ; index < 9 ; index ++)
            grid[index] = sDuplicates(grid[index]);
         grid = sReversed(grid);


        // Create a clone and // Get the 3 by 3s 
        int rev[][] = sReversed(grid);
        int threes[][] = sThrees(grid);


        // Remove duplicates of both threes and reversed 
        for(int index = 0 ; index  < 9 ; index ++)
            threes[index ] = sDuplicates(threes[index ]) ;

         // Get the sum of both reverse and current grid
        for(int index = 0 ; index < 9  ; index++)
            // if the sum of them is not 45 return false [no duplicates]
            if( sSum(rev[index]) != 45 ||   sSum(grid[index]) != 45  ||    sSum(threes[index]) != 45)
                return false ;

        // Not all satisfies 
        return true;
    }
     
    public static int[][] sThrees( int[][] grid){
        int threes[][] = new int[9][9];
        int count = 0 ;
        for(int row = 0 ; row < 9 ; row+= 3)
            for(int col = 0 ; col < 9 ; col+=3)
                threes[count++] = new int[]{grid[row][col],grid[row][col+1],grid[row][col+2],grid[row+1][col],grid[row+1][col+1],grid[row+1][col+2],grid[row+2][col],grid[row+2][col+1],grid[row+2][col+2]};
        return threes; 

    }
     public  static int[][] sNumConvert(char[][] board){
        int[][] result = new int[9][9];
        for(int row = 0 ; row <  9 ; row++){
            for(int col = 0 ; col < 9 ; col++){
                result[row][ col] = board[row][col] == '.'? 0 :Character.getNumericValue(board[row][col])  ;
            }
        }
        return result;
    }
     
    public static boolean sHasDuplicate(int board[]){
        for(int index = 0 ; index <  9 ; index ++)
            for(int jdex = index +1  ; jdex < 9 ;jdex++ )
                if( board[index] == board[jdex] &&  board[index] != 0 )
                    return true;
        return false;
    }
    
    public static int[] sDuplicates(int[] grid){
        for(int index = 0 ; index < 9 ; index ++)
            for(int j = index+1 ; j < 9 ; j++)
                if(grid[j] == grid[index])
                    grid[j] = 0 ; 
        return grid;
    }
     public static int sSum(int grid[]){
        int result = 0 ;
        for( int value : grid)result+= value;
        return result;
    }
     
    public static int[][] sReversed(int grid[][]){
        int[][] result = new int[9][9];
        for(int row = 0 ; row < 9 ; row++)
            for(int col = 0 ; col< 9 ; col++)
                result[col][row] = grid[row][col];
        return result;
    }

    public static int[][] clone(int board[][] ){
        int[][] grid = new int[9][9];
        for(int col =  0 ; col < 9 ; col++)
            for(int row  = 0 ; row < 9 ; row++)
                grid[col][row] = board[col][row];
        return grid;
       
    }
    
    
    
    
    
    
    

    
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
       // timer.start();
//       if(sudokuIsWinner(sNumConvert  (clone))){
//           System.out.println("Hey I won you piece of sht");
//       }
//       
       if( sudokuIsWinner(sNumConvert (prop.sudokuBoard))   ){
           System.out.println("Hey I won , It's working");
       }
      
       repaint();
    
    }
    
  
    
    @Override
    public void keyTyped(KeyEvent e) {
        if( isRunning == false){
                timer.start();
                char key = e.getKeyChar();
                if(key >='0' && key <= '9') {

                    prop.sudokuBoard[pos[0]][pos[1]] = key;
                    clone[pos[0]][pos[1]] = '!';
                }else{
                    clone[pos[0]][pos[1]] = '.'; 
                    prop.sudokuBoard[pos[0]][pos[1]] ='.';
                }
        
        }
        repaint();
        

    }
    @Override
    public void keyPressed(KeyEvent e) {
       //new UnsupportedOperationException("Not supported yet.");
       
    }
    
    
    @Override
    public void keyReleased(KeyEvent e) {
        //new UnsupportedOperationException("Not supported yet.");
         
    }

 
    
    public static void prints(int board[][]){
        int count = 0;
        for(int i = 0 ; i < board.length ; i ++){
            for(int j = 0 ; j < board[i].length ; j++){
                if(board[i][j] != 0) {
                    System.out.print(" "+board[i][j]+" ");
                }else{ 
                    System.out.print(" . ");
                    count++;
                }
                
                if( (j+1 )%3 == 0) 
                    System.out.print(" | ");
            }
            System.out.println();
            if(( i+1 )%3 == 0) System.out.println("------------------------------------"); }
        System.out.println("============================="+count);
        System.out.println("=============================");
    }
    public  void prints(char board[][]){
        for(int i = 0 ; i < board.length ; i ++){
            for(int j = 0 ; j < board[i].length ; j++){
                if(board[i][j] != '.')   System.out.print(" "+board[i][j]+" ");
                else  System.out.print(" . ");
                
                if( (j+1 )%3 == 0) 
                    System.out.print(" | ");
            }
            System.out.println();
            if(( i+1 )%3 == 0) System.out.println("------------------------------------"); }
        System.out.println("=============================");
        System.out.println("=============================");
    }
    
    
    
    
    
    
    
    
    
    
}


