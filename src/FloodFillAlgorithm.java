
import DesignPackage.Properties;
import LinkedList.LinkedList;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.Timer;


public class FloodFillAlgorithm extends javax.swing.JPanel implements KeyListener,ActionListener {

    private Properties prop = new Properties();
    private int COLOR = 2 ; 
    private Timer timer;
    private int mouseX = 0 , mouseY =  0;
    private boolean isPencil = true;
    private boolean isRunning = false;
    private Thread thread = new Thread();
   
    public FloodFillAlgorithm() {
        
        initComponents();
        int rgb[] = prop.COLORS[0];
        setBackground(new Color(  rgb[0],rgb[1],rgb[2] ) );
        timer = new Timer((int)prop.DELAY,this);
        timer.start();
        speed = jSlider2.getValue();
        
        for(int i = 0 ; i < prop.paintWidth ;i++)
            for(int j = 0 ; j <prop.paintHeight ; j++)
                prop.painBoard[i][j] = 1;
        
     
        addMouseListener(new MouseAdapter(){
                 @Override
                 public void mouseClicked(MouseEvent e) {
                        if(isPencil == false){
                            if(isRunning == false){
                                mouseX = e.getX();
                                mouseY  = e.getY();
                                int x =e.getX() /6;
                                int y = e.getY() /6;
                                if(x >= 0 && x < prop.painBoard.length && y >=0 && 7 < prop.painBoard[0].length){
                                Thread t= new Thread(new Runnable(){
                                    @Override public void run() {
                                        int x =e.getX() /6;
                                        int y = e.getY() /6;
                                        getValue = new int[]{x,y};
                                        fill();
                                }});
                                 t.start();
                                 isRunning = true; 
                            }
                        } 
                        
                    }
                }
        });
                 
        
        
        
        
        
        
        
        
        
        //====================================== MOUSE MOVEMENT CONTROLLER ================================================
        addMouseMotionListener(new MouseAdapter() {
                @Override
                public void  mouseMoved(MouseEvent e){
                    mouseX = (int)((e.getX()));
                    mouseY  = (int)(e.getY());
                    repaint();
                }
                @Override
                public void  mouseDragged(MouseEvent e) {
                    if(isPencil == true){
                        mouseX = e.getX();
                        mouseY  = e.getY();
                        int x =e.getX() /6;
                        int y = e.getY() /6;
                        int sld = jSlider1.getValue();
                        if(x>=sld-1 && x< prop.paintWidth-sld+1 && y>=sld-1 && y< prop.paintHeight-sld+1 && isRunning == false){
                                    for(int i = -jSlider1.getValue()+1 ; i < jSlider1.getValue(); i ++)
                                        for(int j = -sld+1 ; j < sld ; j++)
                                            prop.painBoard[x+i][y+j] = COLOR;
                        }
                        repaint();
                    }else{
                        if(isRunning == false){
                            mouseX = e.getX();
                            mouseY  = e.getY();
                            int x =e.getX() /6;
                            int y = e.getY() /6;
                            if(x >= 0 && x < prop.painBoard.length && y >=0 && 7 < prop.painBoard[0].length){
                           Thread t= new Thread(new Runnable(){@Override public void run() {
                                    int x =e.getX() /6;
                                    int y = e.getY() /6;
                                    getValue = new int[]{x,y};
                                    fill();
                                }});
                           t.start();
                            isRunning = true;
                        };
                        }
                    }
                }
            });
        //====================================== MOUSE MOVEMENT CONTROLLER ================================================
        
    }
    
    
    
