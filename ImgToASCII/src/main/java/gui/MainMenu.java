
package gui;

import gui.listeners.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;

@SuppressWarnings("serial")
public class MainMenu extends JMenuBar{
    private JMenu menu;
    private JMenuItem menuItem;
        public MainMenu(){                 
            add(fileM());
            add(editM());
            add(helpM());
        } 
        
        private JMenu fileM(){
            menu = new JMenu("File");
            menuItem = new JMenuItem("New ASCII img",new ImageIcon("src/main/java/res.img/img_0.png"));
            menuItem.addActionListener(new NewASCIIimgAListener());
            menu.add(menuItem);
            
            menuItem = new JMenuItem("Open",new ImageIcon("src/main/java/res.img/img_0.png"));
            menuItem.addActionListener(new OpenAListener());
            menu.add(menuItem);
            
            menuItem = new JMenuItem("Save",new ImageIcon("src/main/java/res.img/img_0.png"));
            menuItem.addActionListener(new SaveAListener());
            menu.add(menuItem);
            
            menuItem = new JMenuItem("Save as",new ImageIcon("src/main/java/res.img/img_0.png"));
            menuItem.addActionListener(new SaveasAListener());
            menu.add(menuItem);
            
            menuItem = new JMenuItem("Print to HTML",new ImageIcon("src/main/java/res.img/img_0.png"));
            menuItem.addActionListener(new PrinttoHTMLAListener());
            menu.add(menuItem);
            
            menuItem = new JMenuItem("Exit",new ImageIcon("src/main/java/res.img/img_0.png"));
            menuItem.addActionListener(new ExitAListener());
            menu.add(menuItem);
            
            return menu;
        }
        private JMenu editM(){
            menu = new JMenu("Edit");
            
            menuItem = new JMenuItem("Colors",new ImageIcon("src/main/java/res.img/img_0.png"));
            menuItem.addActionListener(new ColorsAListener());
            menu.add(menuItem);
            
            menuItem = new JMenuItem("Style",new ImageIcon("src/main/java/res.img/img_0.png"));
            menuItem.addActionListener(new StyleAListener());
            menu.add(menuItem);
            
            return menu;
        }
        private JMenu helpM(){
            menu = new JMenu("Help");
            
            menuItem = new JMenuItem("Help",new ImageIcon("src/main/java/res.img/img_0.png"));
            menuItem.addActionListener(new HelpAListener());
            menu.add(menuItem);
            
            menuItem = new JMenuItem("About",new ImageIcon("src/main/java/res.img/img_0.png"));
            menuItem.addActionListener(new AboutAListener());
            menu.add(menuItem);
            
            return menu;
        }
}
