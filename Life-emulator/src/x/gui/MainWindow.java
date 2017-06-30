package x.gui;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class MainWindow extends JFrame{
    
    public MainWindow() {
        try {UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");} 
        catch (Exception ex) {System.out.println("UImanager error"); ex.printStackTrace();}
        
        setTitle("Life-emulator");
        setBounds(20,20,700,700);
        setIconImage(new ImageIcon("src\\resources\\gui\\icons\\logo.png").getImage());
	setExtendedState(JFrame.MAXIMIZED_BOTH);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.add(new Map());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
