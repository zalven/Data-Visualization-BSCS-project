



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
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class SortingAlgorithms extends JPanel {

    private static Properties settings = new Properties();
    private static  Timer timer;
    
    
    public SortingAlgorithms (){
       
    
        
        int[] rgbs = settings.COLORS[0];
        int reds = rgbs[0] ,greens = rgbs[1],blues = rgbs[2];
        setBackground(new Color(reds,greens,blues));  
        for(int i = settings.ARRAYS.length-1; i >= 0 ;i--    ){
            for(int j = 0 ; j < i ; j ++){
                
                if(settings.ARRAYS[j] < settings.ARRAYS[j+1]){
                    int a = settings.ARRAYS[j];
                    settings.ARRAYS[j] = settings.ARRAYS[j+1];
                    settings.ARRAYS[j+1] =a ;
                }
                try { Thread.sleep(1); } catch (Exception ex) {}
              repaint();
             
            }       
        }
         
         
    }
 
 }
   