    public void paint(Graphics g){
        
        super.paint(g);
        for(int col = 0 ;  col < prop.painBoard.length ; col++  ){
            for(int row = 0 ; row < prop.painBoard[col].length ; row++ ){
                int rgb[] = prop.COLORS[prop.painBoard[col][row]];
                int red = rgb[0], green = rgb[1] , blue = rgb[2];
                g.setColor(new Color(red,green,blue)); 
                g.fillRect( prop.paintSize*col+ prop.paintGap*col      ,   prop.paintSize*row+prop.paintGap *row   ,  prop.paintSize,prop.paintSize);
            }
        }   
        
        if(COLOR>=0 && COLOR <= 10 ){
           
            if(isPencil == true){
                int rgb[] = prop.COLORS[COLOR];
                int red = rgb[0], green = rgb[1] , blue = rgb[2];
                int sld = jSlider1.getValue()+1;
                g.setColor(new Color(red,green,blue)); 
                g.fillRect( mouseX     ,   mouseY,  5*sld,5*sld);
            }else{
                int rgb[] = prop.COLORS[8];
                int red = rgb[0], green = rgb[1] , blue = rgb[2];
                g.setColor(new Color(red,green,blue)); 
                g.fillRect( mouseX- 5*2     ,   mouseY+ 5*2,  5*4,5*4);
                
                
                
                int rgb2[] = prop.COLORS[9];
                int red2 = rgb2[0], green2 = rgb2[1] , blue2 = rgb2[2];
                g.setColor(new Color(red,green,blue)); 
                g.fillRect( mouseX- 5     ,   mouseY+ 5,  5*2,5*2);
                
                int rgb3[] = prop.COLORS[COLOR];
                int red3 = rgb3[0], green3 = rgb3[1] , blue3 = rgb3[2];
                g.setColor(new Color(red3,green3,blue3)); 
                g.fillRect( mouseX- 5*2     ,   mouseY- 5*2,  5,5*3);
                
                
                
                int rgb1[] = prop.COLORS[COLOR];
                int red1 = rgb1[0], green1 = rgb1[1] , blue1 = rgb1[2];
                g.setColor(new Color(red1,green1,blue1)); 
                g.fillRect( mouseX     ,   mouseY,  5*2,5*2);
            }
             
        }
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jSlider1 = new javax.swing.JSlider();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSlider2 = new javax.swing.JSlider();

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
        jButton1.setBounds(210, 710, 180, 40);

        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setFocusPainted(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        add(jButton2);
        jButton2.setBounds(0, 710, 210, 40);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(null);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 90, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 80, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(80, 30, 90, 80);

        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.setFocusPainted(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3);
        jButton3.setBounds(70, 160, 50, 40);

        jButton4.setBorderPainted(false);
        jButton4.setContentAreaFilled(false);
        jButton4.setFocusPainted(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4);
        jButton4.setBounds(130, 170, 40, 20);

        jButton5.setBorderPainted(false);
        jButton5.setContentAreaFilled(false);
        jButton5.setFocusPainted(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5);
        jButton5.setBounds(80, 210, 40, 30);

        jButton6.setBorderPainted(false);
        jButton6.setContentAreaFilled(false);
        jButton6.setFocusPainted(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton6);
        jButton6.setBounds(130, 210, 40, 30);

        jButton7.setBorderPainted(false);
        jButton7.setContentAreaFilled(false);
        jButton7.setFocusPainted(false);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton7);
        jButton7.setBounds(80, 260, 40, 30);

        jButton8.setBorderPainted(false);
        jButton8.setContentAreaFilled(false);
        jButton8.setFocusPainted(false);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton8);
        jButton8.setBounds(130, 260, 40, 30);

