
package gui;

import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.UIManager;

public class MainWindow extends JFrame {
	
    public MainWindow() {
        setupView();
        setVisible(true);
    }

    private void setupView() {
        try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); }
        catch (Exception e) { e.printStackTrace();}
        
        //setIconImage(new ImageIcon("").getImage());
        setTitle("ImgToASCII");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }

}
/*
JFileChooser fc = new JFileChooser();
int val = fc.showDialog(null, "Img");
if (val == JFileChooser.APPROVE_OPTION) {
    File file = fc.getSelectedFile();
    System.out.println(file.getPath());
}
*/