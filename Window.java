package project3;

import java.awt.*;
import javax.swing.*;

/** 
 * Window class-blueprint for Window object
 * @author julian
 */
public class Window extends JFrame
{  
    /** 
    * Constructor-creates objects of Window type and sets properties of window
    */ 
    public Window()
    {
        MainP mainP=new MainP();
        setBounds(100,100,500,500);
        setTitle("Task List");
        Container contentPane=getContentPane();
        contentPane.add(mainP);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}