        jButton9.setBorderPainted(false);
        jButton9.setContentAreaFilled(false);
        jButton9.setFocusPainted(false);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton9);
        jButton9.setBounds(73, 303, 50, 30);

        jButton10.setBorderPainted(false);
        jButton10.setContentAreaFilled(false);
        jButton10.setFocusPainted(false);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton10);
        jButton10.setBounds(130, 300, 40, 30);

        jButton11.setBorderPainted(false);
        jButton11.setContentAreaFilled(false);
        jButton11.setFocusPainted(false);
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton11);
        jButton11.setBounds(70, 340, 50, 40);

        jButton12.setText(" ");
        jButton12.setBorderPainted(false);
        jButton12.setContentAreaFilled(false);
        jButton12.setFocusPainted(false);
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton12);
        jButton12.setBounds(129, 343, 40, 40);

        jButton13.setBorderPainted(false);
        jButton13.setContentAreaFilled(false);
        jButton13.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton13.setFocusPainted(false);
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton13);
        jButton13.setBounds(0, 660, 220, 30);

        jButton14.setBorderPainted(false);
        jButton14.setContentAreaFilled(false);
        jButton14.setFocusPainted(false);
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton14);
        jButton14.setBounds(0, 250, 30, 30);

        jSlider1.setBackground(new java.awt.Color(255, 255, 255));
        jSlider1.setMaximum(5);
        jSlider1.setMinimum(1);
        jSlider1.setValue(2);
        jPanel1.add(jSlider1);
        jSlider1.setBounds(70, 400, 110, 30);

        jButton15.setBorderPainted(false);
        jButton15.setContentAreaFilled(false);
        jButton15.setFocusPainted(false);
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton15);
        jButton15.setBounds(0, 160, 32, 30);

        jButton16.setBorderPainted(false);
        jButton16.setContentAreaFilled(false);
        jButton16.setFocusPainted(false);
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton16);
        jButton16.setBounds(0, 200, 32, 40);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paint.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(0, 0, 220, 690);

        add(jPanel1);
        jPanel1.setBounds(1140, 0, 220, 690);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/t3.png"))); // NOI18N
        jLabel2.setText("jLabel2");
        add(jLabel2);
        jLabel2.setBounds(0, 710, 1360, 40);

        jSlider2.setMaximum(10);
        jSlider2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jSlider2MouseDragged(evt);
            }
        });
        add(jSlider2);
        jSlider2.setBounds(1140, 690, 220, 20);
    }// </editor-fold>//GEN-END:initComponents
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
             if(isRunning == false){
             
                 timer.stop();
                PathFinderInterface panel = new PathFinderInterface();
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

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
            if(isRunning == false){
                removeAll();
                removeAll();
                updateUI();
                MainPanel panel = new MainPanel();
                panel.setVisible(true);
                timer.stop();
                for(int i = 0 ; i < prop.paintWidth ;i++)
                for(int j = 0 ; j <prop.paintHeight ; j++)
                    prop.painBoard[i][j] = 1;
                try {
                    panel.main(new String[]{});
                } catch (Exception ex) {
                    Logger.getLogger(PathFinderInterface.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
            // TODO add your handling code here:
       if(isRunning == false){
       
            COLOR = 2;
        changePanelColor();
       }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
       if(isRunning == false){
           COLOR = 3;
        changePanelColor();
       }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
       if(isRunning == false){
           COLOR = 4;
        changePanelColor();
       }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
      if(isRunning == false){
           COLOR = 5;
        changePanelColor();
      }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
      if(isRunning == false){
          COLOR = 6;
       changePanelColor();
      }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        if(isRunning == false){
            COLOR = 7;
         changePanelColor();
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
      if(isRunning == false){
      
           COLOR = 8;
        changePanelColor();
      }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
         if(isRunning == false){
             COLOR = 9;
          changePanelColor();
         }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        
        if(isRunning == false){COLOR = 1;   
         changePanelColor();}// TODO add your handling code here:}
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        if(isRunning == false){COLOR = 0;
         changePanelColor();}
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        if(isRunning == false){
            for(int i = 0 ; i < prop.paintWidth ;i++)
            for(int j = 0 ; j <prop.paintHeight ; j++)
                prop.painBoard[i][j] = 1;
        }
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        if(isRunning == false){COLOR = 1;  changePanelColor();}
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        if(isRunning == false){isPencil = true;}
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        if(isRunning == false){isPencil = false;}
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jSlider2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSlider2MouseDragged
        speed = jSlider2.getValue();
    }//GEN-LAST:event_jSlider2MouseDragged

    
    private  int getValue[];
    private int speed = 0;
    public void fill(){
        
      try{
          if( prop.painBoard[ getValue[0]][getValue[1]] == COLOR){
              isRunning = false;
          }
          else if(getValue!= null && isPencil == false && prop.painBoard[ getValue[0]][getValue[1]] != COLOR){
                  Thread t = new Thread();
                  t.start();
                  ArrayList<int[]> list = new ArrayList<int[]>();
                  list.add(getValue);
                  int changedValue =  prop.painBoard[ getValue[0]][ getValue[1]];
                  prop.painBoard[ getValue[0]][ getValue[1]] = COLOR;

                  // for(int j = 0 ; j <  list.size() ; j++)
                   for(int i = 0 ;  i < list.size() ; i++){
                       speed = jSlider2.getValue();
                       int x = list.get(i)[0];
                       int y  = list.get(i)[1];
                       if( x > 0){
                           if(  prop.painBoard[x-1][y]  == changedValue  ){
                                prop.painBoard[x-1][y] = COLOR;
                               list.add( new int[]{x-1,y});
                                if(speed != 0){
                                    try { Thread.sleep(speed); } catch (Exception ex) {}
                                    repaint();
                                }
                           }
                       }

                       if(x <   prop.painBoard.length -1 ){
                           if( prop.painBoard[x+1][y] ==  changedValue ){
                                prop.painBoard[x+1][y] = COLOR;
                               list.add(new int[]{x+1,y});
                               if(speed != 0){
                                    try { Thread.sleep(speed); } catch (Exception ex) {}
                                    repaint();
                                }
                            
                           }
                       }

                       if(y > 0 ){
                           if( prop.painBoard[x][y-1] ==  changedValue ){
                                prop.painBoard[x][y-1] = COLOR;
                               list.add(new int[]{x,y-1});
                                if(speed != 0){
                                    try { Thread.sleep(speed); } catch (Exception ex) {}
                                    repaint();
                                }
                        
                           }
                       }


                       if( y <  prop.painBoard[x].length -1){
                           if( prop.painBoard[x][y+1] == changedValue){
                                prop.painBoard[x][y+1] = COLOR;
                               list.add(new int[]{x,y+1});
                                if(speed != 0){
                                    try { Thread.sleep(speed); } catch (Exception ex) {}
                                    repaint();
                                }
                           }
                       }      
                    }
                
                   isRunning = false;
          }else{
            isRunning = false;
        }
             isRunning = false;
      }catch(Exception e){
       isRunning = false;
          
      }
      
      
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

    public void changePanelColor(){
        repaint();
        if( COLOR >= 0 && COLOR <=10){
            int rgb[] = prop.COLORS[COLOR];
            int red = rgb[0], green = rgb[1] , blue = rgb[2];
            jPanel2.setBackground(new Color(red,green,blue));
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        
        
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JSlider jSlider2;
    // End of variables declaration//GEN-END:variables
}
