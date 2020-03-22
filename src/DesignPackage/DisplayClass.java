
package DesignPackage;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D.Double;
import java.awt.geom.Ellipse2D;
import java.util.*;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.Polygon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class DisplayClass extends JPanel{
   

    
    private static Properties settings = new Properties();
  
    public  DisplayClass(){
        
        add(new PathFind());
        //frame.add(new SortingAlgorithms());
        setVisible(true);
        
        
        
        
    }
   
    
    
    
}
