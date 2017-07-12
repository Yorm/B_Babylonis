
package gui;

import java.awt.Color;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {
	
    public MainWindow() {
        getContentPane().add(new MainPanel());
        setJMenuBar(new MainMenu());
        setupView();
        setVisible(true);
    }

    private void setupView() {
        try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); }
        catch (Exception e) { e.printStackTrace();}
        
        this.setBackground(Color.black);
        //setIconImage(new ImageIcon("").getImage());
        setSize(350, 250);
        setTitle("ImgToASCII");
        //setExtendedState(JFrame.MAXIMIZED_BOTH);
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